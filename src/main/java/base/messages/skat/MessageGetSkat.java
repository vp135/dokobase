package base.messages.skat;

import base.Constants;
import base.messages.Message;

public class MessageGetSkat extends Message {

    public final static String COMMAND = Constants.SKAT_GET_SKAT_COMMAND;

    public MessageGetSkat(String player) {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER, player);
    }
}
