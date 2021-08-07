package base.skat.messages;

import base.BaseCard;
import base.messages.Message;
import com.google.gson.JsonArray;

import java.util.List;

public class MessageOuvertCards extends Message {

    public final static String COMMAND = "ouvertCards";

    public MessageOuvertCards(String player, List<BaseCard> cards) {
        this.command = COMMAND;
        this.params.addProperty("player", player);
        JsonArray array = new JsonArray();
        cards.forEach(p->array.add(p.toString()));
        params.add("cards",array);
    }
}
