package base;

import com.google.gson.JsonObject;

public class TcpHeartbeat extends RequestObject {

    public final static String COMMAND = "Heartbeat";

    public TcpHeartbeat(Player player, String p) {
        this.command =COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",p);
    }
}
