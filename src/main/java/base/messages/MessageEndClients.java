package base.messages;

public class MessageEndClients extends Message {

    public final static String COMMAND = "endClients";

    public MessageEndClients() {
        this.command = COMMAND;
    }
}
