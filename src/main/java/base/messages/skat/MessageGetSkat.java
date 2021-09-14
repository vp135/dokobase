package base.messages.skat;

import base.messages.Message;

public class MessageGetSkat extends Message {

    public final static String COMMAND = "getSkat";

    public MessageGetSkat(String player) {
        this.command = COMMAND;
        this.params.addProperty("player", player);
    }
}
