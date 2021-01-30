package base.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MessageAbortGame extends Message {

    public final static String TYPE = "AbortGame";
    private String player;

    public MessageAbortGame(String receiver, String sender, String nextPlayer) {
        super(TYPE, receiver, sender);
        this.player = nextPlayer;
    }

    public String getNextPlayer() {
        return player;
    }

    public void setNextPlayer(String player) {
        this.player = player;
    }

    public static MessageAddPlayer fromString(String json){
        Gson gson = new Gson();
        Type token = new TypeToken<MessageAbortGame>(){}.getType();
        return (gson.fromJson(json,token));
    }

    public String toJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type token = new TypeToken<MessageAbortGame>(){}.getType();
        return (gson.toJson(this,token));
    }
}
