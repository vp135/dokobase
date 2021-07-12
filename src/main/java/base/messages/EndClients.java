package base.messages;

public class EndClients extends RequestObject {

    public final static String COMMAND = "endClients";

    public EndClients() {
        this.command = COMMAND;
    }
}
