package base.messages.admin;

import base.messages.Message;
import com.google.gson.JsonObject;

public class MessageAbortGame extends Message {

    public final static String COMMAND = "AbortGame";

    public MessageAbortGame() {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("nextPlayer",true);
    }

    public MessageAbortGame(Message message){
        this.command = COMMAND;
        this.params = message.getParams();
    }

}
