package base.game.doko;

import base.Constants;
import base.Statics;
import base.game.BaseServer;
import base.game.Card;
import base.game.Player;
import base.messages.*;
import base.messages.doko.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import static base.messages.doko.MessageGameSelected.GAMES.*;

public class DokoServer extends BaseServer {

    Random random = new Random(System.currentTimeMillis());

    private List<Stich> stichList = new ArrayList<>();
    private Stich stich;
    private MessageGameSelected.GAMES selectedGame = NORMAL;


    private boolean wait4Gesund = false;
    private boolean schwein = false;
    private List<Card> armutCards;
    private HashMap<Integer, MessageGameSelected.GAMES> gameSelection = new HashMap<>();
    private int aufspieler = -1;
    private int armutplayer = -1;
    private int hochzeitSpieler = -1;
    private List<Player> players2Ask = new ArrayList<>();
    private Player pendingArmutPlayer;
    private Player armutReceiver;
    private boolean wait4Partner;


    public DokoServer(BaseServer server) {
        super(server.c, server.comServer);
        adminName =server.getAdminName();
        gameType = Statics.game.DOKO;
        spectator = 4;
        prepareGameServer(server);
    }


    @Override
    public void handleInput(MessageIn message) {
        Message requestObject;
        try {
            requestObject = Message.fromString(message.getInput());
        } catch (Exception ex) {
            log.warn("Rejected malformed Doppelkopf message", ex);
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
                log.warn(Constants.REJECTED_UNAUTHORIZED_OR_INVALID_DOPPELKOPF_ACTION);
                return;
            }
        } catch (RuntimeException ex) {
            log.warn(Constants.REJECTED_UNAUTHORIZED_OR_INVALID_DOPPELKOPF_ACTION);
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
                case MessageSendCards.COMMAND:
                    handleSendCards(requestObject);
                    break;
                case MessageGetArmut.COMMAND:
                    handleGetArmut(requestObject);
                    break;
                case MessageSchweinExists.COMMAND:
                    schwein = true;
                    break;
                case MessageCardsReturned.COMMAND:
                    armutReceiver = null;
                    runGame(beginner);
                    break;
            }
        } catch (Exception ex) {
            log.error("Failed to process request {}", requestObject, ex);
        }
    }

    private boolean isValidGameMessage(Player player, Message message) {
        switch (message.getCommand()) {
            case MessagePutCard.COMMAND:
                return isValidCardPlay(player, message);
            case MessageGameSelected.COMMAND:
                MessageGameSelected selection = new MessageGameSelected(message);
                return wait4Gesund && player.isNotSpectator()
                        && selection.getPlayerNumber() == player.getNumber();
            case MessageCurrentStich.LAST:
            case MessageCurrentStich.SPECIFIC:
                return new MessageCurrentStich(message).getPlayerNumber() == player.getNumber();
            case MessageGetArmut.COMMAND:
                return pendingArmutPlayer == player
                        && new MessageGetArmut(message).getPlayerNumber() == player.getNumber();
            case MessageSendCards.COMMAND:
                MessageSendCards cards = new MessageSendCards(message);
                if (MessageSendCards.RICH.equals(cards.getReceiver())) {
                    return player.getNumber() == armutplayer
                            && selectedGame == ARMUT
                            && hasArmutCardsFromHand(player, message.getParams().getAsJsonArray(Constants.CARDS));
                }
                return MessageSendCards.POOR.equals(cards.getReceiver())
                        && selectedGame == ARMUT
                        && armutReceiver == player
                        && armutCards != null
                        && hasCardsFromHand(player, message.getParams().getAsJsonArray(Constants.CARDS),
                        armutCards.size());
            case MessageCardsReturned.COMMAND:
                return player.getNumber() == armutplayer && armutReceiver != null;
            case MessageSchweinExists.COMMAND:
                return (selectedGame == NORMAL || selectedGame == KARO || selectedGame == ARMUT)
                        && player.getHand() != null
                        && player.getHand().stream().filter(card ->
                        card.suit.equals(Statics.KARO) && card.kind.equals(Statics.ASS)).count() >= 2;
            default:
                return true;
        }
    }

    private boolean isValidCardPlay(Player player, Message message) {
        MessagePutCard putCard = new MessagePutCard(message);
        JsonArray cardNumber = new JsonArray();
        JsonElement cardNumberElement = message.getParams().get(Constants.CARD_NUMBER);
        if (cardNumberElement == null) {
            return false;
        }
        cardNumber.add(cardNumberElement);
        if (!gameRunning || wait4Gesund || !player.isNotSpectator()
                || putCard.getPlayerNumber() != player.getNumber()
                || putCard.getPlayerNumber() != currentPlayer
                || !hasCardsFromHand(player, cardNumber, 1)) {
            return false;
        }
        if (!c.other.bedienen || stich == null || stich.getCardMap().isEmpty()
                || stich.getCardMap().size() > 3) {
            return true;
        }
        Card leadCard = stich.getCardMap().keySet().stream()
                .filter(card -> card.playOrder == 0)
                .findFirst()
                .orElse(null);
        if (leadCard == null) {
            return true;
        }
        boolean ledTrump = DokoCards.isTrumpf(leadCard, selectedGame);
        return player.getHand().stream().noneMatch(card -> ledTrump
                ? DokoCards.isTrumpf(card, selectedGame)
                : card.suit.equals(leadCard.suit))
                || (ledTrump
                ? DokoCards.isTrumpf(putCard.getCard(Statics.game.DOKO), selectedGame)
                : putCard.getCard(Statics.game.DOKO).suit.equals(leadCard.suit));
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
            if (cardNumber < 0 || cardNumber >= DokoCards.ALL_CARDS.size()
                    || !uniqueCards.add(cardNumber)
                    || player.getHand().stream().noneMatch(card -> card.cardNumber == cardNumber)) {
                return false;
            }
        }
        return true;
    }

    private boolean hasArmutCardsFromHand(Player player, JsonArray cardNumbers) {
        return cardNumbers != null && cardNumbers.size() <= 3
                && hasCardsFromHand(player, cardNumbers, cardNumbers.size());
    }


    private void handleCurrentStich(Message requestObject) {
        if (!stichList.isEmpty()) {
            try {
                MessageCurrentStich cs = new MessageCurrentStich(stichList.get(stichList.size() - 1).getCardMap(), MessageCurrentStich.LAST);
                queueOut(players.get(new MessageCurrentStich(requestObject).getPlayerNumber()), cs);
            } catch (Exception ex) {
                log.warn("Failed to handle Doppelkopf action", ex);
            }
        }
    }


    private void handleGetArmut(Message requestObject) {
        MessageGetArmut messageGetArmut = new MessageGetArmut(requestObject);
        Player respondingPlayer = players.get(messageGetArmut.getPlayerNumber());
        pendingArmutPlayer = null;
        if (messageGetArmut.getsArmut()) {
            armutReceiver = respondingPlayer;
            queueOut(players.get(messageGetArmut.getPlayerNumber()),
                    new MessageSendCards(armutCards, MessageSendCards.RICH));
            players.get(messageGetArmut.getPlayerNumber()).setRe(true);
            players.stream().filter(player -> player.getNumber() != messageGetArmut.getPlayerNumber())
                    .collect(Collectors.toList()).forEach(player -> queueOut(player, new MessageDisplayMessage(
                            Statics.getString(Statics.ARMUT_ACCEPT,
                                    players.get(messageGetArmut.getPlayerNumber()).getName()))));
        } else {
            armutReceiver = null;
            send2All(new MessageDisplayMessage(
                    Statics.getString(Statics.ARMUT_REFUSE,
                            players.get(messageGetArmut.getPlayerNumber()).getName())));
            askNextPlayer2GetArmut();
        }
    }


    private void handleSendCards(Message requestObject) {
        MessageSendCards messageSendCards = new MessageSendCards(requestObject);
        armutCards = new ArrayList<>();
        if (messageSendCards.getReceiver().equals(MessageSendCards.RICH)) {
            messageSendCards.getCards().forEach(card -> {
                card.trump = DokoCards.isTrumpf(card, selectedGame);
                armutCards.add(card);
            });
            askNextPlayer2GetArmut();
        } else if (messageSendCards.getReceiver().equals(MessageSendCards.POOR)) {
            send2All(new MessageDisplayMessage(Statics.getString(Statics.ARMUT_RETURN,
                    players.get(armutplayer).getName(),
                    messageSendCards.getCards().stream().filter(card -> card.trump).count())));
            queueOut(players.get(armutplayer),
                    requestObject);
            players2Ask = new ArrayList<>();
        }
    }


    private void handleGameSelected(Message requestObject) {
        if (wait4Gesund) {
            MessageGameSelected messageGameSelected = new MessageGameSelected(requestObject);
            gameSelection.put(messageGameSelected.getPlayerNumber(), messageGameSelected.getSelectedGame());
            if (messageGameSelected.getSelectedGame() == KOENIGE) {
                send2All(new MessageDisplayMessage(
                        Statics.getString(Statics.KOENIGE,
                                players.get(messageGameSelected.getPlayerNumber()).getName())));
            }
        }
        if (!gameSelection.isEmpty() && gameSelection.size() > 3) {
            setGameToPlay(gameSelection);
        } else {
            StringBuilder s = new StringBuilder("Warte auf " + (4 - gameSelection.size()) + " Spieler");
            players.forEach(player -> {
                if (gameSelection.containsKey(player.getNumber())) {
                    queueOut(player, new MessageDisplayMessage(s.toString()));
                }
            });
        }
    }


    @Override
    protected void handlePutCard(Message message) {
        MessagePutCard messagePutCard = new MessagePutCard(message);
        if (stich == null || stich.getCardMap().size() > 3) {
            stich = new Stich(players, currentTrickNumber, selectedGame, c.doko);
            currentTrickNumber++;
        }
        Card card = messagePutCard.getCard(Statics.game.DOKO);
        card.trump = DokoCards.isTrumpf(card, selectedGame);
        stich.addCard(players.get(messagePutCard.getPlayerNumber()), card);
        players.get(messagePutCard.getPlayerNumber()).removeCard(card);
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

        if (stich.getCardMap().size() > 3) {
            try {
                int winner = stich.getWinner(schwein);

                stichList.add(stich);
                try {
                    points.put(winner, points.get(winner) + stich.calculatePoints());
                } catch (Exception e) {
                    log.error("Failed to calculate Doppelkopf trick points", e);
                }
                currentPlayer = winner;
                if (wait4Partner) {
                    if (currentTrickNumber < 4) {
                        if (hochzeitSpieler != winner) {
                            players.get(winner).setRe(true);
                            wait4Partner = false;
                        }
                    } else {
                        wait4Partner = false;
                        log.info("kein Ehepartner gefunden");
                    }
                }
                send2All(new MessageUpdateUserPanel(players.stream().filter(p -> p.getNumber() == winner)
                        .findFirst().get().getName(), Constants.HAT_STICHE));
                if (currentTrickNumber > 9) {
                    endIt();
                    return;
                }
            } catch (Exception ex) {
                log.error("Failed to resolve Doppelkopf trick", ex);
            }
        }
        if (currentTrickNumber < 11) {
            send2All(new MessageWait4Player(players.stream().filter(p -> p.getNumber() == currentPlayer).findFirst().get().getName()));
        }
    }


    @Override
    public void endIt() {
        super.endIt();
        DokoEndDialog endDialog = new DokoEndDialog(players, stichList);
        send2All(new MessageGameEnd(endDialog.getReString1(), endDialog.getReString2(), endDialog.getKontraString1(), endDialog.getKontraString2(), endDialog.getRemaining()));
        wait4NextRound = true;
        readyMap = new HashMap<>();
        for (int i = 0; i < players.size(); i++) {
            readyMap.put(i, false);
        }
    }


    @Override
    protected void runGame(int player) {
        super.runGame(player);
        aufspieler = -1;
        send2All(new MessageGameSelected(player, selectedGame));
        send2All(new MessageWait4Player(players.stream().filter(p -> p.getNumber() == (Math.max(player, 0))).findAny().get().getName()));
    }


    @Override
    protected void nextGame() {
        if (selectedGame == NORMAL
                || selectedGame == ARMUT) {
            beginner++;
            if (beginner > players.size() - 1) {
                beginner = 0;
            }
            if (players.size() > 4) {
                spectator = beginner - 1;
                if (spectator < 0) {
                    spectator = players.size() - 1;
                }
            }
            players.forEach(player -> {
                player.setSpectator(false);
                player.setRe(false);
            });
            wait4Partner = false;
            hochzeitSpieler = -1;
            if (players.size() > 4) {
                players.get(spectator).setSpectator(true);
            }
        }
        send2All(new MessageAnnounceSpectator(spectator, beginner));
        shuffleCards();
    }


    private void createListOfPotentialPartners() {
        players2Ask = new ArrayList<>();
        log.info("Armutplayer= {}", armutplayer);
        int korrValue = 1;
        for (int i = 0; i < players.size() - 1; i++) {
            log.info("korrValue= {}", korrValue);
            log.info("i= {}", i);
            int p = i + armutplayer + korrValue;
            log.info("p= {}", p);
            if (p > players.size() - 1) {
                korrValue -= players.size();
                p = i + armutplayer + korrValue;
            }
            if (players.get(p).isNotSpectator()) {
                players2Ask.add(players.get(p));
            }
        }
    }

    private void askNextPlayer2GetArmut() {
        if (!players2Ask.isEmpty()) {
            log.info("Ask {}", players2Ask.get(0));
            pendingArmutPlayer = players2Ask.get(0);
            queueOut(pendingArmutPlayer, new MessageGetArmut());
            players2Ask.remove(0);
        } else {
            pendingArmutPlayer = null;
            send2All(new MessageDisplayMessage(Statics.getString(Statics.NEU_GEBEN)));
            shuffleCards();
        }

    }

    private void setGameToPlay(HashMap<Integer, MessageGameSelected.GAMES> selection) {
        wait4Gesund = false;
        players.forEach(player -> player.setRe(false));
        if (selection.values().stream().allMatch(p -> p == NORMAL)) {
            selectedGame = NORMAL;
            aufspieler = -1;
            players.forEach(player -> {
                if (player.hasCard(Statics.KREUZ, Statics.DAME)) {
                    player.setRe(true);
                }
            });
            send2All(new MessageDisplayMessage(Statics.getString(Statics.NORMALES_SPIEL)));
            runGame(beginner);
        } else if (selection.containsValue(KOENIGE)) {
            int index = -1;
            for (int i : selection.keySet()) {
                if (selection.get(i) == KOENIGE) {
                    index = i;
                }
            }
            int finalIndex = index;
            send2All(new MessageDisplayMessage(Statics.getString(Statics.KOENIGE, players.get(finalIndex).getName())));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.warn("Interrupted before shuffling cards", e);
                Thread.currentThread().interrupt();
                return;
            }
            shuffleCards();
        } else if (selection.containsValue(DAMEN)
                || selection.containsValue(BUBEN)
                || selection.containsValue(BUBENDAMEN)
                || selection.containsValue(FLEISCHLOS)
                || selection.containsValue(KREUZ)
                || selection.containsValue(PIK)
                || selection.containsValue(HERZ)
                || selection.containsValue(KARO)) {
            int checkPlayer = beginner;
            while (true) {
                if (selection.containsKey(checkPlayer)
                        && (selection.get(checkPlayer).equals(DAMEN)
                        || selection.get(checkPlayer).equals(BUBEN)
                        || selection.get(checkPlayer).equals(BUBENDAMEN)
                        || selection.get(checkPlayer).equals(FLEISCHLOS)
                        || selection.get(checkPlayer).equals(KREUZ)
                        || selection.get(checkPlayer).equals(PIK)
                        || selection.get(checkPlayer).equals(HERZ)
                        || selection.get(checkPlayer).equals(KARO))) {
                    selectedGame = selection.get(checkPlayer);
                    aufspieler = checkPlayer;
                    send2All(new MessageAnnounceSpectator(spectator, aufspieler));
                    send2All(new MessageUpdateUserPanel(players.get(beginner).getName(), Constants.EMPTY_STRING));
                    send2All(new MessageUpdateUserPanel(players.get(aufspieler).getName(), Constants.EMPTY_STRING));
                    send2All(new MessageDisplayMessage(
                            Statics.getString(
                                    Statics.SOLO, players.get(aufspieler).getName(),
                                    Statics.getGameName(selectedGame))));
                    players.get(aufspieler).setRe(true);
                    runGame(aufspieler);
                    return;
                } else {
                    checkPlayer++;
                    if (checkPlayer > players.size()) {
                        checkPlayer = 0;
                    }
                }
            }
        } else if (selection.containsValue(ARMUT)) {
            int index = -1;
            for (int i : selection.keySet()) {
                if (selection.get(i).equals(ARMUT)) {
                    index = i;
                    selectedGame = ARMUT;
                }
            }
            int finalIndex = index;
            armutplayer = finalIndex;
            createListOfPotentialPartners();
            send2All(new MessageDisplayMessage(
                    Statics.getString(
                            Statics.ARMUT_GIVE,
                            players.get(armutplayer).getName(),
                            players.get(armutplayer).getHand().stream().filter(card -> card.trump).count())));
            queueOut(players.stream().filter(p -> p.getNumber() == finalIndex).findAny().get(),
                    new MessageSelectCards4Armut());
        } else if (selection.containsValue(HOCHZEIT)) {
            selectedGame = NORMAL;
            aufspieler = -1;
            for (Integer i : selection.keySet()) {
                if (selection.get(i).equals(HOCHZEIT)) {
                    players.get(i).setRe(true);
                    send2All(new MessageDisplayMessage(Statics.getString(Statics.HOCHZEIT, players.get(i).getName())));
                    hochzeitSpieler = i;
                    break;
                }
            }
            wait4Partner = true;
            runGame(beginner);
        }
    }

    @Override
    protected void shuffleCards() {
        for (Player player1 : players) {
            send2All(new MessageUpdateUserPanel(player1.getName(), Constants.EMPTY_STRING));
        }
        stichList = new ArrayList<>();
        random = new Random(System.currentTimeMillis());
        shuffle(new ArrayList<>(DokoCards.ALL_CARDS));
        for (Player player : players) {
            if (player.isNotSpectator()) {
                MessageCards cards = new MessageCards(player.getHand());
                queueOut(player, cards);
            }
        }
        wait4Gesund = true;
        armutplayer = -1;
        pendingArmutPlayer = null;
        armutReceiver = null;
        schwein = false;
        stich = null;
        gameSelection = new HashMap<>();
        send2All(new MessageSelectGame());
    }

    private void shuffle(List<Card> cardList) {
        players.forEach(player -> {
            player.setHand(new ArrayList<>());
            if (player.isNotSpectator()) {
                for (int i = 0; i < 10; i++) {
                    Card card = cardList.get(random.nextInt(cardList.size()));
                    player.getHand().add(card);
                    cardList.remove(card);
                }
            }
        });
    }


    @Override
    public void startGame() {
        super.startGame();
        if (players.size() > 4) {
            if (beginner == 0) {
                spectator = 4;
            } else {
                spectator = beginner - 1;
            }
            players.get(spectator).setSpectator(true);
        }
        send2All(new MessageAnnounceSpectator(spectator, beginner));
        shuffleCards();
    }

    @Override
    public String toString() {
        return Constants.HTML_OPEN + selectedGame + Constants.HTML_BREAK +
                spectator + Constants.HTML_BREAK +
                currentPlayer + Constants.HTML_BREAK;
    }

    @Override
    protected void updateReconnectedPlayer(Player player) {
        player.getOutMessages().addFirst(new MessageAnnounceSpectator(spectator, aufspieler));
        player.getOutMessages().addFirst(new MessageCards(player.getHand()));
        player.getOutMessages().addFirst(new MessageStartGame(gameType.name()));
        player.queue(new MessageGameSelected(player.getNumber(), selectedGame));
        List<String> playerList = new ArrayList<>();
        players.forEach(p -> playerList.add(p.getName()));
        player.queue(MessagePlayerList.playersInLobby(playerList));
        if (stich != null) {
            stich.getCardMap().keySet().forEach(baseCard -> player.queue(new MessagePutCard(stich.getCardMap().get(baseCard), baseCard)));
        }
        if (wait4Gesund) {
            player.queue(new MessageSelectGame());
        } else if (wait4NextRound) {
            readyMap.put(player.getNumber(), true);
            players.stream().filter(Player::isAdmin).forEach(p ->
                    p.queue(new MessageDisplayMessage(player.getName() + Constants.IS_READY)));
        } else {
            player.queue(new MessageWait4Player(players.get(currentPlayer).getName()));
        }
        super.updateReconnectedPlayer(player);
    }

}
