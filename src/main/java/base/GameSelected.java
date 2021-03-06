package base;

import com.google.gson.JsonObject;

public class GameSelected extends RequestObject{


    public final static String NORMAL = "normales Spiel";
    public final static String DAMEN = "Damensolo";
    public final static String BUBEN = "Bubensolo";
    public final static String BUBENDAMEN = "Buben-Damensolo";
    public final static String FLEISCHLOS = "Fleischlos";
    public final static String KREUZ = "Kreuzsolo";
    public final static String PIK = "Piksolo";
    public final static String HERZ = "Herzsolo";
    public final static String KARO = "Karosolo";
    public final static String ARMUT = "Armut";
    public final static String KOENIGE = "Könige";
    public static final String HOCHZEIT = "Hochzeit";


    public final static String COMMAND="GameSelected";

    public GameSelected(int p, String game) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",p);
        this.params.addProperty("game",game);
    }
}
