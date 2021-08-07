package base.skat.messages;

import base.messages.Message;

public class MessageRamschSkat extends Message {

    public final static String COMMAND = "ramschSkat";

    public MessageRamschSkat() {
        this.command =COMMAND;
    }
}
