package base;

public class ReturnMessage extends RequestObject {
    public final static String COMMAND = "returnMessage";

    public ReturnMessage(Player player,RequestObject r) {
        this.command = COMMAND;
        this.params = r.toJsonObject();
    }
}
