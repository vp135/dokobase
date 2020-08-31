package base;

import com.google.gson.JsonObject;

public class ReadyForNextRound extends RequestObject {

    public final static String COMMAND = "ReadyForNextRound";

    public ReadyForNextRound(int player) {
        this.command =COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",player);
    }
}
