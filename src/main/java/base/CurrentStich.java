package base;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Map;

public class CurrentStich extends RequestObject {

    public final static String CURRENT = "CurrentStich";
    public final static String LAST = "LastStich";
    public final static String ALL = "AllStich";
    public final static String SPECIFIC = "SpecificStich";

    public CurrentStich(Map<Card, Integer> map){
        this.command = CURRENT;
        this.params = new JsonObject();
        map.keySet().forEach(p->{
            this.params.addProperty(map.get(p).toString(),p.farbe+" "+p.value);
        });
    }

    public CurrentStich(Map<Card, Integer> map, boolean isLast) {
        this(map);
        if (isLast){
            this.command = LAST;
        }
    }

    public CurrentStich(Map<Card, Integer> map, String command){
        this(map);
        this.command = command;
    }

}
