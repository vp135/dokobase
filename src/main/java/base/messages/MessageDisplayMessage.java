package base.messages;

import com.google.gson.JsonObject;

public class MessageDisplayMessage extends Message {

    public final static String COMMAND = "DisplayMessage";

    public MessageDisplayMessage(String msg) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("message",msg);
    }

    public MessageDisplayMessage(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getText(){
        return params.get("message").getAsString();
    }
}
