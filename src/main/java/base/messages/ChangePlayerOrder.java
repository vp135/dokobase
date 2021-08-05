package base.messages;

import com.google.gson.JsonArray;

import java.util.List;

public class ChangePlayerOrder extends RequestObject {

    public final static String COMMAND= "changePlayerOrder";

    public ChangePlayerOrder(List<String> players) {
    this.command = COMMAND;
    JsonArray array = new JsonArray();
    players.forEach(array::add);
    this.params.add("players",array);
    }
}
