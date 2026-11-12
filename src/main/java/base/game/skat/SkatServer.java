package base.game.skat;

import base.Constants;
import base.Statics;
import base.game.BaseServer;
import base.game.Card;
import base.game.Player;
import base.messages.*;
import base.messages.skat.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.*;

import static base.Statics.SKAT_STATE.*;

public class SkatServer extends BaseServer {

    private Random random = new Random(System.currentTimeMillis());

    private List<Stich> stichList = new ArrayList<>();
    private Stich stich;
    private MessageGameSelected.GAMES selectedGame = MessageGameSelected.GAMES.Ramsch;


    private int hoeren = 0;
    private int sagen = 0;
    private int weitersagen = 0;
    private boolean hand;
    private boolean ouvert;
    private int currentGameValue = 0;
    private List<Card> skat;
    private int nextRamschPlayer;
    private int gamesTilRamsch;
    private int gamesTilNormal;
    private Statics.SKAT_STATE state;
    private MessageReizen lastReizen = null;
    private int gameSelector = -1;
    private int selectedGamePlayer = -1;
    private int pendingBidder = -1;
    private int pendingRamschPlayer = -1;


    public SkatServer(BaseServer server) {
        super(server.c, server.comServer);
        gameType = Statics.game.SKAT;
        spectator = 3;
        prepareGameServer(server);
    }

    @Override
    public void handleInput(MessageIn message) {
        Message requestObject;
        try {
            requestObject = Message.fromString(message.getInput());
        } catch (Exception ex) {
            log.warn("Rejected malformed Skat message", ex);
            return;
        }
        if (requestObject == null || requestObject.getCommand() == null) {
            return;
        }
        if (MessageAddPlayer.COMMAND.equals(requestObject.getCommand())) {
            super.handleInput(message);
            return;
        }
        Player player = getAuthenticatedPlayer(message);
        try {
            if (player == null || !isValidGameMessage(player, requestObject)) {
                log.warn(Constants.REJECTED_UNAUTHORIZED_OR_INVALID_SKAT_ACTION);
                return;
            }
        } catch (RuntimeException ex) {
            log.warn(Constants.REJECTED_UNAUTHORIZED_OR_INVALID_SKAT_ACTION);
            return;
        }
        super.handleInput(message);
        try {
            switch (requestObject.getCommand()) {
                case MessageGameSelected.COMMAND:
                    handleGameSelected(requestObject);
                    break;
                case MessageCurrentStich.LAST:
                    handleCurrentStich(requestObject);
                    break;
                case MessageReizen.COMMAND:
                    handleReizen(requestObject);
                    break;
                case MessagePassen.COMMAND:
                    handlePassen(requestObject);
                    break;
                case MessageGetSkat.COMMAND:
                    handleGetSkat(requestObject);
                    break;
                case MessageSkatCards.SKAT:
                    handleSkat(requestObject);
                    break;
                case MessageSchieben.COMMAND:
                    handleSchieben(requestObject);
                    break;
                case MessageGrandHand.COMMAND:
                    handleGrandHand(requestObject);
                    break;
            }
        } catch (Exception ex) {
            log.error(Constants.FAILED_TO_HANDLE_SKAT_ACTION, ex);
        }
    }

