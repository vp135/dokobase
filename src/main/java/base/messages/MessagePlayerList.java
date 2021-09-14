package base.messages;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class MessagePlayerList extends Message {

    public final static String IN_LOBBY = "PlayersInLobby";
    public final static String CHANGE_ORDER = "changePlayerOrder";


    public MessagePlayerList(List<String> players, String command) {
        this.command = command;
        JsonArray p = new JsonArray();
        for (String s : players) {
            p.add(s);
        }
        this.params.add("players", p);
    }

    public MessagePlayerList(Message message) {
        this.command = message.getCommand();
        this.params = message.getParams();
    }

    public static MessagePlayerList playersInLobby(List<String> players) {
        return new MessagePlayerList(players, IN_LOBBY);
    }

    public static MessagePlayerList playerOrderChanged(List<String> players) {
        return new MessagePlayerList(players, CHANGE_ORDER);
    }

    public List<String> getPlayerNamesList() {
        List<String> list = new ArrayList<>();
        params.get("players").getAsJsonArray().forEach(j -> list.add(j.getAsString()));
        return list;
    }
}
