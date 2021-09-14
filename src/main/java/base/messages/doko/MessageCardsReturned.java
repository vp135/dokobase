package base.messages.doko;

import base.messages.Message;

public class MessageCardsReturned extends Message {

    public final static String COMMAND = "cardReturned";

    public MessageCardsReturned() {
        this.command = COMMAND;
    }
}