    private boolean isValidGameMessage(Player player, Message message) {
        switch (message.getCommand()) {
            case MessagePutCard.COMMAND:
                return isValidCardPlay(player, message);
            case MessageCurrentStich.LAST:
            case MessageCurrentStich.SPECIFIC:
                return new MessageCurrentStich(message).getPlayerNumber() == player.getNumber();
            case MessageReizen.COMMAND:
                MessageReizen bid = new MessageReizen(message);
                return state == REIZEN && bid.getPlayerName().equals(player.getName())
                        && player.getNumber() == pendingBidder
                        && isActiveBidder(player.getNumber())
                        && (!bid.isActive() && bid.getValue() == 0
                        || bid.isActive() && isValidBid(bid.getValue()));
            case MessagePassen.COMMAND:
                return state == REIZEN
                        && new MessagePassen(message).getPlayerName().equals(player.getName())
                        && player.getNumber() == pendingBidder
                        && isActiveBidder(player.getNumber());
            case MessageGameSelected.COMMAND:
                return state == REIZEN && gameSelector == player.getNumber()
                        && new MessageGameSelected(message).getPlayerName().equals(player.getName());
            case MessageGrandHand.COMMAND:
                return state == GRAND_HAND && nextRamschPlayer == player.getNumber()
                        && new MessageGrandHand(message).getPlayerName().equals(player.getName());
            case MessageSchieben.COMMAND:
                return state == RAMSCH
                        && pendingRamschPlayer == player.getNumber()
                        && (player.state == Player.PLAYER_STATE.SELECTING_RAMSCH
                        || player.state == Player.PLAYER_STATE.SELECTING_CARDS_RAMSCH)
                        && new MessageSchieben(message).getPlayerName().equals(player.getName());
            case MessageGetSkat.COMMAND:
                return state == PLAY && selectedGamePlayer == player.getNumber()
                        && !hand && skat != null
                        && player.getName().equals(message.getParams().get(Constants.PLAYER).getAsString());
            case MessageSkatCards.SKAT:
                MessageSkatCards skatCards = new MessageSkatCards(message);
                return skatCards.getPlayerName().equals(player.getName())
                        && hasCardsFromHand(player, message.getParams().getAsJsonArray(Constants.CARDS), 2)
                        && (state == RAMSCH && pendingRamschPlayer == player.getNumber()
                        && (player.state == Player.PLAYER_STATE.SELECTING_RAMSCH
                        || player.state == Player.PLAYER_STATE.SELECTING_CARDS_RAMSCH)
                        || selectedGamePlayer == player.getNumber() && !hand
                        && state == PLAY && stich == null && currentTrickNumber == 0);
            default:
                return true;
        }
    }

    private boolean isActiveBidder(int playerNumber) {
        return playerNumber == hoeren || playerNumber == sagen || playerNumber == weitersagen;
    }

    private boolean isValidBid(int value) {
        return value > currentGameValue
                && Arrays.stream(MessageReizen.VALUES).anyMatch(validValue -> validValue == value);
    }

    private boolean isValidCardPlay(Player player, Message message) {
        if (state != PLAY || !player.isNotSpectator()
                || player.getNumber() != currentPlayer
                || new MessagePutCard(message).getPlayerNumber() != player.getNumber()
                || !hasCardsFromHand(player, singleCardNumber(message), 1)) {
            return false;
        }
        Card playedCard = SkatCards.ALL_CARDS.get(
                message.getParams().get(Constants.CARD_NUMBER).getAsInt());
        if (stich == null || stich.getCardMap().isEmpty() || stich.getCardMap().size() > 2) {
            return true;
        }
        Card leadCard = stich.getCardMap().keySet().stream()
                .filter(card -> card.playOrder == 0)
                .findFirst()
                .orElse(null);
        if (leadCard == null) {
            return true;
        }
        boolean ledTrump = SkatCards.isTrumpf(leadCard, selectedGame);
        boolean hasLedType = player.getHand().stream().anyMatch(card -> ledTrump
                ? SkatCards.isTrumpf(card, selectedGame)
                : !SkatCards.isTrumpf(card, selectedGame) && card.suit.equals(leadCard.suit));
        return !hasLedType || (ledTrump
                ? SkatCards.isTrumpf(playedCard, selectedGame)
                : !SkatCards.isTrumpf(playedCard, selectedGame)
                && playedCard.suit.equals(leadCard.suit));
    }

    private JsonArray singleCardNumber(Message message) {
        JsonArray cardNumber = new JsonArray();
        JsonElement element = message.getParams().get(Constants.CARD_NUMBER);
        if (element != null) {
            cardNumber.add(element);
        }
        return cardNumber;
    }

    private boolean hasCardsFromHand(Player player, JsonArray cardNumbers, int expectedCount) {
        if (cardNumbers == null || cardNumbers.size() != expectedCount || player.getHand() == null) {
            return false;
        }
        Set<Integer> uniqueCards = new HashSet<>();
        for (JsonElement element : cardNumbers) {
            int cardNumber;
            try {
                cardNumber = element.getAsInt();
            } catch (RuntimeException ex) {
                return false;
            }
            if (cardNumber < 0 || cardNumber >= SkatCards.ALL_CARDS.size()
                    || !uniqueCards.add(cardNumber)
                    || player.getHand().stream().noneMatch(card -> card.cardNumber == cardNumber)) {
                return false;
            }
        }
        return true;
    }

