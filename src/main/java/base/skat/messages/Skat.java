package base.skat.messages;

import base.BaseCard;
import base.messages.RequestObject;
import com.google.gson.JsonArray;

import java.util.List;

public class Skat extends RequestObject {
    public final static String COMMAND = "skat";

    public Skat(String player, List<BaseCard> skat) {
        this.command = COMMAND;
        this.params.addProperty("player",player);
        JsonArray array = new JsonArray();
        skat.forEach(p->array.add(p.toString()));
        params.add("cards",array);
    }
}
