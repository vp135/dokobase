package base;

import com.google.gson.JsonObject;

public class SchweinExists extends RequestObject {

    public final static String COMMAND = "SchweinExists";

    public SchweinExists() {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("exists",true);
    }
}
