package base.messages;

import base.Player;
import base.Stich;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MessageEndGame extends Message {

    public final static String TYPE = "EndGame";

    private final List<Stich> stichList;
    private final List<Player> playerList;

    public MessageEndGame(String receiver, String sender, List<Stich> stichList, List<Player> playerList) {
        super(TYPE, receiver, sender);
        this.stichList = stichList;
        this.playerList = playerList;
    }

    public static MessageEndGame fromString(String json){
        Gson gson = new Gson();
        Type token = new TypeToken<MessageEndGame>(){}.getType();
        return (gson.fromJson(json,token));
    }

    public String toJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type token = new TypeToken<MessageEndGame>(){}.getType();
        return (gson.toJson(this,token));
    }

    public List<Stich> getStichList() {
        return stichList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
