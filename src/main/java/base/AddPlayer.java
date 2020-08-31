package base;

import com.google.gson.JsonObject;

public class AddPlayer extends RequestObject {

    public final static String COMMAND = "AddPlayer";

    public AddPlayer(String name) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",name);
    }
}
