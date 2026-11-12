package base;

import base.messages.doko.MessageGameSelected;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Statics {

    public static final String KREUZ = Constants.KREUZ;
    public static final String PIK = Constants.PIK;
    public static final String HERZ = Constants.HERZ;
    public static final String KARO = Constants.KARO;
    public static final String SIEBEN = Constants.SIEBEN;
    public static final String ACHT = Constants.ACHT;
    public static final String NEUN = Constants.NEUN;
    public static final String ZEHN = Constants.ZEHN;
    public static final String BUBE = Constants.BUBE;
    public static final String DAME = Constants.DAME;
    public static final String KOENIG = Constants.KOENIG;
    public static final String ASS = Constants.ASS;
    public static final String TRUMPF = Constants.TRUMPF;

    public enum game {
        DOKO,
        SKAT
    }

    public static final String VERSION = Constants.APP_VERSION;

    public enum ADMIN_REQUESTS {
        NOTHING(0),
        SET_START_PLAYER(1),
        SHUFFLE(10),
        ACKNOWLEDGE(11),
        END_CLIENTS(20),
        END_SERVER(21);

        public final int value;

        ADMIN_REQUESTS(int value) {
            this.value = value;
        }

        public static ADMIN_REQUESTS getName(int val) {
            for (ADMIN_REQUESTS request : ADMIN_REQUESTS.values()) {
                if (request.value == val) {
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

    public final static List<String> NORMALES_SPIEL = new ArrayList<>(Arrays.asList(
            "normales Spiel",
            "keine Vorbehalte"
    ));

    public final static List<String> SOLO = new ArrayList<>(Arrays.asList(
            "%s spielt ein %s",
            "%s spielt ein %s Solo"
    ));

    public final static List<String> ARMUT_GIVE = new ArrayList<>(Arrays.asList(
            "%s hat nur %d Trumpf",
            "%s hat eine Armut und gibt %d Trumpf ab"
    ));

    public final static List<String> KOENIGE = new ArrayList<>(Arrays.asList(
            "%s hat mehr als 5 Koenige",
            "%s hat zu viele Koenige"
    ));

    public final static List<String> ARMUT_REFUSE = new ArrayList<>(Arrays.asList(
            "%s lehnt die Armut ab",
            "%s nimmt die Armut nicht mit"
    ));

    public final static List<String> ARMUT_ACCEPT = new ArrayList<>(Arrays.asList(
            "%s nimmt die Armut auf",
            "%s akzeptiert die Armut"
    ));

    public final static List<String> ARMUT_RETURN = new ArrayList<>(Arrays.asList(
            "%s bekommt %s Trumpf zurueck",
            "%s erhaelt %s Trumpf zurueck",
            "An %s gehen %s Trumpf zurueck"
    ));

    public final static List<String> NEU_GEBEN = new ArrayList<>(Arrays.asList(
            "Es wird neu gegeben",
            "Es wird nochmal gegeben",
            "Karten werden neu verteilt"
    ));

    public static final List<String> HOCHZEIT = new ArrayList<>(Arrays.asList(
            "%s moechte heiraten",
            "%s will Hochzeit feiern"
    ));


    public static String getGameName(MessageGameSelected.GAMES game) {
        switch (game) {
            case BUBEN:
                return "Bubensolo";
            case DAMEN:
                return "Damensolo";
            case BUBENDAMEN:
                return "Buben-Damensolo";
            case FLEISCHLOS:
                return Constants.FLEISCHLOS;
            case KREUZ:
                return "Kreuzsolo";
            case PIK:
                return "Piksolo";
            case HERZ:
                return "Herzsolo";
            case KARO:
                return "Karosolo";
            default:
                return Constants.EMPTY_STRING;
        }
    }

    public static String getString(List<String> list, Object... args) {
        Random r = new Random(System.currentTimeMillis());
        return String.format(list.get(r.nextInt(list.size())), args);
    }
}
