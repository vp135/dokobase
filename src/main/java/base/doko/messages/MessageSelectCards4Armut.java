package base.doko.messages;

import base.messages.Message;
import com.google.gson.JsonObject;

public class MessageSelectCards4Armut extends Message {
    public final static String COMMAND = "SelectCards4Armut";

    public MessageSelectCards4Armut() {
        this.command =COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("message","Alle Truempfe auswaehlen");

    }

    public MessageSelectCards4Armut(Message message){
        this.command = COMMAND;
        this.params = message.getParams();
    }
}
