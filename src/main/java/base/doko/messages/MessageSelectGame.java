package base.doko.messages;

import base.messages.Message;
import com.google.gson.JsonObject;

public class MessageSelectGame extends Message {

    public final static String COMMAND = "SelectGame";

    public MessageSelectGame() {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("0",0);
    }

    public MessageSelectGame(Message message){
        this.command = COMMAND;
        this.params = message.getParams();
    }
}