    private void handleGrandHand(Message message) {
        MessageGrandHand messageGrandHand = new MessageGrandHand(message);
        Player player = players.stream().filter(p -> p.getName().equals(messageGrandHand.getPlayerName())).findFirst().get();
        if (messageGrandHand.getGrandHand()) {
            selectedGame = MessageGameSelected.GAMES.Grand;
            send2All(new MessageGameSelected(player.getName(), selectedGame, true, false));
            hand = true;
            player.setRe(true);
            runGame(beginner);
            beginner--;
        } else {
            send2All(new MessageDisplayMessage(String.format("%s spielt keinen Grand hand", player.getName())));
            askNextGrandHandPlayer();
        }
    }

    private void handleCurrentStich(Message requestObject) {
        if (stichList.size() > 0) {
            try {
                MessageCurrentStich cs = new MessageCurrentStich(stichList.get(stichList.size() - 1).getCardMap(), MessageCurrentStich.LAST);
                queueOut(players.get(new MessageCurrentStich(requestObject).getPlayerNumber()), cs);
            } catch (Exception ex) {
                log.warn(Constants.FAILED_TO_HANDLE_SKAT_ACTION, ex);
            }
        }
    }

    @Override
    protected void handlePutCard(Message message) {
        MessagePutCard messagePutCard = new MessagePutCard(message);
        if (stich == null || stich.getCardMap().size() > 2) {
            stich = new Stich(players, currentTrickNumber, selectedGame);
            currentTrickNumber++;
        }
        stich.addCard(players.get(currentPlayer), messagePutCard.getCard(Statics.game.SKAT));
        send2All(message);
        currentPlayer++;
        if (currentPlayer == spectator) {
            currentPlayer++;
        }
        if (currentPlayer >= players.size()) {
            currentPlayer = 0;
        }
        if (currentPlayer == spectator) {
            currentPlayer++;
        }

        if (stich.getCardMap().size() > 2) {
            try {
                int winner = stich.getWinner();
                if (c.skat.autoAbortNull) {
                    if (selectedGame == MessageGameSelected.GAMES.Null) {
                        if (players.get(winner).isRe()) {
                            endIt();
                            return;
                        }
                    }
                }

                stichList.add(stich);
                try {
                    points.put(winner, points.get(winner) + stich.calculatePoints());
                } catch (Exception e) {
                    log.error("Failed to calculate Skat trick points", e);
                }
                currentPlayer = winner;
                send2All(new MessageUpdateUserPanel(players.stream().filter(p -> p.getNumber() == winner)
                        .findFirst().get().getName(), Constants.HAT_STICHE));
                if (currentTrickNumber > 9) {
                    endIt();
                    return;
                }
            } catch (Exception ex) {
                log.error("Failed to resolve Skat trick", ex);
            }
        }
        if (currentTrickNumber < 11) {
            send2All(new MessageWait4Player(players.stream().filter(p -> p.getNumber() == currentPlayer).findFirst().get().getName()));
        }
    }

    @Override
    public void endIt() {
        super.endIt();
        state = END;
        SkatEndDialog e = new SkatEndDialog(selectedGame, players, stichList, skat);
        send2All(new MessageGameEnd(e.getReString1(), e.getKontraString1(),
                e.getPlayer1String(), e.getPlayer2String(), e.getPlayer3String(),
                e.getRemaining()));
        wait4NextRound = true;
        readyMap = new HashMap<>();
        for (int i = 0; i < players.size(); i++) {
            readyMap.put(i, false);
        }
        if (c.skat.pflichtRamsch) {
            if (gamesTilRamsch == 0) {
                if (selectedGame == MessageGameSelected.GAMES.Ramsch) {
                    gamesTilNormal--;
                    if (gamesTilNormal < 1) {
                        gamesTilRamsch = c.skat.wiederholRamsch;
                    }
                }
            } else {
                gamesTilRamsch--;
                if (gamesTilRamsch < 1) {
                    gamesTilNormal = players.size();
                }
            }
        }
    }


    @Override
    protected void runGame(int player) {
        super.runGame(player);
        state = PLAY;
        players.forEach(p -> p.state = Player.PLAYER_STATE.PLAY_CARD);
        lastReizen = null;
        send2All(new MessageWait4Player(players.stream().filter(p -> p.getNumber() == player).findAny().get().getName()));
    }

