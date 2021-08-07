package base.skat.messages;

import base.BaseCard;
import base.messages.Message;
import com.google.gson.JsonArray;

import java.util.List;

public class MessageSkat extends Message {
    public final static String COMMAND = "skat";

    public MessageSkat(String player, List<BaseCard> skat) {
        this.command = COMMAND;
        this.params.addProperty("player",player);
        JsonArray array = new JsonArray();
        skat.forEach(p->array.add(p.toString()));
        params.add("cards",array);
    }
}
