package base.messages;

import com.google.gson.JsonObject;

public class MessageReadyForNextRound extends Message {

    public final static String COMMAND = "ReadyForNextRound";

    public MessageReadyForNextRound(int p) {
        this.command =COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",p);
    }

    public MessageReadyForNextRound(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public int getPlayerNumber(){
        return params.get("player").getAsInt();
    }
}
