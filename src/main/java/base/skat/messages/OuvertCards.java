package base.skat.messages;

import base.BaseCard;
import base.messages.RequestObject;
import com.google.gson.JsonArray;

import java.util.List;

public class OuvertCards extends RequestObject {

    public final static String COMMAND = "ouvertCards";

    public OuvertCards(String player, List<BaseCard> cards) {
        this.command = COMMAND;
        this.params.addProperty("player", player);
        JsonArray array = new JsonArray();
        cards.forEach(p->array.add(p.toString()));
        params.add("cards",array);
    }
}
