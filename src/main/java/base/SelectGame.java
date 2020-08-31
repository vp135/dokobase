package base;

import com.google.gson.JsonObject;

public class SelectGame extends RequestObject {

    public final static String COMMAND = "SelectGame";

    public SelectGame() {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("0",0);
    }
}
