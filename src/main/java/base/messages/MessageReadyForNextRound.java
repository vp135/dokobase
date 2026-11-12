package base.messages;

import base.Constants;
public class MessageReadyForNextRound extends Message {

    public final static String COMMAND = Constants.READY_FOR_NEXT_ROUND_COMMAND;

    public MessageReadyForNextRound(int p) {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER, p);
    }

    public MessageReadyForNextRound(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public int getPlayerNumber() {
        return params.get(Constants.PLAYER).getAsInt();
    }
}
