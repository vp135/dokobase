package base.messages;

import com.google.gson.JsonObject;

public class AnnounceSpectator extends RequestObject {

    public final static String COMMAND = "AnnounceSpectator";

    public AnnounceSpectator(int p, int p2) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",p);
        this.params.addProperty("starter", p2);
    }
}