package base.messages;

import base.Card;
import base.Statics;
import base.doko.DokoCards;
import base.skat.SkatCards;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

public class MessageCurrentStich extends Message {

    public final static String CURRENT = "CurrentStich";
    public final static String LAST = "LastStich";
    public final static String SPECIFIC = "SpecificStich";


    public MessageCurrentStich(Map<Card, Integer> map){
        this.command = CURRENT;
        this.params = new JsonObject();
        map.keySet().forEach(p-> this.params.addProperty(map.get(p).toString(),p.cardNumber));
    }

    public MessageCurrentStich(Map<Card, Integer> map, int player, boolean isLast) {
        this(map);
        this.params.addProperty("player",player);
        if (isLast){
            this.command = LAST;
        }
    }

    public MessageCurrentStich(Map<Card, Integer> map, String command){
        Map<Card, Integer> baseMap = new HashMap<>();
        map.keySet().forEach(key-> baseMap.put(key,map.get(key)));
        this.command = command;
        this.params = new JsonObject();
        baseMap.keySet().forEach(p-> this.params.addProperty(baseMap.get(p).toString(),p.cardNumber));
    }

    public MessageCurrentStich(Message message){
        this.command = message.command;
        this.params = message.getParams();
    }

    public Map<Integer, Card> GetStichMap(Statics.game game) {
        Map<Integer, Card> map = new HashMap<>();
        switch (game) {
            case DOKO:
                params.keySet().forEach(k -> map.put(Integer.parseInt(k), DokoCards.ALL_CARDS.get(params.get(k).getAsInt())));
                break;
            case SKAT:
                params.keySet().forEach(k -> map.put(Integer.parseInt(k), SkatCards.ALL_CARDS.get(params.get(k).getAsInt())));
                break;
        }
        return map;
    }


    public int getPlayerNumber(){
        return params.get("player").getAsInt();
    }

}
