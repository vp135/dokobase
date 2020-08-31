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


    public final static String COMMAND="GameSelected";

    public GameSelected(int player, String game) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",player);
        this.params.addProperty("game",game);
    }
}
