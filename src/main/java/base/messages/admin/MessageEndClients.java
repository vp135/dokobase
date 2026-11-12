package base.messages.admin;

import base.Constants;
import base.messages.Message;

public class MessageEndClients extends Message {

    public final static String COMMAND = Constants.ADMIN_END_CLIENTS_COMMAND;

    public MessageEndClients() {
        this.command = COMMAND;
    }
}
