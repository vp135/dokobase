package base.skat.messages;

import base.messages.RequestObject;

public class GameSelected extends RequestObject {
    public final static String COMMAND = "gameSelected";

    public GameSelected(GAMES game, boolean hand, boolean ouvert){
        this.command = COMMAND;
        this.params.addProperty("game", game.name());
        this.params.addProperty("hand", hand);
        this.params.addProperty("ouvert", ouvert);
    }


    public enum GAMES{
        Ramsch(0),
        Karo(9),
        Herz(10),
        Pik(11),
        Kreuz(12),
        Null(23),
        Grand(24);


        private final int value;
        GAMES(int value) {
            this.value = value;
        }

        public static GAMES getName(int val){
            for (GAMES s: GAMES.values()){
                if(s.value==val){
                    return s;
                }
            }
            return Ramsch;
        }
    }
}
