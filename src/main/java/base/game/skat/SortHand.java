package base.game.skat;

import base.Statics;
import base.game.Card;
import base.messages.skat.MessageGameSelected;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SortHand {

    public static List<Card> sort(List<Card> list, MessageGameSelected.GAMES order) {
        switch (order) {
            case Karo:
                return sortKaro(list);
            case Herz:
                return sortHerz(list);
            case Pik:
                return sortPik(list);
            case Kreuz:
                return sortKreuz(list);
            case Null:
                return sortNull(list);
            case Grand:
                return sortGrand(list);
            default:
                return list;
        }
    }

    public static List<Card> sortGrand(List<Card> list_orig) {
        return sortKreuz(list_orig);
    }

    public static List<Card> sortKreuz(List<Card> list_orig) {
        List<Card> list = new ArrayList<>(list_orig);
        List<Card> outList = new ArrayList<>();
        sortBuben(list, outList);
        sortColor(list, outList, Statics.KREUZ);
        sortColor(list, outList, Statics.PIK);
        sortColor(list, outList, Statics.HERZ);
        sortColor(list, outList, Statics.KARO);
        return outList;
    }

    public static List<Card> sortPik(List<Card> list_orig) {
        List<Card> list = new ArrayList<>(list_orig);
        List<Card> outList = new ArrayList<>();
        sortBuben(list, outList);
        sortColor(list, outList, Statics.PIK);
        sortColor(list, outList, Statics.KREUZ);
        sortColor(list, outList, Statics.HERZ);
        sortColor(list, outList, Statics.KARO);
        return outList;
    }

    public static List<Card> sortHerz(List<Card> list_orig) {
        List<Card> list = new ArrayList<>(list_orig);
        List<Card> outList = new ArrayList<>();
        sortBuben(list, outList);
        sortColor(list, outList, Statics.HERZ);
        sortColor(list, outList, Statics.KREUZ);
        sortColor(list, outList, Statics.PIK);
        sortColor(list, outList, Statics.KARO);
        return outList;
    }

    public static List<Card> sortKaro(List<Card> list_orig) {
        List<Card> list = new ArrayList<>(list_orig);
        List<Card> outList = new ArrayList<>();
        sortBuben(list, outList);
        sortColor(list, outList, Statics.KARO);
        sortColor(list, outList, Statics.KREUZ);
        sortColor(list, outList, Statics.PIK);
        sortColor(list, outList, Statics.HERZ);
        return outList;
    }

    public static List<Card> sortNull(List<Card> list_orig) {
        List<Card> list = new ArrayList<>(list_orig);
        List<Card> outList = new ArrayList<>();
        sortColorNull(list, outList, Statics.KREUZ);
        sortColorNull(list, outList, Statics.PIK);
        sortColorNull(list, outList, Statics.HERZ);
        sortColorNull(list, outList, Statics.KARO);
        return outList;
    }

    //helper functions


    private static void sortBuben(List<Card> list, List<Card> returnList) {
        List<Card> bubenList = list.stream().filter(c -> c.kind.equals(Statics.BUBE)).collect(Collectors.toList());
        Optional<Card> card = bubenList.stream().filter(c -> c.suit.equals(Statics.KREUZ)).findFirst();
        if (card.isPresent()) {
            returnList.add(card.get());
            bubenList.remove(card.get());
            list.remove(card.get());
        }
        card = bubenList.stream().filter(c -> c.suit.equals(Statics.PIK)).findFirst();
        if (card.isPresent()) {
            returnList.add(card.get());
            bubenList.remove(card.get());
            list.remove(card.get());
        }
        card = bubenList.stream().filter(c -> c.suit.equals(Statics.HERZ)).findFirst();
        if (card.isPresent()) {
            returnList.add(card.get());
            bubenList.remove(card.get());
            list.remove(card.get());
        }
        card = bubenList.stream().filter(c -> c.suit.equals(Statics.KARO)).findFirst();
        if (card.isPresent()) {
            returnList.add(card.get());
            bubenList.remove(card.get());
            list.remove(card.get());
        }
    }


    private static void sortColor(List<Card> list, List<Card> outList, String farbe) {
        List<String> order = Arrays.asList(
                Statics.ASS,
                Statics.ZEHN,
                Statics.KOENIG,
                Statics.DAME,
                Statics.NEUN,
                Statics.ACHT,
                Statics.SIEBEN);
        List<Card> farbList = list.stream().filter(c -> c.suit.equals(farbe)).collect(Collectors.toList());
        order.forEach(pos -> {
            Optional<Card> card = farbList.stream().filter(c -> c.kind.equals(pos)).findFirst();
            if (card.isPresent()) {
                outList.add(card.get());
                farbList.remove(card.get());
                list.remove(card.get());
            }
        });
    }

    private static void sortColorNull(List<Card> list, List<Card> outList, String farbe) {
        List<String> order = Arrays.asList(
                Statics.ASS,
                Statics.KOENIG,
                Statics.DAME,
                Statics.BUBE,
                Statics.ZEHN,
                Statics.NEUN,
                Statics.ACHT,
                Statics.SIEBEN);
        List<Card> farbList = list.stream().filter(c -> c.suit.equals(farbe)).collect(Collectors.toList());
        order.forEach(pos -> {
            Optional<Card> card = farbList.stream().filter(c -> c.kind.equals(pos)).findFirst();
            if (card.isPresent()) {
                outList.add(card.get());
                farbList.remove(card.get());
                list.remove(card.get());
            }
        });
    }


}
