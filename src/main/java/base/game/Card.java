package base.game;

import base.Statics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card {

    public int playOrder;
    public final String kind;
    public final String suit;
    public boolean trump;
    public final int value;
    public final int cardNumber;

    public static final List<String> UNIQUE_CARDS = new ArrayList<>(Arrays.asList(
            Statics.KREUZ + Statics.SIEBEN,
            Statics.KREUZ + Statics.ACHT,
            Statics.KREUZ + Statics.NEUN,
            Statics.KREUZ + Statics.ZEHN,
            Statics.KREUZ + Statics.BUBE,
            Statics.KREUZ + Statics.DAME,
            Statics.KREUZ + Statics.KOENIG,
            Statics.KREUZ + Statics.ASS,
            Statics.PIK + Statics.SIEBEN,
            Statics.PIK + Statics.ACHT,
            Statics.PIK + Statics.NEUN,
            Statics.PIK + Statics.ZEHN,
            Statics.PIK + Statics.BUBE,
            Statics.PIK + Statics.DAME,
            Statics.PIK + Statics.KOENIG,
            Statics.PIK + Statics.ASS,
            Statics.HERZ + Statics.SIEBEN,
            Statics.HERZ + Statics.ACHT,
            Statics.HERZ + Statics.NEUN,
            Statics.HERZ + Statics.ZEHN,
            Statics.HERZ + Statics.BUBE,
            Statics.HERZ + Statics.DAME,
            Statics.HERZ + Statics.KOENIG,
            Statics.HERZ + Statics.ASS,
            Statics.KARO + Statics.SIEBEN,
            Statics.KARO + Statics.ACHT,
            Statics.KARO + Statics.NEUN,
            Statics.KARO + Statics.ZEHN,
            Statics.KARO + Statics.BUBE,
            Statics.KARO + Statics.DAME,
            Statics.KARO + Statics.KOENIG,
            Statics.KARO + Statics.ASS));

    public Card(String kind, String suit, int cardNumber, int value) {
        this.kind = kind;
        this.suit = suit;
        this.trump = false;
        this.cardNumber = cardNumber;
        this.value = value;
    }

    public String toTrimedString() {
        return this.suit + this.kind;
    }

    public String toString() {
        return (this.suit + " " + this.kind);
    }
}
