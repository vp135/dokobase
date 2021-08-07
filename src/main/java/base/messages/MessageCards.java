package base.messages;

import base.BaseCard;
import base.doko.DokoCards;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class MessageCards extends Message {

    public final static String COMMAND ="Cards";

    public MessageCards(List<BaseCard> cardList) {
        this.command = COMMAND;
        JsonArray array = new JsonArray();
        cardList.forEach(p->array.add(p.toString()));
        params.add("cards",array);
    }

    public MessageCards(Message message){
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public List<BaseCard> getCards(){
        List<BaseCard> list = new ArrayList<>();
        this.params.get("cards").getAsJsonArray().forEach(j->{
            list.add(new BaseCard(j.getAsString().split(" ")[1],
                    j.getAsString().split(" ")[0],
                    false));
        });
        return list;
    }
}
