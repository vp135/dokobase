package base.game.skat;

import base.Statics;
import base.game.BaseServer;
import base.game.Card;
import base.game.Player;
import base.messages.*;
import base.messages.skat.*;

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


    public SkatServer(BaseServer server) {
        super(server.c, server.comServer);
        gameType = Statics.game.SKAT;
        spectator = 3;
        prepareGameServer(server);
    }

    @Override
    public void handleInput(MessageIn message) {
        super.handleInput(message);
        Message requestObject = Message.fromString(message.getInput());
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
            log.error(ex.toString());
        }
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
                log.warn(ex.toString());
            }
        }
    }

    @Override
    protected void handlePutCard(Message message) {
        MessagePutCard messagePutCard = new MessagePutCard(message);
        if (stich == null || stich.getCardMap().size() > 2) {
            stich = new Stich(players, currentStichNumber, selectedGame);
            currentStichNumber++;
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
                    e.printStackTrace();
                }
                currentPlayer = winner;
                send2All(new MessageUpdateUserPanel(players.stream().filter(p -> p.getNumber() == winner)
                        .findFirst().get().getName(), " hat Stich(e)"));
                if (currentStichNumber > 9) {
                    endIt();
                    return;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (currentStichNumber < 11) {
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
        selectedGame = messageGameSelected.getSelectedGame();
        hand = messageGameSelected.gethand();
        ouvert = messageGameSelected.getOuvert();
        send2All(message);
        send2All(new MessageDisplayMessage(player + " spielt " + selectedGame.name()
                + (hand ? " hand" : "") + (ouvert ? " ouvert" : "")));
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
            queueOut(players.get(nextRamschPlayer), new MessageRamschSkat());
            players.get(nextRamschPlayer).state = Player.PLAYER_STATE.SELECTING_CARDS_RAMSCH;
            nextRamschPlayer = sagen;
        } else if (nextRamschPlayer == sagen) {
            queueOut(players.get(nextRamschPlayer), new MessageRamschSkat());
            players.get(nextRamschPlayer).state = Player.PLAYER_STATE.SELECTING_RAMSCH;
            nextRamschPlayer = weitersagen;
        } else if (nextRamschPlayer == weitersagen) {
            queueOut(players.get(nextRamschPlayer), new MessageRamschSkat());
            players.get(nextRamschPlayer).state = Player.PLAYER_STATE.SELECTING_RAMSCH;
            nextRamschPlayer = -1;
        } else {
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
                MessageSkatCards.SkatCards("server", skat));
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
        setPlayerRoles();
        nextRamschPlayer = hoeren;
        send2All(new MessageDisplayMessage("Ramsch: " + players.get(beginner).getName() + " bekommt den Skat"));
        askNextRamschPlayer();
    }

    private void selectGame(int playerNumber) {
        players.get(playerNumber).setRe(true);
        queueOut(players.get(playerNumber), new MessageSelectGame());
    }

    private void askNext(int playerNumber) {
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
                        String.format("%s: %s",
                                messageReizen.getPlayerName(),
                                currentGameValue)));
                if (p == hoeren) {
                    selectGame(hoeren);
                } else if (p == sagen) {
                    queueOut(players.get(hoeren), lastReizen = new MessageReizen(players.get(hoeren).getName(), currentGameValue, false));
                } else if (p == weitersagen) {
                    if (sagen < 0) {
                        queueOut(players.get(hoeren), lastReizen = new MessageReizen(players.get(hoeren).getName(), currentGameValue, false));
                    } else if (hoeren < 0) {
                        queueOut(players.get(sagen), lastReizen = new MessageReizen(players.get(sagen).getName(), currentGameValue, false));
                    }
                }
            } else {
                send2All(new MessageDisplayMessage(
                        String.format("%s: %s",
                                messageReizen.getPlayerName(),
                                "Ja")));
                if (p == hoeren) {
                    if (sagen < 0) {
                        queueOut(players.get(weitersagen), lastReizen = new MessageReizen(players.get(weitersagen).getName(), currentGameValue, true));
                    } else {
                        queueOut(players.get(sagen), lastReizen = new MessageReizen(players.get(sagen).getName(), currentGameValue, true));
                    }
                } else if (p == sagen) {
                    queueOut(players.get(weitersagen), lastReizen = new MessageReizen(players.get(weitersagen).getName(), currentGameValue, true));
                }
            }
        }
    }

    @Override
    public void shuffleCards() {
        for (Player player1 : players) {
            send2All(new MessageUpdateUserPanel(player1.getName(), ""));
        }
        try {
            stichList = new ArrayList<>();
            random = new Random(System.currentTimeMillis());
            List<Card> cardList = new ArrayList<>(SkatCards.ALL_CARDS);

            players.forEach(player -> {
                player.setHand(new ArrayList<>());
                player.setPoints(0);
                if (!player.isSpectator()) {
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
            setPlayerRoles();

            if (gamesTilNormal < 1) {
                queueOut(players.get(sagen), lastReizen = new MessageReizen(players.get(sagen).getName(), currentGameValue, true));
            } else if (gamesTilRamsch < 1) {
                nextRamschPlayer = hoeren;
                askNextGrandHandPlayer();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
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
            if (!players.get(i + s).isSpectator()) {
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
            player.queue(new MessageGameSelected("", selectedGame, hand, ouvert));
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
