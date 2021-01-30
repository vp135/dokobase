package base.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MessageAllPlayers extends Message{

    public final static String TYPE = "AllPlayers";

    private final List<String> players;

    public MessageAllPlayers(String receiver, String sender, List<String> players) {
        super(TYPE, receiver, sender);
        this.players = players;
    }

    public static MessageAllPlayers fromString(String json){
        Gson gson = new Gson();
        Type token = new TypeToken<MessageAllPlayers>(){}.getType();
        return (gson.fromJson(json,token));
    }

    public String toJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type token = new TypeToken<MessageAllPlayers>(){}.getType();
        return (gson.toJson(this,token));
    }

    public List<String> getPlayers() {
        return players;
    }
}