    private void handleGameSelected(Message message) {
        MessageGameSelected messageGameSelected = new MessageGameSelected(message);
        String player = messageGameSelected.getPlayerName();
        selectedGamePlayer = gameSelector;
        gameSelector = -1;
        pendingBidder = -1;
        selectedGame = messageGameSelected.getSelectedGame();
        hand = messageGameSelected.gethand();
        ouvert = messageGameSelected.getOuvert();
        send2All(message);
        send2All(new MessageDisplayMessage(player + " spielt " + selectedGame.name()
                + (hand ? " hand" : Constants.EMPTY_STRING) + (ouvert ? " ouvert" : Constants.EMPTY_STRING)));
        runGame(beginner);
        if (ouvert) {
            players.forEach(p -> {
                if (!p.getName().equals(player)) {
                    queueOut(p, MessageSkatCards.OuvertCards(player, players.stream().filter(q -> q.getName().equals(player))
                            .findFirst().get().getHand()));
                }
            });
        }
    }

    private void handleSchieben(Message message) {
        MessageSchieben messageSchieben = new MessageSchieben(message);
        send2All(new MessageDisplayMessage(String.format("%s schiebt", messageSchieben.getPlayerName())));
        log.info("asking nextRamsch because Schieben");
        askNextRamschPlayer();
    }

    private void handleSkat(Message message) {
        MessageSkatCards messageSkatCards = new MessageSkatCards(message);
        skat = messageSkatCards.getCards();
        Player player = players.stream().filter(p -> p.getName().equals(
                messageSkatCards.getPlayerName())).findFirst().get();
        player.getHand().removeAll(skat);
        if (selectedGame == MessageGameSelected.GAMES.Ramsch && currentGameValue == 0) {
            log.info("asking nextRamsch because selected game");
            askNextRamschPlayer();
        }
    }


    private void askNextRamschPlayer() {
        state = RAMSCH;
        if (nextRamschPlayer == hoeren) {
            pendingRamschPlayer = nextRamschPlayer;
            queueOut(players.get(nextRamschPlayer), new MessageRamschSkat());
            players.get(nextRamschPlayer).state = Player.PLAYER_STATE.SELECTING_CARDS_RAMSCH;
            nextRamschPlayer = sagen;
        } else if (nextRamschPlayer == sagen) {
            pendingRamschPlayer = nextRamschPlayer;
            queueOut(players.get(nextRamschPlayer), new MessageRamschSkat());
            players.get(nextRamschPlayer).state = Player.PLAYER_STATE.SELECTING_RAMSCH;
            nextRamschPlayer = weitersagen;
        } else if (nextRamschPlayer == weitersagen) {
            pendingRamschPlayer = nextRamschPlayer;
            queueOut(players.get(nextRamschPlayer), new MessageRamschSkat());
            players.get(nextRamschPlayer).state = Player.PLAYER_STATE.SELECTING_RAMSCH;
            nextRamschPlayer = -1;
        } else {
            pendingRamschPlayer = -1;
            runGame(beginner);
        }
    }


    private void askNextGrandHandPlayer() {
        state = GRAND_HAND;
        if (nextRamschPlayer == hoeren) {
            nextRamschPlayer = sagen;
            queueOut(players.get(nextRamschPlayer), new MessageGrandHand(players.get(nextRamschPlayer).getName(), false));
        } else if (nextRamschPlayer == sagen) {
            nextRamschPlayer = weitersagen;
            queueOut(players.get(nextRamschPlayer), new MessageGrandHand(players.get(nextRamschPlayer).getName(), false));
        } else {
            selectedGame = MessageGameSelected.GAMES.Ramsch;
            ramsch();
        }
    }

    private void handleGetSkat(Message message) {
        MessageSkatCards messageSkatCards = new MessageSkatCards(message);
        queueOut(players.stream().filter(player -> player.getName()
                        .equals(messageSkatCards.getPlayerName())).findFirst().get(),
                MessageSkatCards.SkatCards(Constants.SERVER, skat));
        send2All(new MessageDisplayMessage(players.stream().filter(player -> player.getName()
                .equals(messageSkatCards.getPlayerName())).findFirst().get().getName() + " nimmt den Skat auf"));
        players.stream().filter(player -> player.getName()
                .equals(messageSkatCards.getPlayerName())).findFirst().get().getHand().addAll(skat);
        skat.clear();
        players.stream().filter(player -> player.getName().equals(messageSkatCards.getPlayerName()))
                .findFirst().get().state = Player.PLAYER_STATE.SELECTING_CARDS_GAME;
    }

