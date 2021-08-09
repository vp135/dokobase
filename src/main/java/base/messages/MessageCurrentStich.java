package base.messages;

import base.BaseCard;
import base.DokoConfig;
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


    public MessageCurrentStich(Map<BaseCard, Integer> map){
        this.command = CURRENT;
        this.params = new JsonObject();
        map.keySet().forEach(p-> this.params.addProperty(map.get(p).toString(),p.cardNumber));
    }

    public MessageCurrentStich(Map<BaseCard, Integer> map, int player, boolean isLast) {
        this(map);
        this.params.addProperty("player",player);
        if (isLast){
            this.command = LAST;
        }
    }

    public MessageCurrentStich(Map<BaseCard, Integer> map, String command){
        Map<BaseCard, Integer> baseMap = new HashMap<>();
        map.keySet().forEach(key-> baseMap.put(key,map.get(key)));
        this.command = command;
        this.params = new JsonObject();
        baseMap.keySet().forEach(p-> this.params.addProperty(baseMap.get(p).toString(),p.cardNumber));
    }

    public MessageCurrentStich(Message message){
        this.command = message.command;
        this.params = message.getParams();
    }

    public Map<Integer,BaseCard> GetStichMap(Statics.game game) {
        Map<Integer, BaseCard> map = new HashMap<>();
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
