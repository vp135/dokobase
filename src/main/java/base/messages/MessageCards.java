package base.messages;

import base.Card;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MessageCards extends Message {

    public final static String TYPE = "Cards";

    private final List<Card> cards;

    public MessageCards(String receiver, String sender, List<Card> cards) {
        super(TYPE, receiver, sender);
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public static MessageCards fromString(String json){
        Gson gson = new Gson();
        Type token = new TypeToken<MessageCards>(){}.getType();
        return (gson.fromJson(json,token));
    }

    public String toJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type token = new TypeToken<MessageCards
                >(){}.getType();
        return (gson.toJson(this,token));
    }
}
