package base.messages;

public class MessageReadyForNextRound extends Message {

    public final static String COMMAND = "ReadyForNextRound";

    public MessageReadyForNextRound(int p) {
        this.command = COMMAND;
        this.params.addProperty("player", p);
    }

    public MessageReadyForNextRound(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public int getPlayerNumber() {
        return params.get("player").getAsInt();
    }
}
