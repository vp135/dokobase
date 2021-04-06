package base.skat;

import base.Statics;
import base.skat.messages.GameSelected;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Card {

    public int order;
    public String value;
    public String farbe;
    public boolean trumpf;

    public Card(String value, String farbe){
        this.value = value;
        this.farbe = farbe;
        //this.trumpf = isTrumpf();
    }

    public Card(String value, String farbe, boolean trumpf) {
        this.value = value;
        this.farbe = farbe;
        //this.trumpf = trumpf;
    }

    public static boolean isTrumpf(Card card, GameSelected.GAMES gametype) {
        switch (gametype){
            case GRAND:
                return card.value.equals(Statics.BUBE);
            case KREUZ:
                return card.value.equals(Statics.BUBE) || card.farbe.equalsIgnoreCase(Statics.KREUZ);
            case PIK:
                return card.value.equals(Statics.BUBE) || card.farbe.equalsIgnoreCase(Statics.PIK);
            case HERZ:
                return card.value.equals(Statics.BUBE) || card.farbe.equalsIgnoreCase(Statics.HERZ);
            case KARO:
                return card.value.equals(Statics.BUBE) || card.farbe.equalsIgnoreCase(Statics.KARO);
            case NULL:
                return false;
        }
        return false;
    }

    @Override
    public String toString() {
        return farbe+ " "+ value;
    }


    public static Card randomCard(List<Card> cards, Random r){
        return cards.get(r.nextInt(40));
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
