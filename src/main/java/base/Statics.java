package base;

public class Statics {

    public static final String KREUZ = "Kreuz";
    public static final String PIK = "Pik";
    public static final String HERZ = "Herz";
    public static final String KARO = "Karo";
    public static final String SIEBEN = "7";
    public static final String ACHT = "8";
    public static final String NEUN = "9";
    public static final String ZEHN = "10";
    public static final String BUBE = "Bube";
    public static final String DAME = "Dame";
    public static final String KOENIG = "Koenig";
    public static final String ASS = "Ass";

    public enum game{
        DOKO,
        SKAT
    }

    public static final String VERSION = "4.2.1";

    public enum ADMINREQUESTS {
        NOTHING(0),
        SHUFFLE(1),
        ACKNOWLEDGE(2),
        END_CLIENTS(3);

        public final int value;
        ADMINREQUESTS(int value) {
            this.value = value;
        }

        public static ADMINREQUESTS getName(int val){
            for (ADMINREQUESTS request: ADMINREQUESTS.values()){
                if(request.value==val){
                    return request;
                }
            }
            return NOTHING;
        }
    }

    public enum SKAT_STATE {
        REIZEN(0),
        GRAND_HAND(1),
        RAMSCH(2),
        PLAY(3),
        END(4);

        private final int value;
        SKAT_STATE(int value) {
            this.value = value;
        }
    }
}
