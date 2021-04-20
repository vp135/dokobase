package base.messages;

import base.BaseCard;
import com.google.gson.JsonArray;

import java.util.List;

public class Cards extends RequestObject {

    public final static String COMMAND ="Cards";

    public Cards(List<BaseCard> cardList) {
        this.command = COMMAND;
        JsonArray array = new JsonArray();
        cardList.forEach(p->array.add(p.toString()));
        params.add("cards",array);
    }
}
