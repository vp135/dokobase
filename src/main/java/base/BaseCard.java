package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseCard {

    public int order;
    public String value;
    public String farbe;
    public boolean trumpf;

    public BaseCard(String value, String farbe, boolean trumpf) {
        this.value = value;
        this.farbe = farbe;
        this.trumpf = trumpf;
    }

    protected abstract boolean isTrumpf(Object object);

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

    public String toTrimedString() {
        return this.farbe+this.value;
    }
}
