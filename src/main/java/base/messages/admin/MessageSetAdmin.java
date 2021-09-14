package base.messages.admin;

import base.messages.Message;

public class MessageSetAdmin extends Message {

    public final static String COMMAND = "Admin";

    public MessageSetAdmin(boolean admin) {
        this.command = COMMAND;
        this.params.addProperty("isAdmin", admin);
    }

    public MessageSetAdmin(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public boolean isAdmin() {
        return params.get("isAdmin").getAsBoolean();
    }
}
