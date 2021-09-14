package base.game;

import base.Statics;
import base.communication.ComClient;
import base.config.Configuration;
import base.game.Card;
import base.game.doko.Compare;
import base.game.doko.DokoCards;
import base.game.doko.SortHand;
import base.game.doko.Stich;
import base.game.doko.assist.Assist;
import base.game.doko.assist.Bucket;
import base.helper.Logger;
import base.interfaces.IInputputHandler;
import base.messages.*;
import base.messages.doko.MessageGameEnd;
import base.messages.doko.MessageGameSelected;
import base.messages.doko.MessageSchweinExists;
import base.messages.doko.MessageSelectGame;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

import static base.messages.doko.MessageGameSelected.GAMES.*;

public class NPCClient implements IInputputHandler {

    private final ComClient comClient;
    private final Configuration config;
    private MessageGameSelected.GAMES selectedGame = MessageGameSelected.GAMES.NORMAL;
    private int currentCardsOnTable = 0;
    private Stich currentTrick;
    private boolean schweinExists = false;
    private boolean wait4Player = false;
    private List<Card> hand = new ArrayList<>();
    private final String playerName;
    private final List<String> players = new ArrayList<>();
    private int spectator;
    private Statics.game game = Statics.game.DOKO;
    private Card mustPlay;
    private final HashMap<Integer, Card> tableStich = new HashMap<>();
    private int aufspieler;
    private final Assist assist;


    private final Logger log;
    private List<Player> pList;
    private ArrayList<String> partners;

    public NPCClient(Configuration config, String playerName) {
        this.config = config;
        this.comClient = new ComClient(config.connection.server, config.connection.port, this, playerName, 0);
        this.comClient.start();
        this.playerName = playerName;
        this.assist = new Assist();
        log = new Logger(playerName, 0, true);
    }

    @Override
    public void handleInput(Message message) {
        switch (message.getCommand()) {
            case MessageStartGame.COMMAND:
                game = (new MessageStartGame(message)).getGame();
                break;
            case MessageCards.COMMAND:
                handleCards(message);
                break;
            case MessagePutCard.COMMAND:
                handlePutCard(message);
                break;
            case MessageGameSelected.COMMAND:
                handleGameSelected(message);
                break;
            case MessageSelectGame.COMMAND:
                handleSelectGame(message);
                break;
            case MessageGameEnd.COMMAND:
                handleGameEnd(message);
                break;
            case MessageWait4Player.COMMAND:
                handleWait4Player(message);
                break;
            case MessagePlayerList.IN_LOBBY:
            case MessagePlayerList.CHANGE_ORDER:
                handlePlayersInLobby(message);
                break;
        }
    }


    //handle server messages

    private void handleGameSelected(Message message) {
        MessageGameSelected messageGameSelected = new MessageGameSelected(message);
        selectedGame = messageGameSelected.getSelectedGame();
        if (hand != null && hand.size() > 0) {
            hand = SortHand.sort(hand, selectedGame, schweinExists);
            if (selectedGame == NORMAL
                    || selectedGame == KARO
                    || selectedGame == ARMUT) {
                if (hand.stream().filter(p -> p.suit.equals(Statics.KARO)
                        && p.kind.equals(Statics.ASS)).count() > 1) {
                    send(new MessageSchweinExists());
                    schweinExists = true;
                } else {
                    schweinExists = false;
                }
            }
        }
        aufspieler = -1;
        assist.setHand(hand);
        assist.setGame(selectedGame);
    }

    private void handlePlayersInLobby(Message message) {
        MessagePlayerList messagePlayersInLobby = new MessagePlayerList(message);
        DefaultListModel<String> model = new DefaultListModel<>();
        players.clear();
        players.addAll(messagePlayersInLobby.getPlayerNamesList());
        model.addAll(players);
    }

