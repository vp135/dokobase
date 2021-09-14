package base.game.doko.assist;

import base.Statics;
import base.game.Card;
import base.game.doko.Compare;
import base.game.doko.DokoCards;
import base.messages.doko.MessageGameSelected;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Assist {
    public Bucket playerBucket;
    public Bucket gameBucket;
    public MessageGameSelected.GAMES game;
    public boolean schwein;
    private JLabel lTrumpf;
    private JLabel lKreuz;
    private JLabel lPik;
    private JLabel lHerz;
    private JLabel lKaro;
    private JFrame frame;
    private List<Card> hand;

    public Assist() {

    }

    public Assist(List<Card> cards) {
        this.hand = cards;
    }

    public void setGame(MessageGameSelected.GAMES game) {
        this.game = game;
        createGameBucket();
        if (hand != null) {
            createPlayerBucket(hand);
        }
    }

    public Assist(List<Card> cards, MessageGameSelected.GAMES game) {
        this.game = game;
        this.hand = cards;
        createGameBucket();
        createPlayerBucket(cards);
        //calcHighCard();
        //openAssistWindow();
    }

    public void resetAssist() {
        this.schwein = false;
    }

    public void setHand(List<Card> cards) {
        this.hand = cards;
    }

    public double playSolo() {

        // Calc höchste Karten einer Farbe
        int sum = 0;
        int b1 = calcHighCardForBucket(Statics.TRUMPF);
        int b2 = calcHighCardForBucket(Statics.KREUZ);
        int b3 = calcHighCardForBucket(Statics.PIK);
        int b4 = calcHighCardForBucket(Statics.HERZ);
        int b5 = calcHighCardForBucket(Statics.KARO);


        //calc mögliche verluste
        int l1 = Math.max(calcCardsToDraw(b1, gameBucket.trumpf.size(), playerBucket.trumpf.size()), 0);
        int l2 = Math.max(calcCardsToDraw(b2, gameBucket.kreuz.size(), playerBucket.kreuz.size()), 0);
        int l3 = Math.max(calcCardsToDraw(b3, gameBucket.pik.size(), playerBucket.pik.size()), 0);
        int l4 = Math.max(calcCardsToDraw(b4, gameBucket.herz.size(), playerBucket.herz.size()), 0);
        int l5 = Math.max(calcCardsToDraw(b5, gameBucket.karo.size(), playerBucket.karo.size()), 0);
        int loss = l1 + l2 + l3 + l4 + l5;

        //calc wahrscheinliche Verteilung der verbleibenden Trümpfe
        int rTrump = Math.max((gameBucket.trumpf.size() - b1 - playerBucket.trumpf.size()), 0);
        double prob = calcProbableCardDistubution(rTrump, 10000);
        return prob + loss;
    }

    private double calcProbableCardDistubution(int rTrump, int count) {
        int prob = 0;
        Random r = new Random(System.currentTimeMillis());
        for (int j = 0; j < count; j++) {
            int[] players = new int[3];
            int i = 0;
            while (i < rTrump) {
                int tmp = r.nextInt(3);
                if (players[tmp] + 1 < 11) {
                    players[tmp]++;
                    i++;
                }
            }
            prob += Arrays.stream(players).max().getAsInt();
        }
        return (double) prob / (double) count;
    }

    public int calcHighCardForBucket(String bucketName) {
        int nmb = 0;
        try {
            List<Card> gamelist = new ArrayList<>();
            List<Card> playerList = new ArrayList<>();
            switch (bucketName) {
                case Statics.TRUMPF:
                    gamelist = gameBucket.trumpf;
                    playerList = playerBucket.trumpf;
                    break;
                case Statics.KREUZ:
                    gamelist = gameBucket.kreuz;
                    playerList = playerBucket.kreuz;
                    break;
                case Statics.PIK:
                    gamelist = gameBucket.pik;
                    playerList = playerBucket.pik;
                    break;
                case Statics.HERZ:
                    gamelist = gameBucket.herz;
                    playerList = playerBucket.herz;
                    break;
                case Statics.KARO:
                    gamelist = gameBucket.karo;
                    playerList = playerBucket.karo;
                    break;
            }
            for (int i = 0; i < gamelist.size() && i < playerList.size(); i++) {
                if (Compare.getComparer(game, schwein).compare(gamelist.get(i), playerList.get(i)) == 0) {
                    nmb++;
                } else {
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return nmb;
    }

    private int calcCardsToDraw(int highCardCount, int allCardCount, int ownCardCount) {
        return Math.min((allCardCount - highCardCount - ownCardCount), (ownCardCount - highCardCount));
    }

    public void createGameBucket() {
        gameBucket = new Bucket(DokoCards.ALL_CARDS, game, schwein);
    }

    public void createPlayerBucket(List<Card> cards) {
        playerBucket = new Bucket(cards, game, schwein);
    }

    public void putCard(Card card) {
        gameBucket.remove(card);
        playerBucket.remove(card);
        //Display();
    }

    private void Display() {
        lTrumpf.setText(gameBucket.DisplayStringByBucket(playerBucket, Statics.TRUMPF));
        lKreuz.setText(gameBucket.DisplayStringByBucket(playerBucket, Statics.KREUZ));
        lPik.setText(gameBucket.DisplayStringByBucket(playerBucket, Statics.PIK));
        lHerz.setText(gameBucket.DisplayStringByBucket(playerBucket, Statics.HERZ));
        lKaro.setText(gameBucket.DisplayStringByBucket(playerBucket, Statics.KARO));
    }

    public void openAssistWindow() {
        frame = new JFrame();
        JPanel panel = new JPanel(new GridLayout(1, 5));
        lTrumpf = new JLabel();
        lKreuz = new JLabel();
        lPik = new JLabel();
        lHerz = new JLabel();
        lKaro = new JLabel();
        panel.add(lTrumpf);
        panel.add(lKreuz);
        panel.add(lPik);
        panel.add(lHerz);
        panel.add(lKaro);
        frame.add(panel);
        frame.pack();
        //Display();
        frame.setVisible(true);

    }

    public void close() {
        if (frame != null) {
            frame.dispose();
        }
    }
}
