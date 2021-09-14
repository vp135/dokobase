package base.game.doko;

import base.Statics;
import base.game.Card;
import base.messages.doko.MessageGameSelected;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DokoCards {


    public static final List<Card> ALL_CARDS = new ArrayList<>(Arrays.asList(
            new Card(Statics.ZEHN, Statics.KREUZ, 0, 10),
            new Card(Statics.ZEHN, Statics.KREUZ, 1, 10),
            new Card(Statics.BUBE, Statics.KREUZ, 2, 2),
            new Card(Statics.BUBE, Statics.KREUZ, 3, 2),
            new Card(Statics.DAME, Statics.KREUZ, 4, 3),
            new Card(Statics.DAME, Statics.KREUZ, 5, 3),
            new Card(Statics.KOENIG, Statics.KREUZ, 6, 4),
            new Card(Statics.KOENIG, Statics.KREUZ, 7, 4),
            new Card(Statics.ASS, Statics.KREUZ, 8, 11),
            new Card(Statics.ASS, Statics.KREUZ, 9, 11),

            new Card(Statics.ZEHN, Statics.PIK, 10, 10),
            new Card(Statics.ZEHN, Statics.PIK, 11, 10),
            new Card(Statics.BUBE, Statics.PIK, 12, 2),
            new Card(Statics.BUBE, Statics.PIK, 13, 2),
            new Card(Statics.DAME, Statics.PIK, 14, 3),
            new Card(Statics.DAME, Statics.PIK, 15, 3),
            new Card(Statics.KOENIG, Statics.PIK, 16, 4),
            new Card(Statics.KOENIG, Statics.PIK, 17, 4),
            new Card(Statics.ASS, Statics.PIK, 18, 11),
            new Card(Statics.ASS, Statics.PIK, 19, 11),

            new Card(Statics.ZEHN, Statics.HERZ, 20, 10),
            new Card(Statics.ZEHN, Statics.HERZ, 21, 10),
            new Card(Statics.BUBE, Statics.HERZ, 22, 2),
            new Card(Statics.BUBE, Statics.HERZ, 23, 2),
            new Card(Statics.DAME, Statics.HERZ, 24, 3),
            new Card(Statics.DAME, Statics.HERZ, 25, 3),
            new Card(Statics.KOENIG, Statics.HERZ, 26, 4),
            new Card(Statics.KOENIG, Statics.HERZ, 27, 4),
            new Card(Statics.ASS, Statics.HERZ, 28, 11),
            new Card(Statics.ASS, Statics.HERZ, 29, 11),

            new Card(Statics.ZEHN, Statics.KARO, 30, 10),
            new Card(Statics.ZEHN, Statics.KARO, 31, 10),
            new Card(Statics.BUBE, Statics.KARO, 32, 2),
            new Card(Statics.BUBE, Statics.KARO, 33, 2),
            new Card(Statics.DAME, Statics.KARO, 34, 3),
            new Card(Statics.DAME, Statics.KARO, 35, 3),
            new Card(Statics.KOENIG, Statics.KARO, 36, 4),
            new Card(Statics.KOENIG, Statics.KARO, 37, 4),
            new Card(Statics.ASS, Statics.KARO, 38, 11),
            new Card(Statics.ASS, Statics.KARO, 39, 11)
    ));

    public static boolean isTrumpf(Card card, Object object) {
        MessageGameSelected.GAMES gametype = (MessageGameSelected.GAMES) object;
        switch (gametype) {
            case NORMAL:
            case HOCHZEIT:
            case ARMUT:
            case KARO:
                return ((card.suit.equals(Statics.KARO)
                        || card.kind.equals(Statics.BUBE)
                        || card.kind.equals(Statics.DAME)
                        || (card.kind.equals(Statics.ZEHN) && card.suit.equals(Statics.HERZ))));

            case DAMEN:
                return (card.kind.equals(Statics.DAME));
            case BUBEN:
                return (card.kind.equals(Statics.BUBE));
            case BUBENDAMEN:
                return (card.kind.equals(Statics.DAME)
                        || card.kind.equals(Statics.BUBE));
            case KREUZ:
                return ((card.suit.equals(Statics.KREUZ)
                        || card.kind.equals(Statics.BUBE)
                        || card.kind.equals(Statics.DAME)
                        || (card.kind.equals(Statics.ZEHN) && card.suit.equals(Statics.HERZ))));
            case PIK:
                return ((card.suit.equals(Statics.PIK)
                        || card.kind.equals(Statics.BUBE)
                        || card.kind.equals(Statics.DAME)
                        || (card.kind.equals(Statics.ZEHN) && card.suit.equals(Statics.HERZ))));
            case HERZ:
                return ((card.suit.equals(Statics.HERZ)
                        || card.kind.equals(Statics.BUBE)
                        || card.kind.equals(Statics.DAME)));
            case FLEISCHLOS:
                return false;
        }
        return false;
    }


    public static Card randomCard() {
        Random r = new Random(System.currentTimeMillis());
        return ALL_CARDS.get(r.nextInt(40));
    }

}


