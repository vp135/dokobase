package base.doko.messages;

import base.doko.Card;
import base.messages.RequestObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;



import java.util.List;

public class Cards extends RequestObject {

    public final static String COMMAND ="Cards";

    public Cards(List<Card> cardList) {
        this.command = COMMAND;
        this.params = new JsonObject();
        JsonArray array = new JsonArray();
        cardList.forEach(p->array.add(p.toString()));
        params.add("cards",array);
    }
}