    private void handleWait4Player(Message message) {
        if (game == Statics.game.DOKO) {
            handleWait4PlayerDoko(message);
        } else if (game == Statics.game.SKAT) {
            handleWait4PlayerSkat(message);
        }
    }

    private void handleWait4PlayerSkat(Message message) {

    }

    private void handleWait4PlayerDoko(Message message) {
        if (message.getParams().get("player").getAsString().equals(playerName)) {
            Optional<Card> optCard;
            if (mustPlay != null) {
                if (mustPlay.trump) {
                    if (assist.playerBucket.trumpf.size() > 0) {
                        optCard = playTrumpCard();
                    } else {
                        optCard = getCardCantFollowTrump();
                    }
                } else {
                    if (assist.playerBucket.getListBySuit(mustPlay.suit).size() > 0) {
                        optCard = playFehlCard();
                    } else {
                        optCard = getCardCantFollowSuit();
                    }
                }
            } else {
                optCard = hand.stream().findAny();
            }
            optCard.ifPresent(card -> {
                send(new MessagePutCard(players.indexOf(playerName), card), true);
                log.info(String.format("played %s", card));
                hand.remove(card);
            });
            assist.playerBucket = new Bucket(hand, selectedGame, false);
        }
    }

    private void handleGameEnd(Message message) {
        currentCardsOnTable = 0;
        schweinExists = false;
        wait4Player = false;
        hand = new ArrayList<>();
        send(new MessageReadyForNextRound(players.indexOf(playerName)));
    }

    private void handleSelectGame(Message message) {
        if (game == Statics.game.DOKO) {
            handleSelectGameDoko();
        } else if (game == Statics.game.SKAT) {
            handleSelectGameSkat();
        }
    }

    private void handleSelectGameSkat() {

    }

    private void handleSelectGameDoko() {
        Assist assist = new Assist(hand);
        for (MessageGameSelected.GAMES g : MessageGameSelected.GAMES.values()) {
            assist.setGame(g);
            double risk = 4.5;
            if (assist.playSolo() < risk) {
                send(new MessageGameSelected(players.indexOf(playerName), g));
                return;
            }
        }
        send(new MessageGameSelected(players.indexOf(playerName), NORMAL));
    }

    private void handlePutCard(Message message) {
        if (game == Statics.game.DOKO) {
            handlePutCardDoko(message);
        } else if (game == Statics.game.SKAT) {
            handlePutCardSkat(message);
        }
    }

    private void handlePutCardSkat(Message message) {

    }

