package base.skat.messages;

import base.messages.Message;

public class MessagePassen extends Message {

    public final static String COMMAND = "passen";

    public MessagePassen(String player) {
        this.command = COMMAND;
        this.params.addProperty("player",player);
    }
}
