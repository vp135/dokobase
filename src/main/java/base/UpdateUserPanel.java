package base;

import com.google.gson.JsonObject;

public class UpdateUserPanel extends RequestObject {

    public final static String COMMAND = "UpdateUserPanel";

    public UpdateUserPanel(String player, String text) {
        this.command =COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",player);
        this.params.addProperty("text",text);
    }
}
