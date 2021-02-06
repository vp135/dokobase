package base;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;

public class GameEnd extends RequestObject {
    public final static String COMMAND = "GameEnd";

    public GameEnd(HashMap<Integer,Integer> map, List<Stich> stichList, List<Player> players) {
        this.command = COMMAND;
        this.params = new JsonObject();
        JsonArray array = new JsonArray();
        for(int i = 0; i<players.size();i++){
            array.add(map.get(i) + (players.get(i).isRe() ? "(Re)":"(Kontra)"));
        }
        map.keySet().forEach(p->array.add(map.get(p) +"("));
        this.params.add("result",array);
        for (int i = 0;i<stichList.size();i++) {
            this.params.addProperty("s"+ i,stichList.get(i).calculatePoints());
            this.params.addProperty("w"+ i,stichList.get(i).getWinner());
        }
    }

    public GameEnd(String re1, String re2, String kontra1, String kontra2){
        this.command = COMMAND;
        this.params.addProperty("re1",re1);
        this.params.addProperty("re2",re2);
        this.params.addProperty("kontra1", kontra1);
        this.params.addProperty("kontra2", kontra2);
    }
}
