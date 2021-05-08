package base.doko;

import base.BaseCard;
import base.Statics;
import base.doko.messages.GameSelected;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortHand {

    public static List<BaseCard> sort(List<BaseCard> list, String selectedGame, boolean schweinExists){
        switch (selectedGame){
            case GameSelected.NORMAL:{
                return sortNormal(list,schweinExists);
            }
            case GameSelected.DAMEN:{
                return sortDamenSolo(list);
            }
            case GameSelected.BUBEN:{
                return sortBubenSolo(list);
            }
            case GameSelected.BUBENDAMEN:{
                return sortBubenDamenSolo(list);
            }
            case GameSelected.FLEISCHLOS:{
                return sortFleischlos(list);
            }
            case GameSelected.ARMUT:{
                return sortArmut(list,schweinExists);
            }
            case GameSelected.KREUZ:{
                return sortKreuz(list);
            }
            case GameSelected.PIK:{
                return sortPik(list);
            }
            case GameSelected.HERZ:{
                return sortHerz(list);
            }
            case GameSelected.KARO:{
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
        List<BaseCard> fuchslist = list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList());
        if (fuchslist.size() > 1 || schweinExists) {
            fuchslist.forEach(p -> {
                frc.add(p);
                list.remove(p);
            });
        }
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        return frc;
    }

    public static List<BaseCard> sortBubenSolo(List<BaseCard> list_orig){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });


        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });



        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        return frc;
    }

    public static List<BaseCard> sortDamenSolo(List<BaseCard> list_orig){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });


        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });



        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        return frc;
    }

    public static List<BaseCard> sortBubenDamenSolo(List<BaseCard> list_orig){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });


        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });



        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        return frc;
    }

    public static List<BaseCard> sortFleischlos(List<BaseCard> list_orig){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        return frc;
    }

    public static List<BaseCard> sortKreuz(List<BaseCard> list_orig){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();

        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });



        return frc;
    }

    public static List<BaseCard> sortPik(List<BaseCard> list_orig){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();

        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });


        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        return frc;
    }

    public static List<BaseCard> sortHerz(List<BaseCard> list_orig){
        List<BaseCard> list = new ArrayList<>(list_orig);
        List<BaseCard> frc = new ArrayList<>();
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.DAME))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });

        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.BUBE))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.HERZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });



        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KREUZ) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.PIK) && p.value.equals(Statics.KOENIG))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ASS))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.ZEHN))
                .collect(Collectors.toList()).forEach(p -> {
            frc.add(p);
            list.remove(p);
        });
        list.stream().filter(p -> p.farbe.equals(Statics.KARO) && p.value.equals(Statics.KOENIG))
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
