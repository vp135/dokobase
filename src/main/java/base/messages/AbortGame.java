package base.messages;

import com.google.gson.JsonObject;

public class AbortGame extends RequestObject {

    public final static String COMMAND = "AbortGame";

    public AbortGame() {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("nextPlayer",true);
    }
}
