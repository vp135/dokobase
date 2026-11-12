package base.messages;

import base.Constants;
public class MessageGetVersion extends Message {

    public final static String COMMAND = Constants.GET_VERSION_COMMAND;

    public MessageGetVersion(String playername, String version) {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER, playername);
        this.params.addProperty(Constants.VERSION, version);
    }

    public MessageGetVersion(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName() {
        return params.get(Constants.PLAYER).getAsString();
    }

    public String getVersion() {
        return params.get(Constants.VERSION).getAsString();
    }
}
