package base.messages;

import base.doko.messages.GameSelected;
import com.google.gson.JsonObject;

public class GameType extends RequestObject {

    public final static String COMMAND = "GameType";

    public GameType(GameSelected.GAMES gameType) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty(COMMAND,gameType.value);
    }
}
