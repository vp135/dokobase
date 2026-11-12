package base.messages;

import base.Constants;
public class MessageDisplayMessage extends Message {

    public final static String COMMAND = Constants.DISPLAY_MESSAGE_COMMAND;

    public MessageDisplayMessage(String msg) {
        this.command = COMMAND;
        this.params.addProperty(Constants.MESSAGE, msg);
    }

    public MessageDisplayMessage(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getText() {
        return params.get(Constants.MESSAGE).getAsString();
    }
}