    private void handlePutCardDoko(Message message) {
        try {
            int ownNumber = players.indexOf(playerName);
            List<Integer> tmpList = new ArrayList<>();
            int i = ownNumber;

            while (tmpList.size() < 4) {
                if (i != spectator) {
                    tmpList.add(i);
                }
                i++;
                if (i > players.size() - 1) {
                    i = 0;
                }
            }
            currentCardsOnTable++;
            MessagePutCard messagePutCard = new MessagePutCard(message);
            Card card = messagePutCard.getCard(Statics.game.DOKO);
            card.trump = DokoCards.isTrumpf(card, selectedGame);
            assist.putCard(card);
            checkForPartner(messagePutCard.getPlayerNumber(), card);
            if (mustPlay != null) {
                if (mustPlay.trump) {
                    if (!card.trump) {
                        pList.get(messagePutCard.getPlayerNumber()).hasTrumpf = 0;
                    }
                } else if (!mustPlay.suit.equals(card.suit)) {
                    pList.get(messagePutCard.getPlayerNumber()).setSuit(mustPlay.suit, 0);
                    log.info(pList.get(messagePutCard.getPlayerNumber()).name + " hat keine " + mustPlay.suit + " mehr");
                }
            }
            if (currentCardsOnTable == 1) {
                currentTrick = new Stich(selectedGame, config.doko);
                currentTrick.addCard(messagePutCard.getPlayerNumber(), card);
                mustPlay = card;
            }
            if (currentCardsOnTable > 3) {
                mustPlay = null;
                currentCardsOnTable = 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void checkForPartner(int playerNumber, Card card) {
        switch (selectedGame) {
            case NORMAL:
                if (card.suit.equals(Statics.KREUZ) && card.kind.equals(Statics.DAME)) {
                    pList.get(playerNumber).party = 1;
                    if (!assist.gameBucket.contains(card)) {
                        pList.forEach(player -> {
                            if (player.party == 0) {
                                player.party = -1;
                            }
                        });
                    }
                }
                break;
        }

        Player player = pList.stream().filter(p -> p.name.equals(playerName)).findFirst().get();
        partners = new ArrayList<>();
        pList.stream().filter(p -> p.party == player.party).collect(Collectors.toList()).forEach(p -> {
            if (!p.name.equals(playerName)) {
                partners.add(p.name);
            }
        });
    }

    private void handleCards(Message message) {
        MessageCards messageCards = new MessageCards(message);
        selectedGame = MessageGameSelected.GAMES.NORMAL;
        hand = messageCards.getCards(Statics.game.DOKO);
        pList = new ArrayList<>();
        players.forEach(p -> pList.add(new Player(p)));
        setSelf();
    }


    //Decicionmaking for different situations

    private Optional<Card> playFehlCard() {
        List<Card> cards = assist.playerBucket.getListBySuit(mustPlay.suit);
        Comparator<Card> comp = Compare.getComparer(selectedGame, false).reversed();
        cards.sort(comp);
        Optional<Card> optCard;
        if ((assist.calcHighCardForBucket(mustPlay.suit) > 0) && (comp.compare(cards.get(0), mustPlay) > 0)) {
            optCard = cards.stream().findFirst();
        } else {
            optCard = getCardByValueLowest(cards);
        }
        return optCard;
    }


    private Optional<Card> playTrumpCard() {
        List<Card> winning = new ArrayList<>();
        List<Card> losing = new ArrayList<>();
        assist.playerBucket.trumpf.forEach(card ->
        {
            if (Compare.getComparer(selectedGame, false).compare(card, mustPlay) > 0) {
                winning.add(card);
            } else {
                losing.add(card);
            }
        });
        Optional<Card> optCard;
        if (winning.size() > losing.size()) {
            optCard = winning.stream().findAny();
        } else {
            if (checkIfPartnerGetsTrick()) {
                optCard = getCardByValueHighest(assist.playerBucket.trumpf);
            } else {
                optCard = getCardByValueLowest(losing);
            }
        }
        return optCard;
    }

    private boolean checkIfPartnerGetsTrick() {

        return partners.contains(pList.get(currentTrick.getWinner(schweinExists)).name);
    }


    private Optional<Card> getCardByValueLowest(List<Card> cards) {
        Optional<Card> optCard;
        if (cards.stream().anyMatch(card -> card.value == 2)) {
            optCard = cards.stream().filter(card -> card.value == 2).findAny();
        } else if (cards.stream().anyMatch(card -> card.value == 3)) {
            optCard = cards.stream().filter(card -> card.value == 3).findAny();
        } else if (cards.stream().anyMatch(card -> card.value == 4)) {
            optCard = cards.stream().filter(card -> card.value == 4).findAny();
        } else if (cards.stream().anyMatch(card -> card.value == 10)) {
            optCard = cards.stream().filter(card -> card.value == 10).findAny();
        } else {
            optCard = cards.stream().filter(card -> card.value == 11).findAny();
        }
        return optCard;
    }

    private Optional<Card> getCardByValueHighest(List<Card> cards) {
        Optional<Card> optCard;
        if (cards.stream().anyMatch(card -> card.value == 11)) {
            optCard = cards.stream().filter(card -> card.value == 11).findAny();
        } else if (cards.stream().anyMatch(card -> card.value == 10)) {
            optCard = cards.stream().filter(card -> card.value == 10).findAny();
        } else if (cards.stream().anyMatch(card -> card.value == 4)) {
            optCard = cards.stream().filter(card -> card.value == 4).findAny();
        } else if (cards.stream().anyMatch(card -> card.value == 3)) {
            optCard = cards.stream().filter(card -> card.value == 3).findAny();
        } else {
            optCard = cards.stream().filter(card -> card.value == 2).findAny();
        }
        return optCard;
    }

    private Optional<Card> getCardCantFollowTrump() {
        Optional<Card> optCard;
        if (hand.stream().anyMatch(card -> !card.trump && card.value == 2)) {
            optCard = hand.stream().filter(card -> !card.trump && card.value == 2).findAny();
        } else if (hand.stream().anyMatch(card -> !card.trump && card.value == 3)) {
            optCard = hand.stream().filter(card -> !card.trump && card.value == 3).findAny();

        } else if (hand.stream().anyMatch(card -> !card.trump && card.value == 4)) {
            optCard = hand.stream().filter(card -> !card.trump && card.value == 4).findAny();

        } else if (hand.stream().anyMatch(card -> !card.trump && card.value == 10)) {
            optCard = hand.stream().filter(card -> !card.trump && card.value == 10).findAny();

        } else if (hand.stream().anyMatch(card -> !card.trump && card.value == 11)) {
            optCard = hand.stream().filter(card -> !card.trump && card.value == 11).findAny();
        } else {
            optCard = hand.stream().findAny();
        }
        return optCard;
    }

    private Optional<Card> getCardCantFollowSuit() {
        Optional<Card> optCard;
        if (hand.stream().anyMatch(card -> card.trump)) {
            optCard = hand.stream().filter(card -> card.trump).findAny();
        } else if (hand.stream().anyMatch(card -> !card.trump && card.value == 2)) {
            optCard = hand.stream().filter(card -> !card.trump && card.value == 2).findAny();
        } else if (hand.stream().anyMatch(card -> !card.trump && card.value == 3)) {
            optCard = hand.stream().filter(card -> !card.trump && card.value == 3).findAny();

        } else if (hand.stream().anyMatch(card -> !card.trump && card.value == 4)) {
            optCard = hand.stream().filter(card -> !card.trump && card.value == 4).findAny();

        } else if (hand.stream().anyMatch(card -> !card.trump && card.value == 10)) {
            optCard = hand.stream().filter(card -> !card.trump && card.value == 10).findAny();

        } else if (hand.stream().anyMatch(card -> !card.trump && card.value == 11)) {
            optCard = hand.stream().filter(card -> !card.trump && card.value == 11).findAny();
        } else {
            optCard = hand.stream().findAny();
        }
        return optCard;
    }

    //


    private void setSelf() {
        Player player = pList.stream().filter(p -> p.name.equals(playerName)).findFirst().get();
        if (hand.stream().anyMatch(card -> card.suit.equals(Statics.KREUZ) && card.kind.equals(Statics.DAME))) {
            player.party = 1;
        } else {
            player.party = -1;
        }
    }


    private void send(Message message) {
        send(message, false);
    }


    private void send(Message message, boolean delayed) {
        if (delayed) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        comClient.queueOutMessage(message);
    }


    public static class Player {

        public String name;
        public boolean isPartner;
        public int party = 0;
        public float hasKreuz = 100f;
        public float hasPik = 100f;
        public float hasHerz = 100f;
        public float hasKaro = 100f;
        public float hasTrumpf = 100f;

        public Player(String name) {
            this.name = name;
        }

        public void setSuit(String suit, float i) {
            switch (suit) {
                case (Statics.KREUZ):
                    hasKreuz = i;
                    break;
                case (Statics.PIK):
                    hasPik = i;
                    break;
                case (Statics.HERZ):
                    hasHerz = i;
                    break;
                case (Statics.KARO):
                    hasKaro = i;
                    break;
            }
        }
    }

}
