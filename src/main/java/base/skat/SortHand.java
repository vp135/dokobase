package base.skat;

import base.BaseCard;
import base.Statics;
import base.skat.messages.GameSelected;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SortHand {

    public static List<BaseCard> sort(List<BaseCard> list, GameSelected.GAMES order){
        switch (order){
            case Karo:{
                return sortKaro(list);
            }
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

    public static List<BaseCard> sortGrand(List<BaseCard> list_orig) {
        return sortKreuz(list_orig);
    }

    public static List<BaseCard> sortKreuz(List<BaseCard> list_orig) {
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> outList = new ArrayList<>();
        sortBuben(list, outList);
        sortColor(list,outList,Statics.KREUZ);
        sortColor(list,outList,Statics.PIK);
        sortColor(list,outList,Statics.HERZ);
        sortColor(list,outList,Statics.KARO);
        return outList;
    }

    public static List<BaseCard> sortPik(List<BaseCard> list_orig) {
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> outList = new ArrayList<>();
        sortBuben(list, outList);
        sortColor(list,outList,Statics.PIK);
        sortColor(list,outList,Statics.KREUZ);
        sortColor(list,outList,Statics.HERZ);
        sortColor(list,outList,Statics.KARO);
        return outList;
    }

    public static List<BaseCard> sortHerz(List<BaseCard> list_orig) {
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> outList = new ArrayList<>();
        sortBuben(list, outList);
        sortColor(list,outList,Statics.HERZ);
        sortColor(list,outList,Statics.KREUZ);
        sortColor(list,outList,Statics.PIK);
        sortColor(list,outList,Statics.KARO);
        return outList;
    }

    public static List<BaseCard> sortKaro(List<BaseCard> list_orig) {
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> outList = new ArrayList<>();
        sortBuben(list, outList);
        sortColor(list,outList,Statics.KARO);
        sortColor(list,outList,Statics.KREUZ);
        sortColor(list,outList,Statics.PIK);
        sortColor(list,outList,Statics.HERZ);
        return outList;
    }

    public static List<BaseCard> sortNull(List<BaseCard> list_orig) {
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> outList = new ArrayList<>();
        sortColorNull(list,outList,Statics.KREUZ);
        sortColorNull(list,outList,Statics.PIK);
        sortColorNull(list,outList,Statics.HERZ);
        sortColorNull(list,outList,Statics.KARO);
        return outList;
    }

    //helper functions


    private static void sortBuben(List<BaseCard> list, List<BaseCard> returnList) {
        List<BaseCard> bubenList = list.stream().filter(c-> c.value.equals(Statics.BUBE)).collect(Collectors.toList());
        Optional<BaseCard> card = bubenList.stream().filter(c->c.farbe.equals(Statics.KREUZ)).findFirst();
        if(card.isPresent()){
            returnList.add(card.get());
            bubenList.remove(card.get());
            list.remove(card.get());
        }
        card = bubenList.stream().filter(c->c.farbe.equals(Statics.PIK)).findFirst();
        if(card.isPresent()){
            returnList.add(card.get());
            bubenList.remove(card.get());
            list.remove(card.get());
        }
        card = bubenList.stream().filter(c->c.farbe.equals(Statics.HERZ)).findFirst();
        if(card.isPresent()){
            returnList.add(card.get());
            bubenList.remove(card.get());
            list.remove(card.get());
        }
        card = bubenList.stream().filter(c->c.farbe.equals(Statics.KARO)).findFirst();
        if(card.isPresent()){
            returnList.add(card.get());
            bubenList.remove(card.get());
            list.remove(card.get());
        }
    }


    private static void sortColor(List<BaseCard> list, List<BaseCard> outList, String farbe) {
        List<String> order = Arrays.asList(
                Statics.ASS,
                Statics.ZEHN,
                Statics.KOENIG,
                Statics.DAME,
                Statics.NEUN,
                Statics.ACHT,
                Statics.SIEBEN);
        List<BaseCard> farbList = list.stream().filter(c-> c.farbe.equals(farbe)).collect(Collectors.toList());
        order.forEach(pos->{
            Optional<BaseCard> card = farbList.stream().filter(c->c.value.equals(pos)).findFirst();
            if(card.isPresent()) {
                outList.add(card.get());
                farbList.remove(card.get());
                list.remove(card.get());
            }
        });
    }

    private static void sortColorNull(List<BaseCard> list, List<BaseCard> outList, String farbe) {
        List<String> order = Arrays.asList(
                Statics.ASS,
                Statics.KOENIG,
                Statics.DAME,
                Statics.BUBE,
                Statics.ZEHN,
                Statics.NEUN,
                Statics.ACHT,
                Statics.SIEBEN);
        List<BaseCard> farbList = list.stream().filter(c-> c.farbe.equals(farbe)).collect(Collectors.toList());
        order.forEach(pos->{
            Optional<BaseCard> card = farbList.stream().filter(c->c.value.equals(pos)).findFirst();
            if(card.isPresent()) {
                outList.add(card.get());
                farbList.remove(card.get());
                list.remove(card.get());
            }
        });
    }


}
