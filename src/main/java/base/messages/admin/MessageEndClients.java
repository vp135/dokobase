package base.messages.admin;

import base.messages.Message;

public class MessageEndClients extends Message {

    public final static String COMMAND = "endClients";

    public MessageEndClients() {
        this.command = COMMAND;
    }
}
