package base.skat.messages;

import base.messages.Message;

public class MessageSchieben extends Message {

    public final static String COMMAND = "schieben";

    public MessageSchieben(String player) {
        this.command = COMMAND;
        this.params.addProperty("player",player);
    }
}
