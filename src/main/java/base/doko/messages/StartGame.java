package base.doko.messages;

import base.messages.RequestObject;
import com.google.gson.JsonObject;

public class StartGame extends RequestObject {
    public final static String COMMAND = "startGame";

    public StartGame() {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("start",true);
    }
}
