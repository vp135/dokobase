package base.game.skat;

import base.Statics;
import base.game.Card;

public class Compare {

    public static Card karo(Card first, Card second) {
        return farbSolo(first, second);
    }

    public static Card herz(Card first, Card second) {
        return farbSolo(first, second);
    }

    public static Card pik(Card first, Card second) {
        return farbSolo(first, second);
    }

    public static Card kreuz(Card first, Card second) {
        return farbSolo(first, second);
    }

    public static Card farbSolo(Card first, Card second) {
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

    public static Card grand(Card first, Card second) {
        if (first.kind.equals(Statics.BUBE) && second.kind.equals(Statics.BUBE)) {
            return getWinnerBuben(first, second);
        } else if (first.kind.equals(Statics.BUBE)) {
            return first;
        } else if (second.kind.equals(Statics.BUBE)) {
            return second;
        } else {
            return getWinnerForSameColor(first, second);
        }
    }

    public static Card nullspiel(Card first, Card second) {
        if (first.suit.equals(second.suit)) {
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

    private static Card getWinnerForSameColor(Card first, Card second) {
        if (first.suit.equals(second.suit)) {
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

    private static Card getWinnerBuben(Card first, Card second) {
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
