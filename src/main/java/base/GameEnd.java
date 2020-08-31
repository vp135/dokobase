package base;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;

public class GameEnd extends RequestObject {
    public final static String COMMAND = "GameEnd";

    public GameEnd(HashMap<Integer,Integer> map, List<Stich> stichList) {
        this.command = COMMAND;
        this.params = new JsonObject();
        JsonArray array = new JsonArray();
        map.keySet().forEach(p->array.add(map.get(p)));
        this.params.add("result",array);
        for (int i = 0;i<stichList.size();i++) {
            this.params.addProperty(String.valueOf(i),stichList.get(i).calculatePoints());
        }
    }
}
