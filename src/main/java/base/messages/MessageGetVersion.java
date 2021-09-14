package base.messages;

public class MessageGetVersion extends Message {

    public final static String COMMAND = "getVersion";

    public MessageGetVersion(String playername, String version) {
        this.command = COMMAND;
        this.params.addProperty("player", playername);
        this.params.addProperty("version", version);
    }

    public MessageGetVersion(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName() {
        return params.get("player").getAsString();
    }

    public String getVersion() {
        return params.get("version").getAsString();
    }
}