    private void handlePassen(Message message) {
        MessagePassen messagePassen = new MessagePassen(message);
        send2All(new MessageDisplayMessage(messagePassen.getPlayerName() + ": weg"));
        Optional<Player> player = players.stream().filter(p -> p.getName().equals(messagePassen.getPlayerName())).findFirst();
        if (player.isPresent()) {
            int p = player.get().getNumber();
            if (p == hoeren) {
                hoeren = -1;
                if (currentGameValue == 0) {
                    pendingBidder = -1;
                    ramsch();
                } else if (sagen < 0) {
                    selectGame(weitersagen);
                } else {
                    askNext(weitersagen);
                }
            } else if (p == weitersagen) {
                weitersagen = -1;
                if (hoeren < 0) {
                    selectGame(sagen);
                } else if (sagen < 0) {
                    if (currentGameValue > 0) {
                        selectGame(hoeren);
                    } else {
                        askNext(hoeren);
                    }
                }
            } else if (p == sagen) {
                sagen = -1;
                if (hoeren < 0) {
                    selectGame(weitersagen);
                } else {
                    askNext(weitersagen);
                }
            } else {
                log.error("Da lief etwas falsch");
            }
        }
    }

    private void ramsch() {
        pendingBidder = -1;
        pendingRamschPlayer = -1;
        setPlayerRoles();
        nextRamschPlayer = hoeren;
        send2All(new MessageDisplayMessage("Ramsch: " + players.get(beginner).getName() + " bekommt den Skat"));
        askNextRamschPlayer();
    }

    private void selectGame(int playerNumber) {
        players.get(playerNumber).setRe(true);
        players.get(playerNumber).state = Player.PLAYER_STATE.SELECTING_GAME;
        gameSelector = playerNumber;
        pendingBidder = -1;
        queueOut(players.get(playerNumber), new MessageSelectGame());
    }

    private void askNext(int playerNumber) {
        pendingBidder = playerNumber;
        queueOut(players.get(playerNumber), lastReizen = new MessageReizen(
                players.get(playerNumber).getName(),
                currentGameValue,
                true));
    }

    private void handleReizen(Message message) {
        MessageReizen messageReizen = new MessageReizen(message);
        Optional<Player> player = players.stream().filter(p -> p.getName().equals(messageReizen.getPlayerName())).findFirst();
        if (player.isPresent()) {
            int p = player.get().getNumber();
            if (messageReizen.isActive()) {
                currentGameValue = messageReizen.getValue();
                send2All(new MessageDisplayMessage(
                        String.format(Constants.PLAYER_POINTS_FORMAT,
                                messageReizen.getPlayerName(),
                                currentGameValue)));
                if (p == hoeren) {
                    selectGame(hoeren);
                } else if (p == sagen) {
                    pendingBidder = hoeren;
                    queueOut(players.get(hoeren), lastReizen = new MessageReizen(players.get(hoeren).getName(), currentGameValue, false));
                } else if (p == weitersagen) {
                    if (sagen < 0) {
                        pendingBidder = hoeren;
                        queueOut(players.get(hoeren), lastReizen = new MessageReizen(players.get(hoeren).getName(), currentGameValue, false));
                    } else if (hoeren < 0) {
                        pendingBidder = sagen;
                        queueOut(players.get(sagen), lastReizen = new MessageReizen(players.get(sagen).getName(), currentGameValue, false));
                    }
                }
            } else {
                send2All(new MessageDisplayMessage(
                        String.format(Constants.PLAYER_POINTS_FORMAT,
                                messageReizen.getPlayerName(),
                                Constants.JA)));
                if (p == hoeren) {
                    if (sagen < 0) {
                        pendingBidder = weitersagen;
                        queueOut(players.get(weitersagen), lastReizen = new MessageReizen(players.get(weitersagen).getName(), currentGameValue, true));
                    } else {
                        pendingBidder = sagen;
                        queueOut(players.get(sagen), lastReizen = new MessageReizen(players.get(sagen).getName(), currentGameValue, true));
                    }
                } else if (p == sagen) {
                    pendingBidder = weitersagen;
                    queueOut(players.get(weitersagen), lastReizen = new MessageReizen(players.get(weitersagen).getName(), currentGameValue, true));
                }
            }
        }
    }

