package base.messages;

import base.Constants;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class MessagePlayerList extends Message {

    public final static String IN_LOBBY = Constants.PLAYER_LIST_IN_LOBBY;
    public final static String CHANGE_ORDER = Constants.PLAYER_LIST_CHANGE_ORDER;


    public MessagePlayerList(List<String> players, String command) {
        this.command = command;
        JsonArray p = new JsonArray();
        for (String s : players) {
            p.add(s);
        }
        this.params.add(Constants.PLAYERS, p);
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
        params.get(Constants.PLAYERS).getAsJsonArray().forEach(j -> list.add(j.getAsString()));
        return list;
    }
}
