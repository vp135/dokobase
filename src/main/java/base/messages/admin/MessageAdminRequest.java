package base.messages.admin;

import base.Statics;
import base.messages.Message;

public class MessageAdminRequest extends Message {

    public final static String COMMAND = "request";

    public MessageAdminRequest(Statics.ADMINREQUESTS request) {
        this.command = COMMAND;
        this.params.addProperty("request", request.value);
    }

    public MessageAdminRequest(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public Statics.ADMINREQUESTS getRequest() {
        return Statics.ADMINREQUESTS.getName(params.get("request").getAsInt());
    }
}
