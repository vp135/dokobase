package base.skat.messages;

import base.BaseCard;
import base.Statics;
import base.doko.DokoCards;
import base.doko.messages.MessageGameSelected;
import base.messages.Message;
import base.skat.SkatCards;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class MessageSkatCards extends Message {

    public final static String OUVERT = "ouvertCards";
    public final static String SKAT = "skat";

    public MessageSkatCards(String command, String player, List<BaseCard> cards) {
        this.command = command;
        this.params.addProperty("player", player);
        JsonArray array = new JsonArray();
        cards.forEach(p->array.add(p.toString()));
        params.add("cards",array);
    }

    public MessageSkatCards(Message message) {
        this.command = message.getCommand();
        this.params = message.getParams();
    }

    public static MessageSkatCards OuvertCards(String player, List<BaseCard> cards){
        return new MessageSkatCards(OUVERT,player,cards);
    }

    public static MessageSkatCards SkatCards(String player, List<BaseCard> cards){
        return new MessageSkatCards(SKAT,player,cards);
    }

    public List<BaseCard> getCards(){
        List<BaseCard> list = new ArrayList<>();
        this.params.get("cards").getAsJsonArray().forEach(j-> list.add(SkatCards.ALL_CARDS.get(j.getAsInt())));
        return list;
    }

    public String getPlayerName(){
        return params.get("player").getAsString();
    }

}
