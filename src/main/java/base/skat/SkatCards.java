package base.skat;

import base.BaseCard;
import base.Statics;
import base.skat.messages.MessageGameSelected;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SkatCards {


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


    public static BaseCard randomCard(List<BaseCard> cards, Random r){
        return cards.get(r.nextInt(32));
    }


    public static List<BaseCard> createCardList(){
        List<BaseCard> cardList = new ArrayList<>();

        cardList.add(new BaseCard(Statics.SIEBEN,Statics.KREUZ));
        cardList.add(new BaseCard(Statics.ACHT,Statics.KREUZ));
        cardList.add(new BaseCard(Statics.NEUN,Statics.KREUZ));
        cardList.add(new BaseCard(Statics.ZEHN,Statics.KREUZ));
        cardList.add(new BaseCard(Statics.BUBE,Statics.KREUZ));
        cardList.add(new BaseCard(Statics.DAME,Statics.KREUZ));
        cardList.add(new BaseCard(Statics.KOENIG,Statics.KREUZ));
        cardList.add(new BaseCard(Statics.ASS,Statics.KREUZ));

        cardList.add(new BaseCard(Statics.SIEBEN,Statics.PIK));
        cardList.add(new BaseCard(Statics.ACHT,Statics.PIK));
        cardList.add(new BaseCard(Statics.NEUN,Statics.PIK));
        cardList.add(new BaseCard(Statics.ZEHN,Statics.PIK));
        cardList.add(new BaseCard(Statics.BUBE,Statics.PIK));
        cardList.add(new BaseCard(Statics.DAME,Statics.PIK));
        cardList.add(new BaseCard(Statics.KOENIG,Statics.PIK));
        cardList.add(new BaseCard(Statics.ASS,Statics.PIK));

        cardList.add(new BaseCard(Statics.SIEBEN,Statics.HERZ));
        cardList.add(new BaseCard(Statics.ACHT,Statics.HERZ));
        cardList.add(new BaseCard(Statics.NEUN,Statics.HERZ));
        cardList.add(new BaseCard(Statics.ZEHN,Statics.HERZ));
        cardList.add(new BaseCard(Statics.BUBE,Statics.HERZ));
        cardList.add(new BaseCard(Statics.DAME,Statics.HERZ));
        cardList.add(new BaseCard(Statics.KOENIG,Statics.HERZ));
        cardList.add(new BaseCard(Statics.ASS,Statics.HERZ));

        cardList.add(new BaseCard(Statics.SIEBEN,Statics.KARO));
        cardList.add(new BaseCard(Statics.ACHT,Statics.KARO));
        cardList.add(new BaseCard(Statics.NEUN,Statics.KARO));
        cardList.add(new BaseCard(Statics.ZEHN,Statics.KARO));
        cardList.add(new BaseCard(Statics.BUBE,Statics.KARO));
        cardList.add(new BaseCard(Statics.DAME,Statics.KARO));
        cardList.add(new BaseCard(Statics.KOENIG,Statics.KARO));
        cardList.add(new BaseCard(Statics.ASS,Statics.KARO));

        return cardList;
    }
}
