package base.skat.messages;

import base.messages.Message;

public class MessageSelectGame extends Message {
    public final static String COMMAND = "selectGame";

    public MessageSelectGame(){
        this.command = COMMAND;

    }
}
