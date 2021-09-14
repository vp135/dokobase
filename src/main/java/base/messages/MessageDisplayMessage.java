package base.messages;

public class MessageDisplayMessage extends Message {

    public final static String COMMAND = "DisplayMessage";

    public MessageDisplayMessage(String msg) {
        this.command = COMMAND;
        this.params.addProperty("message", msg);
    }

    public MessageDisplayMessage(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getText() {
        return params.get("message").getAsString();
    }
}
