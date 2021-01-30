package base;

import com.google.gson.JsonObject;

public class AnnounceSpectator extends RequestObject {

    public final static String COMMAND = "AnnounceSpectator";

    public AnnounceSpectator(int p) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",p);
    }
}
