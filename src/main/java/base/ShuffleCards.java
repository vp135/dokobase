package base;

public class ShuffleCards extends RequestObject {

    public final static String COMMAND = "ShuffledCards";

    public ShuffleCards() {
        this.command = COMMAND;
    }
}
