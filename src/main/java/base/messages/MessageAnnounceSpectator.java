package base.messages;

import base.Constants;
public class MessageAnnounceSpectator extends Message {

    public final static String COMMAND = Constants.ANNOUNCE_SPECTATOR_COMMAND;

    public MessageAnnounceSpectator(int p1, int p2) {
        this.command = COMMAND;
        this.params.addProperty(Constants.SPECTATOR, p1);
        this.params.addProperty(Constants.STARTER, p2);
    }

    public MessageAnnounceSpectator(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public int getSpectatorNumber() {
        return params.get(Constants.SPECTATOR).getAsInt();
    }

    public int getStarterNumber() {
        return params.get(Constants.STARTER).getAsInt();
    }
}