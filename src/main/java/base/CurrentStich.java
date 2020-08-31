package base;

import com.google.gson.JsonObject;

import java.util.Map;

public class CurrentStich extends RequestObject {

    public final static String CURRENT = "CurrentStich";
    public final static String LAST = "LastStich";
    public final static String ALL = "AllStich";
    public final static String SPECIFIC = "SpecificStich";

    public CurrentStich(Map<Integer,Card> map){
        this.command = CURRENT;
        this.params = new JsonObject();
        this.params.addProperty("player","all");
        map.keySet().forEach(p->params.addProperty(p.toString(),map.get(p).toString()));
    }

    public CurrentStich(int player){
        this.command = LAST;
        this.params = new JsonObject();
        this.params.addProperty("player",player);
    }

    public CurrentStich(Map<Integer,Card> map, int player) {
        this.command = LAST;
        this.params = new JsonObject();
        this.params.addProperty("player", "all");
        map.keySet().forEach(p -> params.addProperty(p.toString(), map.get(p).toString()));

    }

    public static CurrentStich specificStich(Map<Integer,Card> map){
        CurrentStich currentStich = new CurrentStich(map);
        currentStich.command = SPECIFIC;
        return currentStich;
    }

}
