package base.messages;

import base.Constants;
public class MessageSetStartPlayer extends Message {

    public final static String COMMAND = Constants.SET_START_PLAYER_COMMAND;

    public MessageSetStartPlayer(int playerNumber) {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER_NUMBER,playerNumber);
    }

    public MessageSetStartPlayer(Message message){
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public int getPlayerNumber(){
        return this.params.get(Constants.PLAYER_NUMBER).getAsInt();
    }
}
