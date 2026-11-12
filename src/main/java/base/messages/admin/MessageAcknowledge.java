package base.messages.admin;

import base.Constants;
import base.messages.Message;

public class MessageAcknowledge extends Message {

    public final static String COMMAND = Constants.ADMIN_ACKNOWLEDGE_COMMAND;

    public MessageAcknowledge() {
        this.command = COMMAND;
    }
}
