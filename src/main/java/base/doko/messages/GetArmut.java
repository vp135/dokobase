package base.doko.messages;

import base.messages.RequestObject;
import com.google.gson.JsonObject;

public class GetArmut extends RequestObject {

    public final static String COMMAND = "GetArmut";

    public GetArmut() {
        this.command =COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",-1);
    }

    public GetArmut(int p,boolean getArmut) {
        this.command =COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",p);
        this.params.addProperty("getArmut",getArmut);
    }
}
