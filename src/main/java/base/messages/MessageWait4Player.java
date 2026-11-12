package base.messages;

import base.Constants;
public class MessageWait4Player extends Message {

    public final static String COMMAND = Constants.WAIT4_PLAYER_COMMAND;

    public MessageWait4Player(String playername) {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER, playername);
    }

    public MessageWait4Player(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName() {
        return this.params.get(Constants.PLAYER).getAsString();
    }
}
