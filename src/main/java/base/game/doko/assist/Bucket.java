package base.game.doko.assist;

import base.Statics;
import base.game.Card;
import base.game.doko.Compare;
import base.game.doko.DokoCards;
import base.messages.doko.MessageGameSelected;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Bucket {
    public List<Card> trumpf;
    public List<Card> kreuz;
    public List<Card> pik;
    public List<Card> herz;
    public List<Card> karo;


    public Bucket() {

    }

    public Bucket(List<Card> cards, MessageGameSelected.GAMES game, boolean schwein) {
        cards.forEach(card -> card.trump = DokoCards.isTrumpf(card, game));
        List<Card> localCardList = new ArrayList<>(cards);
        trumpf = localCardList.stream().filter(card -> card.trump).collect(Collectors.toList());
        trumpf.sort(Compare.getComparer(game, schwein).reversed());
        trumpf.forEach(localCardList::remove);
        kreuz = localCardList.stream().filter(card -> card.suit.equals(Statics.KREUZ)).collect(Collectors.toList());
        kreuz.sort(Compare.getComparer(game, schwein).reversed());
        kreuz.forEach(localCardList::remove);
        pik = localCardList.stream().filter(card -> card.suit.equals(Statics.PIK)).collect(Collectors.toList());
        pik.sort(Compare.getComparer(game, schwein).reversed());
        pik.forEach(localCardList::remove);
        herz = localCardList.stream().filter(card -> card.suit.equals(Statics.HERZ)).collect(Collectors.toList());
        herz.sort(Compare.getComparer(game, schwein).reversed());
        herz.forEach(localCardList::remove);
        karo = localCardList.stream().filter(card -> card.suit.equals(Statics.KARO)).collect(Collectors.toList());
        karo.sort(Compare.getComparer(game, schwein).reversed());
        karo.forEach(localCardList::remove);
    }

    public List<Card> getListBySuit(String suit) {
        switch (suit) {
            case (Statics.KREUZ):
                return kreuz;
            case (Statics.PIK):
                return pik;
            case (Statics.HERZ):
                return herz;
            case (Statics.KARO):
                return karo;
            default:
                return new ArrayList<>();
        }
    }


    public String calcRisk(int nmb) {
        Random r = new Random(System.currentTimeMillis());
        int total = 100000;
        int miss = 0;
        for (int i = 0; i < total; i++) {
            int[] positions = new int[nmb];
            for (int j = 0; j < kreuz.size(); j++) {
                positions[r.nextInt(nmb)]++;
            }
            if (Arrays.stream(positions).anyMatch(p -> p == 0)) {
                miss++;
            }
        }
        return ("Anzahl unbekannte Kreuz: " + kreuz.size() + "\n" +
                "miss(" + miss + ")/total(" + total + ")=" + (double) miss / (double) total);
    }


    public static Bucket substract(Bucket b1, Bucket b2) {
        Bucket result = new Bucket();
        result.trumpf = new ArrayList<>(b1.trumpf);
        result.trumpf.removeAll(b2.trumpf);

        result.kreuz = new ArrayList<>(b1.kreuz);
        result.kreuz.removeAll(b2.kreuz);

        result.pik = new ArrayList<>(b1.pik);
        result.pik.removeAll(b2.pik);

        result.herz = new ArrayList<>(b1.herz);
        result.herz.removeAll(b2.herz);

        result.karo = new ArrayList<>(b1.karo);
        result.karo.removeAll(b2.karo);
        return result;
    }

    public String DisplayStringByBucket(Bucket otherBucket, String bucket) {
        switch (bucket) {
            case Statics.TRUMPF:
                return createHtmlString(this.trumpf, otherBucket.trumpf);
            case Statics.KREUZ:
                return createHtmlString(this.kreuz, otherBucket.kreuz);
            case Statics.PIK:
                return createHtmlString(this.pik, otherBucket.pik);
            case Statics.HERZ:
                return createHtmlString(this.herz, otherBucket.herz);
            case Statics.KARO:
                return createHtmlString(this.karo, otherBucket.karo);
            default:
                return "";
        }
    }

    private String createHtmlString(List<Card> trumpf, List<Card> trumpf1) {
        StringBuilder builder = new StringBuilder("<html>");
        for (Card card : trumpf) {
            if (trumpf1.contains(card)) {
                builder.append(addColor(card.toString(), "red")).append("<br>");
            } else {
                builder.append(card.toString()).append("<br>");
            }
        }
        builder.append("</html>");

        return builder.toString();
    }


    private static String addColor(String s, String color) {
        return "<font color=\"" +
                color +
                "\">" +
                s +
                "</font>";
    }

    @Override
    public String toString() {
        return (String.format("buckets:\nTrumpf:%s\nKreuz:%s\nPik:%s\nHerz:%s\nKaro:%s\n",
                trumpf.size(),
                kreuz.size(),
                pik.size(),
                herz.size(),
                karo.size()));
    }

    public void remove(Card card) {
        if (card.trump) {
            trumpf.remove(card);
        }
        switch (card.suit) {
            case (Statics.KREUZ):
                kreuz.remove(card);
                break;
            case (Statics.PIK):
                pik.remove(card);
                break;
            case (Statics.HERZ):
                herz.remove(card);
                break;
            case (Statics.KARO):
                karo.remove(card);
                break;
        }
    }

    public boolean contains(Card card) {
        List<Card> allCards = new ArrayList<>();
        allCards.addAll(trumpf);
        allCards.addAll(kreuz);
        allCards.addAll(pik);
        allCards.addAll(herz);
        allCards.addAll(karo);
        for (Card c : allCards) {
            if (c.suit.equals(card.suit) && c.kind.equals(card.kind)) {
                return true;
            }
        }
        return false;
    }
}