    @Override
    public void shuffleCards() {
        for (Player player1 : players) {
            send2All(new MessageUpdateUserPanel(player1.getName(), Constants.EMPTY_STRING));
        }
        try {
            stichList = new ArrayList<>();
            random = new Random(System.currentTimeMillis());
            List<Card> cardList = new ArrayList<>(SkatCards.ALL_CARDS);

            players.forEach(player -> {
                player.setHand(new ArrayList<>());
                player.setPoints(0);
                if (player.isNotSpectator()) {
                    for (int i = 0; i < 10; i++) {
                        Card card = cardList.get(random.nextInt(cardList.size()));
                        player.getHand().add(card);
                        cardList.remove(card);
                    }
                }
            });
            skat = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                skat.add(cardList.get(i));
            }
            players.forEach(player -> queueOut(player, new MessageCards(player.getHand())));


            //TODO: Extract method to reset all game variables for a new game

            hand = false;
            ouvert = false;
            currentGameValue = 0;
            stich = null;
            selectedGame = MessageGameSelected.GAMES.Ramsch;
            points = new HashMap<>();
            state = REIZEN;
            gameSelector = -1;
            selectedGamePlayer = -1;
            pendingBidder = -1;
            pendingRamschPlayer = -1;
            setPlayerRoles();

            if (gamesTilNormal < 1) {
                pendingBidder = sagen;
                queueOut(players.get(sagen), lastReizen = new MessageReizen(players.get(sagen).getName(), currentGameValue, true));
            } else if (gamesTilRamsch < 1) {
                nextRamschPlayer = hoeren;
                askNextGrandHandPlayer();
            }

        } catch (Exception ex) {
            log.error("Failed to initialize next Skat game", ex);
        }
    }

    @Override
    public void startGame() {
        super.startGame();
        send2All(new MessageAnnounceSpectator(spectator, beginner));
        if (c.skat.pflichtRamsch) {
            gamesTilRamsch = c.skat.beginnRamsch * players.size();
            if (gamesTilRamsch == 0) {
                gamesTilNormal = players.size();
            } else {
                gamesTilNormal = 0;
            }
        }
        shuffleCards();
    }


    @Override
    protected void nextGame() {
        beginner++;
        if (beginner > players.size() - 1) {
            beginner = 0;
        }
        if (players.size() > 3) {
            spectator = beginner - 1;
            if (spectator < 0) {
                spectator = players.size() - 1;
            }
        }
        players.forEach(player -> {
            player.setSpectator(false);
            player.setRe(false);
        });
        if (players.size() > 3) {
            players.get(spectator).setSpectator(true);
        }
        send2All(new MessageAnnounceSpectator(spectator, beginner));
        shuffleCards();
    }


    private void setPlayerRoles() {
        hoeren = beginner;
        sagen = nextNotSpectator(hoeren);
        weitersagen = nextNotSpectator(sagen);
    }

    private int nextNotSpectator(int s) {
        int i = 0;
        while (i < players.size()) {
            i++;
            if ((i + s) >= players.size()) {
                s -= players.size();
            }
            if (players.get(i + s).isNotSpectator()) {
                return i + s;
            }
        }
        return -1;
    }

    @Override
    protected void updateReconnectedPlayer(Player player) {
        player.getOutMessages().addFirst(new MessageAnnounceSpectator(spectator, beginner));
        player.getOutMessages().addFirst(new MessageCards(player.getHand()));
        player.getOutMessages().addFirst(new MessageStartGame(gameType.name()));
        Optional<Player> opt = players.stream().filter(Player::isRe).findFirst();
        Player rePlayer;
        if (opt.isPresent()) {
            rePlayer = opt.get();
            player.queue(new MessageGameSelected(rePlayer.getName(), selectedGame, hand, ouvert));
        } else {
            player.queue(new MessageGameSelected(Constants.EMPTY_STRING, selectedGame, hand, ouvert));
        }
        List<String> playerList = new ArrayList<>();
        players.forEach(p -> playerList.add(p.getName()));
        player.queue(MessagePlayerList.playersInLobby(playerList));
        if (stich != null) {
            stich.getBaseCardMap().keySet().forEach(baseCard -> player.queue(new MessagePutCard(stich.getBaseCardMap().get(baseCard), baseCard)));
        }
        switch (state) {
            case REIZEN:
                if (lastReizen.getPlayerName().equals(player.getName())) {
                    queueOut(player, lastReizen);
                }
                player.queue(new MessageDisplayMessage(lastReizen.getPlayerName() + ": " + lastReizen.getValue()));
                break;
            case GRAND_HAND:
                break;
            case RAMSCH:
                queueOut(players.get(nextRamschPlayer), new MessageRamschSkat());
                break;
            case PLAY:
                player.queue(new MessageWait4Player(players.get(currentPlayer).getName()));
                break;
            case END:
                readyMap.put(player.getNumber(), true);
                break;
        }
        super.updateReconnectedPlayer(player);
    }

}
