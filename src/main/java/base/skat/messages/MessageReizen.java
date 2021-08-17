package base.skat.messages;

import base.messages.Message;

public class MessageReizen extends Message {

    public final static int[] VALUES = {18,20,22,23,24,27,30,33,35,36,40,44,45,46,48,50,54,55,59,60,
            63,66,70,72,77,80,81,84,88,90,96,99,100,108,110,120,121,132,144,168,192,216,240,264};

    public final static String COMMAND = "Reizen";

    public MessageReizen(String player, int value, boolean active) {
        this.command = COMMAND;
        this.params.addProperty("player", player);
        this.params.addProperty("value", value);
        this.params.addProperty("active", active);
    }

    public MessageReizen(Message message){
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName(){
        return this.params.get("player").getAsString();
    }

    public int getValue(){
        return this.params.get("value").getAsInt();
    }

    public boolean isActive(){
        return this.params.get("active").getAsBoolean();
    }
}
