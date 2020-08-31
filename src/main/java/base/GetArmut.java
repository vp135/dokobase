package base;

import com.google.gson.JsonObject;

public class GetArmut extends RequestObject {

    public final static String COMMAND = "GetArmut";

    public GetArmut() {
        this.command =COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",-1);
    }

    public GetArmut(int player,boolean getArmut) {
        this.command =COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",player);
        this.params.addProperty("getArmut",getArmut);
    }
}
