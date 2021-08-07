package base.messages;

import com.google.gson.JsonObject;

public class MessageAddPlayer extends Message {

    public final static String COMMAND = "AddPlayer";

    public MessageAddPlayer(String name) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",name);
    }

    public MessageAddPlayer(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName(){
        return params.get("player").getAsString();
    }
}
