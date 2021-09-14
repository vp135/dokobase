package base.messages.admin;

import base.messages.Message;

public class MessageAcknowledge extends Message {

    public final static String COMMAND = "ackowledge";

    public MessageAcknowledge() {
        this.command = COMMAND;
    }
}
