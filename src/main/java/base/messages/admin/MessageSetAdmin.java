package base.messages.admin;

import base.Constants;
import base.messages.Message;

public class MessageSetAdmin extends Message {

    public final static String COMMAND = Constants.ADMIN_SET_ADMIN_COMMAND;

    public MessageSetAdmin(boolean admin) {
        this.command = COMMAND;
        this.params.addProperty(Constants.ISADMIN, admin);
    }

    public MessageSetAdmin(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public boolean isAdmin() {
        return params.get(Constants.ISADMIN).getAsBoolean();
    }
}
