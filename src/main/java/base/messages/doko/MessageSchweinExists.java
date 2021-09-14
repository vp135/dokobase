package base.messages.doko;

import base.messages.Message;

public class MessageSchweinExists extends Message {

    public final static String COMMAND = "SchweinExists";

    public MessageSchweinExists() {
        this.command = COMMAND;
        this.params.addProperty("exists", true);
    }

    public MessageSchweinExists(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public boolean isExisting() {
        return this.params.get("exists").getAsBoolean();
    }
}
