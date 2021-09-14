package base.game.doko;

import base.Statics;
import base.config.DokoConfig;
import base.game.Card;
import base.game.Player;
import base.helper.Logger;
import base.messages.doko.MessageGameSelected;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static base.messages.doko.MessageGameSelected.GAMES.*;

public class Stich {

    private final static Logger log = new Logger("Stich", 3, true);

    private final transient HashMap<Card, Integer> cardMap = new HashMap<>();
    private int points;
    private int winner;
    private final int stichnumber;
    private int doppelkopf = 0;
    private int karlchen = 0;
    private int fuchsGefangen = 0;
    private int herzstich = 0;
    private Card winningCard;
    private int summe;
    private List<Player> players;
    private final MessageGameSelected.GAMES gameType;
    private final DokoConfig config;


    public Stich(List<Player> players, int stichnumber, MessageGameSelected.GAMES gameType, DokoConfig config) {
        this.players = players;
        this.stichnumber = stichnumber;
        this.gameType = gameType;
        this.config = config;
    }

    public Stich(MessageGameSelected.GAMES gameType, DokoConfig config) {
        this.gameType = gameType;
        this.config = config;
        this.stichnumber = 0;
        this.players = new ArrayList<>();
    }

    public void addCard(Player player, Card card) {
        addCard(player.getNumber(), card);
    }

    public void addCard(int playerNumber, Card card) {
        card.playOrder = cardMap.size();
        cardMap.put(card, playerNumber);
    }

    public HashMap<Card, Integer> getCardMap() {
        return cardMap;
    }

    public int getWinner() {
        return winner;
    }

    public int getWinner(boolean schwein) {
        Card currentWinner = cardMap.keySet().stream().filter(card -> card.playOrder == 0).findFirst().get();
        if (cardMap.size() == 4) {
            for (int i = 1; i < cardMap.size(); i++) {
                int finalI = i;
                Card nextCard = cardMap.keySet().stream().filter(card -> card.playOrder == finalI).findFirst().get();
                currentWinner = Compare.getWinner(currentWinner, nextCard, gameType, schwein);
            }
        }
        this.points = calculatePoints();
        winner = cardMap.get(currentWinner);
        winningCard = currentWinner;
        return cardMap.get(currentWinner);
    }

    public int calculatePoints() {
        int result = 0;
        for (Card card : this.cardMap.keySet()) {
            switch (card.kind) {
                case Statics.ZEHN: {
                    result += 10;
                    break;
                }
                case Statics.BUBE: {
                    result += 2;
                    break;
                }
                case Statics.DAME: {
                    result += 3;
                    break;
                }
                case Statics.KOENIG: {
                    result += 4;
                    break;
                }
                case Statics.ASS: {
                    result += 11;
                    break;
                }
            }
        }
        summe = result;
        return result;
    }

    public int getPoints() {
        return points;
    }

    public String getExtraPoints() {
        StringBuilder builder = new StringBuilder();
        if (doppelkopf > 0) {
            builder.append("Doppelkopf");
        }
        if (karlchen > 0) {
            if (builder.length() > 0) {
                builder.append(";");
            }
            builder.append("Karlchen");
        }
        if (herzstich > 0) {
            if (builder.length() > 0) {
                builder.append(";");
            }
            builder.append("Herzstich");
        }
        if (fuchsGefangen > 0) {
            if (builder.length() > 0) {
                builder.append(";");
            }
            builder.append("Fuchs gefangen(").append(fuchsGefangen).append(")");
        }
        if (builder.length() > 0) {
            return "(" + builder + ")";
        } else {
            return "";
        }
    }

    public void check4ExtraPoints() {
        checkDoppelkopf();
        if (gameType == NORMAL
                || gameType.equals(ARMUT)
                || gameType.equals(HOCHZEIT)
                || gameType.equals(HERZ)
                || gameType.equals(KARO)
                || gameType.equals(KREUZ)
                || gameType.equals(PIK)) {
            checkKarlchen();
        }
        if (gameType.equals(NORMAL)
                || gameType.equals(ARMUT)
                || gameType.equals(HOCHZEIT)
                || gameType.equals(KARO)
                || gameType.equals(KREUZ)
                || gameType.equals(PIK)) {
            checkHerzStich();
        }
        if (gameType.equals(NORMAL)
                || gameType.equals(ARMUT)
                || gameType.equals(HOCHZEIT)
                || gameType.equals(KARO)) {
            checkFuchsGefangen();
        }
    }

    private void checkFuchsGefangen() {
        fuchsGefangen = 0;
        if (config.sonderpunkte.fuchs) {
            cardMap.keySet().stream().filter(card -> (card.suit.equals(Statics.KARO) && card.kind.equals(Statics.ASS)))
                    .collect(Collectors.toList()).forEach(card -> {
                        if (players.get(cardMap.get(card)).isRe() != players.get(winner).isRe()) {
                            fuchsGefangen++;
                            log.info("Fuchs gefangen");
                        }
                    });
        }
    }

    private void checkHerzStich() {
        if (config.sonderpunkte.herzstich) {
            if (winningCard.suit.equals(Statics.HERZ) && !winningCard.trump) {
                herzstich = 1;
            }
        }
    }

    private void checkDoppelkopf() {
        if (config.sonderpunkte.doppelkopf) {
            if (summe > 39) {
                doppelkopf = 1;
                log.info("Doppelkopf");
            }
        }
    }

    private void checkKarlchen() {
        if (config.sonderpunkte.karlchen) {
            if (stichnumber == 9) {
                if (winningCard.suit.equals(Statics.KREUZ) && winningCard.kind.equals(Statics.BUBE)) {
                    karlchen = 1;
                    log.info("Karlchen");
                }
            }
        }
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        cardMap.keySet().forEach(card -> s
                .append(cardMap.get(card))
                .append(":")
                .append(card.suit)
                .append(" ")
                .append(card.kind)
                .append("\n"));
        return s.toString();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
