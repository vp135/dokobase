package base.messages.skat;

import base.Constants;
import base.messages.Message;

public class MessageSchieben extends Message {

    public final static String COMMAND = Constants.SKAT_SCHIEBEN_COMMAND;

    public MessageSchieben(String player) {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER, player);
    }

    public MessageSchieben(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName() {
        return params.get(Constants.PLAYER).getAsString();
    }
}
