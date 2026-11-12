package base.messages.skat;

import base.Constants;
import base.messages.Message;

public class MessagePassen extends Message {

    public final static String COMMAND = Constants.SKAT_PASSEN_COMMAND;

    public MessagePassen(String player) {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER, player);
    }

    public MessagePassen(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName() {
        return params.get(Constants.PLAYER).getAsString();
    }
}
