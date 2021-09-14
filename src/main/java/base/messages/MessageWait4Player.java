package base.messages;

public class MessageWait4Player extends Message {

    public final static String COMMAND = "wait4Player";

    public MessageWait4Player(String playername) {
        this.command = COMMAND;
        this.params.addProperty("player", playername);
    }

    public MessageWait4Player(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName() {
        return this.params.get("player").getAsString();
    }
}
