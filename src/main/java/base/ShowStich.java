package base;

import com.google.gson.JsonObject;

public class ShowStich extends RequestObject {
    public final static String COMMAND = "ShowStich";

    public ShowStich(Player player,int stichNumber) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("stichNumber",stichNumber);
    }
}
