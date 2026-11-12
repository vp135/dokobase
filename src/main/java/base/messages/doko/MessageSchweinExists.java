package base.messages.doko;

import base.Constants;
import base.messages.Message;

public class MessageSchweinExists extends Message {

    public final static String COMMAND = Constants.DOKO_SCHWEIN_EXISTS_COMMAND;

    public MessageSchweinExists() {
        this.command = COMMAND;
        this.params.addProperty(Constants.EXISTS, true);
    }

    public MessageSchweinExists(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public boolean isExisting() {
        return this.params.get(Constants.EXISTS).getAsBoolean();
    }
}
