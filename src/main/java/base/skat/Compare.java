package base.skat;

import base.Statics;

public class Compare {

    public static Card karo (Card first, Card second){
        return farbSolo(first,second);
    }

    public static Card herz (Card first, Card second){
        return farbSolo(first,second);
    }

    public static Card pik (Card first, Card second){
        return farbSolo(first,second);
    }

    public static Card kreuz (Card first, Card second){
        return farbSolo(first,second);
    }

    public static Card farbSolo(Card first, Card second) {
        if (first.trumpf && !second.trumpf) {
            return first;
        } else if (!first.trumpf && second.trumpf) {
            return second;
        } else if (!first.trumpf) {
            if (!first.farbe.equals(second.farbe)) {
                return first;
            } else {
                return getWinnerForSameColor(first, second);
            }
        } else {
            switch (first.value) {
                case Statics.SIEBEN:
                    return second;
                case Statics.ACHT:
                    if (second.value.equals(Statics.SIEBEN)) {
                        return first;
                    } else {
                        return second;
                    }
                case Statics.NEUN:
                    if (second.value.equals(Statics.SIEBEN) ||
                            second.value.equals(Statics.ACHT)) {
                        return first;
                    } else {
                        return second;
                    }
                case Statics.DAME:
                    if (second.value.equals(Statics.KOENIG) ||
                            second.value.equals(Statics.ZEHN) ||
                            second.value.equals(Statics.ASS) ||
                            second.value.equals(Statics.BUBE)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.KOENIG:
                    if (second.value.equals(Statics.ZEHN) ||
                            second.value.equals(Statics.ASS) ||
                            second.value.equals(Statics.BUBE)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.ZEHN:
                    if (second.value.equals(Statics.ASS) ||
                            second.value.equals(Statics.BUBE)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.ASS:
                    if (second.value.equals(Statics.BUBE)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.BUBE:
                    if (second.value.equals(Statics.BUBE)) {
                        return getWinnerBuben(first, second);
                    } else {
                        return first;
                    }
            }
        }
        return first;
    }

    public static Card grand (Card first, Card second){
        if(first.value.equals(Statics.BUBE)&&second.value.equals(Statics.BUBE)){
            return getWinnerBuben(first,second);
        }
        else if(first.value.equals(Statics.BUBE)){
            return first;
        }
        else if(second.value.equals(Statics.BUBE)){
            return second;
        }
        else{
            return getWinnerForSameColor(first, second);
        }
    }

    public static Card nullspiel(Card first, Card second){
        if(first.farbe.equals(second.farbe)) {
            switch (first.value) {
                case Statics.SIEBEN:
                    return second;
                case Statics.ACHT:
                    if (second.value.equals(Statics.SIEBEN)) {
                        return first;
                    } else {
                        return second;
                    }
                case Statics.NEUN:
                    if (second.value.equals(Statics.SIEBEN) ||
                            second.value.equals(Statics.ACHT)) {
                        return first;
                    } else {
                        return second;
                    }
                case Statics.ZEHN:
                    if (second.value.equals(Statics.SIEBEN) ||
                            second.value.equals(Statics.ACHT) ||
                            second.value.equals(Statics.NEUN)) {
                        return first;
                    } else {
                        return second;
                    }
                case Statics.BUBE:
                    if (second.value.equals(Statics.DAME) ||
                            second.value.equals(Statics.KOENIG) ||
                            second.value.equals(Statics.ASS)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.DAME:
                    if (second.value.equals(Statics.KOENIG) ||
                            second.value.equals(Statics.ASS)) {
                        return second;
                    } else {
                        return first;
                    }
                case Statics.KOENIG:
                    if (second.value.equals(Statics.ASS)) {
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

    private static Card getWinnerForSameColor(Card first, Card second) {
        switch (first.value) {
            case Statics.SIEBEN:
                return second;
            case Statics.ACHT:
                if (second.value.equals(Statics.SIEBEN)) {
                    return first;
                } else {
                    return second;
                }
            case Statics.NEUN:
                if (second.value.equals(Statics.SIEBEN) ||
                        second.value.equals(Statics.ACHT)) {
                    return first;
                } else {
                    return second;
                }
            case Statics.DAME:
                if (second.value.equals(Statics.KOENIG) ||
                        second.value.equals(Statics.ZEHN) ||
                        second.value.equals(Statics.ASS)) {
                    return second;
                } else {
                    return first;
                }
            case Statics.KOENIG:
                if (second.value.equals(Statics.ZEHN) ||
                        second.value.equals(Statics.ASS)) {
                    return second;
                } else {
                    return first;
                }
            case Statics.ZEHN:
                if (second.value.equals(Statics.ASS)) {
                    return second;
                } else {
                    return first;
                }
            case Statics.ASS:
                return first;
        }
        return first;
    }

    private static Card getWinnerBuben(Card first, Card second) {
        switch (first.farbe) {
            case Statics.KREUZ:
                return first;
            case Statics.PIK:
                if (second.farbe.equals(Statics.KREUZ)) {
                    return second;
                } else {
                    return first;
                }
            case Statics.HERZ:
                if (second.farbe.equals(Statics.KARO)) {
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
