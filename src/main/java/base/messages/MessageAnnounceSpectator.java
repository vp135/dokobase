package base.messages;

public class MessageAnnounceSpectator extends Message {

    public final static String COMMAND = "AnnounceSpectator";

    public MessageAnnounceSpectator(int p1, int p2) {
        this.command = COMMAND;
        this.params.addProperty("spectator", p1);
        this.params.addProperty("starter", p2);
    }

    public MessageAnnounceSpectator(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public int getSpectatorNumber() {
        return params.get("spectator").getAsInt();
    }

    public int getStarterNumber() {
        return params.get("starter").getAsInt();
    }
}