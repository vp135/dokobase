package base.messages.doko;

import base.Constants;
import base.messages.Message;

public class MessageCardsReturned extends Message {

    public final static String COMMAND = Constants.DOKO_CARDS_RETURNED_COMMAND;

    public MessageCardsReturned() {
        this.command = COMMAND;
    }
}
