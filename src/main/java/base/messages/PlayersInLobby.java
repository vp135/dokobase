package base.messages;

import base.messages.RequestObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public class PlayersInLobby extends RequestObject {

    public final static String COMMAND="PlayersInLobby";

    public PlayersInLobby(List<String> players) {
        this.command= COMMAND;
        this.params = new JsonObject();
        JsonArray p = new JsonArray();
        for (String s:players){
            p.add(s);
        }
        this.params.add("players",p);
    }
}
