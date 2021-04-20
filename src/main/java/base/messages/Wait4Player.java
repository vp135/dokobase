package base.messages;

import com.google.gson.JsonObject;

public class Wait4Player extends RequestObject {

    public final static String COMMAND = "wait4Player";

    public Wait4Player(String playername) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player", playername);
    }
}
