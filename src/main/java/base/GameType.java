package base;

import com.google.gson.JsonObject;

public class GameType extends RequestObject {

    public final static String COMMAND = "GameType";

    public GameType(String gameType) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty(COMMAND,gameType);
    }
}
