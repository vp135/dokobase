package base.messages;

public class MessageUpdateUserPanel extends Message {

    public final static String COMMAND = "UpdateUserPanel";

    public MessageUpdateUserPanel(String p, String text) {
        this.command = COMMAND;
        this.params.addProperty("player", p);
        this.params.addProperty("text", text);
    }

    public MessageUpdateUserPanel(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName() {
        return params.get("player").getAsString();
    }

    public String getText() {
        return params.get("text").getAsString();
    }
}
