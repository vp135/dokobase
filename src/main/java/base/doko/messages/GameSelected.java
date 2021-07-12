package base.doko.messages;

import base.messages.RequestObject;
import com.google.gson.JsonObject;

public class GameSelected extends RequestObject {

    public final static String COMMAND="GameSelected";

    public GameSelected(int p, GAMES game) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",p);
        this.params.addProperty("game",game.value);
    }

    public enum GAMES{
        NORMAL(0),
        DAMEN(1),
        BUBEN(2),
        BUBENDAMEN(3),
        FLEISCHLOS(4),
        KREUZ(5),
        PIK(6),
        HERZ(7),
        KARO(8),
        ARMUT(9),
        KOENIGE(10),
        HOCHZEIT(11);

        public final int value;
        GAMES(int val){
            this.value = val;
        }

        public static GAMES getName(int val){
            for (GAMES s: GAMES.values()){
                if(s.value==val){
                    return s;
                }
            }
            return NORMAL;
        }
    }
}
