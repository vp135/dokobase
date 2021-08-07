package base.messages;

import base.BaseCard;
import com.google.gson.JsonObject;

public class MessagePutCard extends Message {

    public final static String COMMAND = "PutCard";

    public MessagePutCard(int player, String farbe, String wert) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("player",player);
        this.params.addProperty("farbe",farbe);
        this.params.addProperty("wert",wert);
    }

    public MessagePutCard(Message message){
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public BaseCard getCard() {
        return new BaseCard(this.params.get("wert").getAsString(),this.params.get("farbe").getAsString(),false);
    }

    public int getPlayerNumber(){
        return this.params.get("player").getAsInt();
    }
}
