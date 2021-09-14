package base.messages;

public class MessageSetStartPlayer extends Message {

    public final static String COMMAND = "setStartPlayer";

    public MessageSetStartPlayer(int playerNumber) {
        this.command = COMMAND;
        this.params.addProperty("playerNumber",playerNumber);
    }

    public MessageSetStartPlayer(Message message){
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public int getPlayerNumber(){
        return this.params.get("playerNumber").getAsInt();
    }
}
