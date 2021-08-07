package base.messages;

import com.google.gson.JsonObject;

public class MessageDisplayMessage extends Message {

    public final static String COMMAND = "DisplayMessage";

    public MessageDisplayMessage(String msg) {
        //System.out.println(msg);
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("message",msg);
    }
}
