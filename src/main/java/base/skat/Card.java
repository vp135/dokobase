package base.skat;

import base.BaseCard;
import base.Statics;
import base.skat.messages.GameSelected;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Card extends BaseCard {


    public Card(String value, String farbe){
        super(value,farbe,false);
    }


    protected boolean isTrumpf(Object object) {
        GameSelected.GAMES gametype = (GameSelected.GAMES) object;
        switch (gametype){
            case Grand:
                return this.value.equals(Statics.BUBE);
            case Kreuz:
                return this.value.equals(Statics.BUBE) || this.farbe.equalsIgnoreCase(Statics.KREUZ);
            case Pik:
                return this.value.equals(Statics.BUBE) || this.farbe.equalsIgnoreCase(Statics.PIK);
            case Herz:
                return this.value.equals(Statics.BUBE) || this.farbe.equalsIgnoreCase(Statics.HERZ);
            case Karo:
                return this.value.equals(Statics.BUBE) || this.farbe.equalsIgnoreCase(Statics.KARO);
            case Null:
                return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return farbe+ " "+ value;
    }


    public static Card randomCard(List<Card> cards, Random r){
        return cards.get(r.nextInt(32));
    }


    public static List<Card> createCardList(){
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Statics.SIEBEN,Statics.KREUZ));
        cardList.add(new Card(Statics.ACHT,Statics.KREUZ));
        cardList.add(new Card(Statics.NEUN,Statics.KREUZ));
        cardList.add(new Card(Statics.ZEHN,Statics.KREUZ));
        cardList.add(new Card(Statics.BUBE,Statics.KREUZ));
        cardList.add(new Card(Statics.DAME,Statics.KREUZ));
        cardList.add(new Card(Statics.KOENIG,Statics.KREUZ));
        cardList.add(new Card(Statics.ASS,Statics.KREUZ));

        cardList.add(new Card(Statics.SIEBEN,Statics.PIK));
        cardList.add(new Card(Statics.ACHT,Statics.PIK));
        cardList.add(new Card(Statics.NEUN,Statics.PIK));
        cardList.add(new Card(Statics.ZEHN,Statics.PIK));
        cardList.add(new Card(Statics.BUBE,Statics.PIK));
        cardList.add(new Card(Statics.DAME,Statics.PIK));
        cardList.add(new Card(Statics.KOENIG,Statics.PIK));
        cardList.add(new Card(Statics.ASS,Statics.PIK));

        cardList.add(new Card(Statics.SIEBEN,Statics.HERZ));
        cardList.add(new Card(Statics.ACHT,Statics.HERZ));
        cardList.add(new Card(Statics.NEUN,Statics.HERZ));
        cardList.add(new Card(Statics.ZEHN,Statics.HERZ));
        cardList.add(new Card(Statics.BUBE,Statics.HERZ));
        cardList.add(new Card(Statics.DAME,Statics.HERZ));
        cardList.add(new Card(Statics.KOENIG,Statics.HERZ));
        cardList.add(new Card(Statics.ASS,Statics.HERZ));

        cardList.add(new Card(Statics.SIEBEN,Statics.KARO));
        cardList.add(new Card(Statics.ACHT,Statics.KARO));
        cardList.add(new Card(Statics.NEUN,Statics.KARO));
        cardList.add(new Card(Statics.ZEHN,Statics.KARO));
        cardList.add(new Card(Statics.BUBE,Statics.KARO));
        cardList.add(new Card(Statics.DAME,Statics.KARO));
        cardList.add(new Card(Statics.KOENIG,Statics.KARO));
        cardList.add(new Card(Statics.ASS,Statics.KARO));

        return cardList;
    }
}
