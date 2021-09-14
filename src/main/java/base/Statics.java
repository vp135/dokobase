package base;

import base.messages.doko.MessageGameSelected;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    public static final String TRUMPF = "Trumpf";

    public enum game {
        DOKO,
        SKAT
    }

    public static final String VERSION = "5.0.0";

    public enum ADMINREQUESTS {
        NOTHING(0),
        SHUFFLE(1),
        ACKNOWLEDGE(2),
        END_CLIENTS(3);

        public final int value;

        ADMINREQUESTS(int value) {
            this.value = value;
        }

        public static ADMINREQUESTS getName(int val) {
            for (ADMINREQUESTS request : ADMINREQUESTS.values()) {
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
            "%s spielt ein %s"
    ));

    public final static List<String> ARMUT_GIVE = new ArrayList<>(Arrays.asList(
            "%s hat nur %d Trumpf"
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
            "%s nimmt die Armut auf"
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
                return "Fleischlos";
            case KREUZ:
                return "Kreuzsolo";
            case PIK:
                return "Piksolo";
            case HERZ:
                return "Herzsolo";
            case KARO:
                return "Karosolo";
            default:
                return "";
        }
    }

    public static String getString(List<String> list, Object... args) {
        Random r = new Random(System.currentTimeMillis());
        return String.format(list.get(r.nextInt(list.size())), args);
    }
}
