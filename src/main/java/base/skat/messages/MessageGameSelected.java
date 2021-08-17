package base.skat.messages;

import base.messages.Message;

public class MessageGameSelected extends Message {
    public final static String COMMAND = "gameSelected";

    public MessageGameSelected(String player, GAMES game, boolean hand, boolean ouvert){
        this.command = COMMAND;
        this.params.addProperty("player", player);
        this.params.addProperty("game", game.value);
        this.params.addProperty("hand", hand);
        this.params.addProperty("ouvert", ouvert);
    }

    public MessageGameSelected(Message message){
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName(){
        return params.get("player").getAsString();
    }

    public GAMES getSelectedGame(){
        return GAMES.getName(params.get("game").getAsInt());
    }

    public boolean gethand(){
        return params.get("hand").getAsBoolean();
    }

    public boolean getOuvert(){
        return params.get("ouvert").getAsBoolean();
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
