package base.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MessageAddPlayer extends Message {

    public final static String TYPE = "AddPlayer";
    private final String playerName;

    public MessageAddPlayer(String receiver, String sender, String playerName) {
        super(TYPE, receiver, sender);
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public static MessageAddPlayer fromString(String json){
        Gson gson = new Gson();
        Type token = new TypeToken<MessageAddPlayer>(){}.getType();
        return (gson.fromJson(json,token));
    }

    public String toJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type token = new TypeToken<MessageAddPlayer>(){}.getType();
        return (gson.toJson(this,token));
    }
}
