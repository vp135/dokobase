package base.messages.admin;

import base.Constants;
import base.messages.Message;

public class MessageAbortGame extends Message {

    public final static String COMMAND = Constants.ADMIN_ABORT_GAME_COMMAND;

    public MessageAbortGame() {
        this.command = COMMAND;
        this.params.addProperty("nextPlayer", true);
    }

    public MessageAbortGame(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

}
