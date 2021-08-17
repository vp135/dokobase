package base.doko;

import base.Card;
import base.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static base.doko.messages.MessageGameSelected.GAMES;

public class SortHand {

    public static List<Card> sort(List<Card> list, GAMES selectedGame, boolean schweinExists){
        switch (selectedGame){
            case NORMAL:{
                return sortNormal(list,schweinExists);
            }
            case DAMEN:{
                return sortDamenSolo(list);
            }
            case BUBEN:{
                return sortBubenSolo(list);
            }
            case BUBENDAMEN:{
                return sortBubenDamenSolo(list);
            }
            case FLEISCHLOS:{
                return sortFleischlos(list);
            }
            case ARMUT:{
                return sortArmut(list,schweinExists);
            }
            case KREUZ:{
                return sortKreuz(list);
            }
            case PIK:{
                return sortPik(list);
            }
            case HERZ:{
                return sortHerz(list);
            }
            case KARO:{
                return sortKaro(list,schweinExists);
            }
            default:{
                return list;
            }
        }
    }

    public static List<Card> sortNormal(List<Card> list_orig, boolean schweinExists){
        List<Card> list = new ArrayList<>(list_orig);
        List<Card> frc = new ArrayList<>();
        List<Card> fuchslist = list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList());
        if (fuchslist.size() > 1 || schweinExists) {
            fuchslist.forEach(p -> {
                frc.add(p);
                list.remove(p);
            });
        }
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        return frc;
    }

    public static List<Card> sortBubenSolo(List<Card> list_orig){
        List<Card> list = new ArrayList<>(list_orig);
        List<Card> frc = new ArrayList<>();

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });


        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });



        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        return frc;
    }

    public static List<Card> sortDamenSolo(List<Card> list_orig){
        List<Card> list = new ArrayList<>(list_orig);
        List<Card> frc = new ArrayList<>();

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });


        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });



        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        return frc;
    }

    public static List<Card> sortBubenDamenSolo(List<Card> list_orig){
        List<Card> list = new ArrayList<>(list_orig);
        List<Card> frc = new ArrayList<>();

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });


        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });



        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        return frc;
    }

    public static List<Card> sortFleischlos(List<Card> list_orig){
        List<Card> list = new ArrayList<>(list_orig);
        List<Card> frc = new ArrayList<>();

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        return frc;
    }

    public static List<Card> sortKreuz(List<Card> list_orig){
        List<Card> list = new ArrayList<>(list_orig);
        List<Card> frc = new ArrayList<>();

        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });



        return frc;
    }

    public static List<Card> sortPik(List<Card> list_orig){
        List<Card> list = new ArrayList<>(list_orig);
        List<Card> frc = new ArrayList<>();

        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });


        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        return frc;
    }

    public static List<Card> sortHerz(List<Card> list_orig){
        List<Card> list = new ArrayList<>(list_orig);
        List<Card> frc = new ArrayList<>();
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.HERZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });



        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KREUZ) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.PIK) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        return frc;
    }

    public static List<Card> sortKaro(List<Card> hand, boolean schweinExists) {
        return sortNormal(hand, schweinExists);
    }

    public static List<Card> sortArmut(List<Card> hand, boolean schweinExists) {
        return sortNormal(hand, schweinExists);
    }
}
