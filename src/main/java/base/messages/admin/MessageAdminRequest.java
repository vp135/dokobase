package base.messages.admin;

import base.Constants;
import base.Statics;
import base.messages.Message;

public class MessageAdminRequest extends Message {

    public final static String COMMAND = Constants.REQUEST;

    public MessageAdminRequest(Statics.ADMIN_REQUESTS request) {
        this.command = COMMAND;
        this.params.addProperty(Constants.REQUEST, request.value);
    }

    public MessageAdminRequest(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public Statics.ADMIN_REQUESTS getRequest() {
        return Statics.ADMIN_REQUESTS.getName(params.get(Constants.REQUEST).getAsInt());
    }
}
