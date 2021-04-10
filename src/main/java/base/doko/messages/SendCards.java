package base.doko.messages;

import base.BaseCard;
import base.doko.Card;
import base.messages.RequestObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

public class SendCards extends RequestObject {
    public final static String COMMAND = "SendCards";
    public final static String RICH = "reich";
    public final static String POOR = "arm";

    public SendCards(List<BaseCard> cards, String receiver) {
        this.command = COMMAND;
        JsonArray array = new JsonArray();
        cards.forEach(card ->  array.add(card.toString()));
        this.params = new JsonObject();
        this.params.add("cards",array);
        this.params.addProperty("receiver",receiver);
    }
}
