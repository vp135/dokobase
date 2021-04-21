package base.messages;

import com.google.gson.JsonObject;

public class UpdateUserPanel extends RequestObject {

    public final static String COMMAND = "UpdateUserPanel";

    public UpdateUserPanel(String p, String text) {
        this.command =COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",p);
        this.params.addProperty("text",text);
    }
}
