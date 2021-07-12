package base.messages;

import base.Statics;

public class AdminRequest extends RequestObject {

    public final static String COMMAND = "request";

    public AdminRequest(Statics.ADMINREQUESTS request) {
        this.command = COMMAND;
        this.params.addProperty("request", request.value);
    }
}
