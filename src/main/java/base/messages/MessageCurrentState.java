package base.messages;

import base.Stich;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MessageCurrentState extends Message {

    public final static String TYPE = "CurrentState";

    private final String currentPlayer;
    private final Stich stich;

    public MessageCurrentState(String receiver, String sender, String currentPlayer, Stich stich) {
        super(TYPE, receiver, sender);
        this.currentPlayer = currentPlayer;
        this.stich = stich;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public Stich getStich() {
        return stich;
    }

    public static MessageCurrentState fromString(String json){
        Gson gson = new Gson();
        Type token = new TypeToken<MessageCurrentState>(){}.getType();
        return (gson.fromJson(json,token));
    }

    public String toJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type token = new TypeToken<MessageCurrentState>(){}.getType();
        return (gson.toJson(this,token));
    }
}
