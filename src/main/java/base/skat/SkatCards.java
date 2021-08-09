package base.skat;

import base.BaseCard;
import base.Statics;
import base.skat.messages.MessageGameSelected;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SkatCards {

    public static List<BaseCard> ALL_CARDS = new ArrayList<>(Arrays.asList(
            new BaseCard(Statics.SIEBEN,Statics.KREUZ,0,0),
            new BaseCard(Statics.ACHT,Statics.KREUZ,1,0),
            new BaseCard(Statics.NEUN,Statics.KREUZ,2,0),
            new BaseCard(Statics.ZEHN,Statics.KREUZ,3,10),
            new BaseCard(Statics.BUBE,Statics.KREUZ,4,2),
            new BaseCard(Statics.DAME,Statics.KREUZ,5,3),
            new BaseCard(Statics.KOENIG,Statics.KREUZ,6,4),
            new BaseCard(Statics.ASS,Statics.KREUZ,7,11),

            new BaseCard(Statics.SIEBEN,Statics.PIK,0,0),
            new BaseCard(Statics.ACHT,Statics.PIK,1,0),
            new BaseCard(Statics.NEUN,Statics.PIK,2,0),
            new BaseCard(Statics.ZEHN,Statics.PIK,3,10),
            new BaseCard(Statics.BUBE,Statics.PIK,4,2),
            new BaseCard(Statics.DAME,Statics.PIK,5,3),
            new BaseCard(Statics.KOENIG,Statics.PIK,6,4),
            new BaseCard(Statics.ASS,Statics.PIK,7,11),

            new BaseCard(Statics.SIEBEN,Statics.HERZ,0,0),
            new BaseCard(Statics.ACHT,Statics.HERZ,1,0),
            new BaseCard(Statics.NEUN,Statics.HERZ,2,0),
            new BaseCard(Statics.ZEHN,Statics.HERZ,3,10),
            new BaseCard(Statics.BUBE,Statics.HERZ,4,2),
            new BaseCard(Statics.DAME,Statics.HERZ,5,3),
            new BaseCard(Statics.KOENIG,Statics.HERZ,6,4),
            new BaseCard(Statics.ASS,Statics.HERZ,7,11),

            new BaseCard(Statics.SIEBEN,Statics.KARO,0,0),
            new BaseCard(Statics.ACHT,Statics.KARO,1,0),
            new BaseCard(Statics.NEUN,Statics.KARO,2,0),
            new BaseCard(Statics.ZEHN,Statics.KARO,3,10),
            new BaseCard(Statics.BUBE,Statics.KARO,4,2),
            new BaseCard(Statics.DAME,Statics.KARO,5,3),
            new BaseCard(Statics.KOENIG,Statics.KARO,6,4),
            new BaseCard(Statics.ASS,Statics.KARO,7,11)
    ));


    public static boolean isTrumpf(BaseCard card, Object object) {
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


    public static BaseCard randomCard(){
        Random r = new Random(System.currentTimeMillis());
        return ALL_CARDS.get(r.nextInt(32));
    }



}
