package base.messages;

public class AdminRequest extends RequestObject {

    public final static String COMMAND = "request";

    public AdminRequest(String request) {
        this.command = COMMAND;
        this.params.addProperty("request", request);
    }
}
