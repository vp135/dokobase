package base;

import base.doko.DokoCards;
import base.skat.SkatCards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseCard {

    public int order;
    public String kind;
    public String suit;
    public boolean trump;
    public int value;

    public BaseCard(String kind, String suit){
        this.kind = kind;
        this.suit = suit;
        this.trump = false;
    }

    public BaseCard(String kind, String suit, boolean trump) {
        this.kind = kind;
        this.suit = suit;
        this.trump = trump;
    }

    public BaseCard(String kind, String suit, Object game){
        this.kind = kind;
        this.suit = suit;
        if(game instanceof  base.doko.messages.MessageGameSelected.GAMES) {
            this.trump = DokoCards.isTrumpf(this, game);
    }else if(game instanceof base.skat.messages.MessageGameSelected.GAMES){
            this.trump = SkatCards.isTrumpf(this, game);
        }
    }

    public static List<String> UNIQUE_CARDS = new ArrayList<>(Arrays.asList(
            Statics.KREUZ+Statics.SIEBEN,
            Statics.KREUZ+Statics.ACHT,
            Statics.KREUZ+Statics.NEUN,
            Statics.KREUZ+Statics.ZEHN,
            Statics.KREUZ+Statics.BUBE,
            Statics.KREUZ+Statics.DAME,
            Statics.KREUZ+Statics.KOENIG,
            Statics.KREUZ+Statics.ASS,
            Statics.PIK+Statics.SIEBEN,
            Statics.PIK+Statics.ACHT,
            Statics.PIK+Statics.NEUN,
            Statics.PIK+Statics.ZEHN,
            Statics.PIK+Statics.BUBE,
            Statics.PIK+Statics.DAME,
            Statics.PIK+Statics.KOENIG,
            Statics.PIK+Statics.ASS,
            Statics.HERZ+Statics.SIEBEN,
            Statics.HERZ+Statics.ACHT,
            Statics.HERZ+Statics.NEUN,
            Statics.HERZ+Statics.ZEHN,
            Statics.HERZ+Statics.BUBE,
            Statics.HERZ+Statics.DAME,
            Statics.HERZ+Statics.KOENIG,
            Statics.HERZ+Statics.ASS,
            Statics.KARO+Statics.SIEBEN,
            Statics.KARO+Statics.ACHT,
            Statics.KARO+Statics.NEUN,
            Statics.KARO+Statics.ZEHN,
            Statics.KARO+Statics.BUBE,
            Statics.KARO+Statics.DAME,
            Statics.KARO+Statics.KOENIG,
            Statics.KARO+Statics.ASS));

    public static BaseCard fromString(String string) {
        return new BaseCard(string.split(" ")[0],string.split(" ")[1]);
    }

    public String toTrimedString() {
        return this.suit +this.kind;
    }

    public String toString(){
        return (this.suit +" "+this.kind);
    }
}
