package base;

import com.google.gson.JsonObject;

public class TcpHeartbeat extends RequestObject {

    public final static String COMMAND = "Heartbeat";

    public TcpHeartbeat(String player) {
        this.command =COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",player);
    }
}
