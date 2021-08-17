package base.skat;

import base.Card;
import base.Statics;
import base.skat.messages.MessageGameSelected;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SkatCards {

    public static List<Card> ALL_CARDS = new ArrayList<>(Arrays.asList(
            new Card(Statics.SIEBEN,Statics.KREUZ,0,0),
            new Card(Statics.ACHT,Statics.KREUZ,1,0),
            new Card(Statics.NEUN,Statics.KREUZ,2,0),
            new Card(Statics.ZEHN,Statics.KREUZ,3,10),
            new Card(Statics.BUBE,Statics.KREUZ,4,2),
            new Card(Statics.DAME,Statics.KREUZ,5,3),
            new Card(Statics.KOENIG,Statics.KREUZ,6,4),
            new Card(Statics.ASS,Statics.KREUZ,7,11),

            new Card(Statics.SIEBEN,Statics.PIK,8,0),
            new Card(Statics.ACHT,Statics.PIK,9,0),
            new Card(Statics.NEUN,Statics.PIK,10,0),
            new Card(Statics.ZEHN,Statics.PIK,11,10),
            new Card(Statics.BUBE,Statics.PIK,12,2),
            new Card(Statics.DAME,Statics.PIK,13,3),
            new Card(Statics.KOENIG,Statics.PIK,14,4),
            new Card(Statics.ASS,Statics.PIK,15,11),

            new Card(Statics.SIEBEN,Statics.HERZ,16,0),
            new Card(Statics.ACHT,Statics.HERZ,17,0),
            new Card(Statics.NEUN,Statics.HERZ,18,0),
            new Card(Statics.ZEHN,Statics.HERZ,19,10),
            new Card(Statics.BUBE,Statics.HERZ,20,2),
            new Card(Statics.DAME,Statics.HERZ,21,3),
            new Card(Statics.KOENIG,Statics.HERZ,22,4),
            new Card(Statics.ASS,Statics.HERZ,23,11),

            new Card(Statics.SIEBEN,Statics.KARO,24,0),
            new Card(Statics.ACHT,Statics.KARO,25,0),
            new Card(Statics.NEUN,Statics.KARO,26,0),
            new Card(Statics.ZEHN,Statics.KARO,27,10),
            new Card(Statics.BUBE,Statics.KARO,28,2),
            new Card(Statics.DAME,Statics.KARO,29,3),
            new Card(Statics.KOENIG,Statics.KARO,30,4),
            new Card(Statics.ASS,Statics.KARO,31,11)
    ));


    public static boolean isTrumpf(Card card, Object object) {
        MessageGameSelected.GAMES gametype = (MessageGameSelected.GAMES) object;
        switch (gametype){
            case Grand:
                return card.kind.equals(Statics.BUBE);
            case Kreuz:
                return card.kind.equals(Statics.BUBE) || card.suit.equalsIgnoreCase(Statics.KREUZ);
            case Pik:
                return card.kind.equals(Statics.BUBE) || card.suit.equalsIgnoreCase(Statics.PIK);
            case Herz:
                return card.kind.equals(Statics.BUBE) || card.suit.equalsIgnoreCase(Statics.HERZ);
            case Karo:
                return card.kind.equals(Statics.BUBE) || card.suit.equalsIgnoreCase(Statics.KARO);
            case Null:
                return false;
        }
        return false;
    }


    public static Card randomCard(){
        Random r = new Random(System.currentTimeMillis());
        return ALL_CARDS.get(r.nextInt(32));
    }



}
