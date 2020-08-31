package base;

import com.google.gson.JsonObject;

public class DisplayMessage extends RequestObject {

    public final static String COMMAND = "DisplayMessage";

    public DisplayMessage(String msg) {
        System.out.println(msg);
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("message",msg);
    }
}
