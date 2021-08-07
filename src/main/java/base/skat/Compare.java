package base.skat;

import base.BaseCard;
import base.Statics;

public class Compare {

    public static BaseCard karo (BaseCard first, BaseCard second){
        return farbSolo(first,second);
    }

    public static BaseCard herz (BaseCard first, BaseCard second){
        return farbSolo(first,second);
    }

    public static BaseCard pik (BaseCard first, BaseCard second){
        return farbSolo(first,second);
    }

    public static BaseCard kreuz (BaseCard first, BaseCard second){
        return farbSolo(first,second);
    }

    public static BaseCard farbSolo(BaseCard first, BaseCard second) {
        if (first.trump && !second.trump) {
            return first;
        } else if (!first.trump && second.trump) {
            return second;
        } else if (!first.trump) {
            if (!first.suit.equals(second.suit)) {
                return first;
            } else {
                return getWinnerForSameColor(first, second);
            }
        } else {
            switch (first.kind) {
                case Statics.SIEBEN:
                    return second;
                case Statics.ACHT:
                    if (second.kind.equals(Statics.SIEBEN)) {
                        return first;
                    } else {
                        return second;
                    }
                case Statics.NEUN:
                    if (second.kind.equals(Statics.SIEBEN) ||
                            second.kind.equals(Statics.ACHT)) {
                        return first;
                    } else {
                        return second;
                    }
                case Statics.DAME:
                    if (second.kind.equals(Statics.KOENIG) ||
                            second.kind.equals(Statics.ZEHN) ||
                            second.kind.equals(Statics.ASS) ||
                            second.kind.equals(Statics.BUBE)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.KOENIG:
                    if (second.kind.equals(Statics.ZEHN) ||
                            second.kind.equals(Statics.ASS) ||
                            second.kind.equals(Statics.BUBE)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.ZEHN:
                    if (second.kind.equals(Statics.ASS) ||
                            second.kind.equals(Statics.BUBE)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.ASS:
                    if (second.kind.equals(Statics.BUBE)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.BUBE:
                    if (second.kind.equals(Statics.BUBE)) {
                        return getWinnerBuben(first, second);
                    } else {
                        return first;
                    }
            }
        }
        return first;
    }

    public static BaseCard grand (BaseCard first, BaseCard second){
        if(first.kind.equals(Statics.BUBE)&&second.kind.equals(Statics.BUBE)){
            return getWinnerBuben(first,second);
        }
        else if(first.kind.equals(Statics.BUBE)){
            return first;
        }
        else if(second.kind.equals(Statics.BUBE)){
            return second;
        }
        else{
            return getWinnerForSameColor(first, second);
        }
    }

    public static BaseCard nullspiel(BaseCard first, BaseCard second){
        if(first.suit.equals(second.suit)) {
            switch (first.kind) {
                case Statics.SIEBEN:
                    return second;
                case Statics.ACHT:
                    if (second.kind.equals(Statics.SIEBEN)) {
                        return first;
                    } else {
                        return second;
                    }
                case Statics.NEUN:
                    if (second.kind.equals(Statics.SIEBEN) ||
                            second.kind.equals(Statics.ACHT)) {
                        return first;
                    } else {
                        return second;
                    }
                case Statics.ZEHN:
                    if (second.kind.equals(Statics.SIEBEN) ||
                            second.kind.equals(Statics.ACHT) ||
                            second.kind.equals(Statics.NEUN)) {
                        return first;
                    } else {
                        return second;
                    }
                case Statics.BUBE:
                    if (second.kind.equals(Statics.DAME) ||
                            second.kind.equals(Statics.KOENIG) ||
                            second.kind.equals(Statics.ASS)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.DAME:
                    if (second.kind.equals(Statics.KOENIG) ||
                            second.kind.equals(Statics.ASS)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.KOENIG:
                    if (second.kind.equals(Statics.ASS)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.ASS:
                    return first;
            }
        }
        return first;
    }

    private static BaseCard getWinnerForSameColor(BaseCard first, BaseCard second) {
        if(first.suit.equals(second.suit)){
            switch (first.kind) {
                case Statics.SIEBEN:
                    return second;
                case Statics.ACHT:
                    if (second.kind.equals(Statics.SIEBEN)) {
                        return first;
                    } else {
                        return second;
                    }
                case Statics.NEUN:
                    if (second.kind.equals(Statics.SIEBEN) ||
                            second.kind.equals(Statics.ACHT)) {
                        return first;
                    } else {
                        return second;
                    }
                case Statics.DAME:
                    if (second.kind.equals(Statics.KOENIG) ||
                            second.kind.equals(Statics.ZEHN) ||
                            second.kind.equals(Statics.ASS)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.KOENIG:
                    if (second.kind.equals(Statics.ZEHN) ||
                            second.kind.equals(Statics.ASS)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.ZEHN:
                    if (second.kind.equals(Statics.ASS)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.ASS:
                    return first;
            }
        }
        return first;
    }

    private static BaseCard getWinnerBuben(BaseCard first, BaseCard second) {
        switch (first.suit) {
            case Statics.KREUZ:
                return first;
            case Statics.PIK:
                if (second.suit.equals(Statics.KREUZ)) {
                    return second;
                } else {
                    return first;
                }
            case Statics.HERZ:
                if (second.suit.equals(Statics.KARO)) {
                    return first;
                } else {
                    return second;
                }
            case Statics.KARO:
                return second;
        }
        return first;
    }
}
