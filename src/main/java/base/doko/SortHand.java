package base.doko;

import base.BaseCard;
import base.Statics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static base.doko.messages.MessageGameSelected.GAMES;

public class SortHand {

    public static List<BaseCard> sort(List<BaseCard> list, GAMES selectedGame, boolean schweinExists){
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

    public static List<BaseCard> sortNormal(List<BaseCard> list_orig, boolean schweinExists){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();
        List<BaseCard> fuchslist = list.stream().filter(p -> p.suit.equals(Statics.KARO) && p.kind.equals(Statics.ASS))
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

    public static List<BaseCard> sortBubenSolo(List<BaseCard> list_orig){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();

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

    public static List<BaseCard> sortDamenSolo(List<BaseCard> list_orig){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();

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

    public static List<BaseCard> sortBubenDamenSolo(List<BaseCard> list_orig){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();

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

    public static List<BaseCard> sortFleischlos(List<BaseCard> list_orig){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();

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

    public static List<BaseCard> sortKreuz(List<BaseCard> list_orig){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();

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

    public static List<BaseCard> sortPik(List<BaseCard> list_orig){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();

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

    public static List<BaseCard> sortHerz(List<BaseCard> list_orig){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();
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

    public static List<BaseCard> sortKaro(List<BaseCard> hand, boolean schweinExists) {
        return sortNormal(hand, schweinExists);
    }

    public static List<BaseCard> sortArmut(List<BaseCard> hand, boolean schweinExists) {
        return sortNormal(hand, schweinExists);
    }
}
