package base.doko;

import base.Statics;

import java.util.ArrayList;
import java.util.Arrays;
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
        this.trumpf = isTrumpf();
    }

    public Card(String value, String farbe, boolean trumpf) {
        this.value = value;
        this.farbe = farbe;
        this.trumpf = trumpf;
    }

    private boolean isTrumpf() {
        if(this.farbe.equals(Statics.KARO)){
            return true;
        }
        else if(this.value.equals(Statics.BUBE)||this.value.equals(Statics.DAME)){
            return true;
        }
        else {
            return this.farbe.equals(Statics.HERZ) && this.value.equals(Statics.ZEHN);
        }
    }

    @Override
    public String toString() {
        return farbe+ " "+ value;
    }


    public static Card randomCard(List<Card> cards, Random r){
        return cards.get(r.nextInt(40));
    }

    public static Card randomCard(Random r){
        return createCardList().get(r.nextInt(40));
    }

    public static Card randomCard(){
        Random r = new Random(System.currentTimeMillis());
        return createCardList().get(r.nextInt(40));
    }

    public static List<Card> createCardList(){
        List<Card> cardList = new ArrayList<>();

        cardList.add(new Card(Statics.ZEHN, Statics.KREUZ,false));
        cardList.add(new Card(Statics.ZEHN, Statics.KREUZ,false));
        cardList.add(new Card(Statics.BUBE, Statics.KREUZ,true));
        cardList.add(new Card(Statics.BUBE, Statics.KREUZ,true));
        cardList.add(new Card(Statics.DAME, Statics.KREUZ,true));
        cardList.add(new Card(Statics.DAME, Statics.KREUZ,true));
        cardList.add(new Card(Statics.KOENIG, Statics.KREUZ,false));
        cardList.add(new Card(Statics.KOENIG, Statics.KREUZ,false));
        cardList.add(new Card(Statics.ASS, Statics.KREUZ,false));
        cardList.add(new Card(Statics.ASS, Statics.KREUZ,false));

        cardList.add(new Card(Statics.ZEHN, Statics.PIK,false));
        cardList.add(new Card(Statics.ZEHN, Statics.PIK,false));
        cardList.add(new Card(Statics.BUBE, Statics.PIK,true));
        cardList.add(new Card(Statics.BUBE, Statics.PIK,true));
        cardList.add(new Card(Statics.DAME, Statics.PIK,true));
        cardList.add(new Card(Statics.DAME, Statics.PIK,true));
        cardList.add(new Card(Statics.KOENIG, Statics.PIK,false));
        cardList.add(new Card(Statics.KOENIG, Statics.PIK,false));
        cardList.add(new Card(Statics.ASS, Statics.PIK,false));
        cardList.add(new Card(Statics.ASS, Statics.PIK,false));

        cardList.add(new Card(Statics.ZEHN, Statics.HERZ, true));
        cardList.add(new Card(Statics.ZEHN, Statics.HERZ,true));
        cardList.add(new Card(Statics.BUBE, Statics.HERZ,true));
        cardList.add(new Card(Statics.BUBE, Statics.HERZ,true));
        cardList.add(new Card(Statics.DAME, Statics.HERZ,true));
        cardList.add(new Card(Statics.DAME, Statics.HERZ,true));
        cardList.add(new Card(Statics.KOENIG, Statics.HERZ,false));
        cardList.add(new Card(Statics.KOENIG, Statics.HERZ,false));
        cardList.add(new Card(Statics.ASS, Statics.HERZ,false));
        cardList.add(new Card(Statics.ASS, Statics.HERZ,false));

        cardList.add(new Card(Statics.ZEHN, Statics.KARO, true));
        cardList.add(new Card(Statics.ZEHN, Statics.KARO,true));
        cardList.add(new Card(Statics.BUBE, Statics.KARO,true));
        cardList.add(new Card(Statics.BUBE, Statics.KARO,true));
        cardList.add(new Card(Statics.DAME, Statics.KARO,true));
        cardList.add(new Card(Statics.DAME, Statics.KARO,true));
        cardList.add(new Card(Statics.KOENIG, Statics.KARO,true));
        cardList.add(new Card(Statics.KOENIG, Statics.KARO,true));
        cardList.add(new Card(Statics.ASS, Statics.KARO,true));
        cardList.add(new Card(Statics.ASS, Statics.KARO,true));

        return cardList;
    }
    
    public static List<String> UNIQUE_CARDS = new ArrayList<>(Arrays.asList(
            Statics.KREUZ+Statics.ZEHN,
            Statics.KREUZ+Statics.BUBE,
            Statics.KREUZ+Statics.DAME,
            Statics.KREUZ+Statics.KOENIG,
            Statics.KREUZ+Statics.ASS,
            Statics.PIK+Statics.ZEHN,
            Statics.PIK+Statics.BUBE,
            Statics.PIK+Statics.DAME,
            Statics.PIK+Statics.KOENIG,
            Statics.PIK+Statics.ASS,
            Statics.HERZ+Statics.ZEHN,
            Statics.HERZ+Statics.BUBE,
            Statics.HERZ+Statics.DAME,
            Statics.HERZ+Statics.KOENIG,
            Statics.HERZ+Statics.ASS,
            Statics.KARO+Statics.ZEHN,
            Statics.KARO+Statics.BUBE,
            Statics.KARO+Statics.DAME,
            Statics.KARO+Statics.KOENIG,
            Statics.KARO+Statics.ASS));
}


