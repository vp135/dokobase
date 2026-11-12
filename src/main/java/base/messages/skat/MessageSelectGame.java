package base.messages.skat;

import base.Constants;
import base.messages.Message;

public class MessageSelectGame extends Message {
    public final static String COMMAND = Constants.SKAT_SELECT_GAME_COMMAND;

    public MessageSelectGame() {
        this.command = COMMAND;

    }
}
