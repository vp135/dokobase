package base.messages;

import base.Constants;
public class MessageAddPlayer extends Message {

    public final static String COMMAND = Constants.ADD_PLAYER_COMMAND;

    public MessageAddPlayer(String name) {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER, name);
    }

    public MessageAddPlayer(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName() {
        return params.get(Constants.PLAYER).getAsString();
    }
}
