package base.messages;

public class Acknowledge extends RequestObject {

    public final static String COMMAND = "ackowledge";

    public Acknowledge() {
        this.command = COMMAND;
    }
}
