package base.messages;

public class MessageAcknowledge extends Message {

    public final static String COMMAND = "ackowledge";

    public MessageAcknowledge() {
        this.command = COMMAND;
    }
}
