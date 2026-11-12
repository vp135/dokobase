package base.messages;

import base.Constants;
public class MessageUpdateUserPanel extends Message {

    public final static String COMMAND = Constants.UPDATE_USER_PANEL_COMMAND;

    public MessageUpdateUserPanel(String p, String text) {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER, p);
        this.params.addProperty(Constants.TEXT, text);
    }

    public MessageUpdateUserPanel(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName() {
        return params.get(Constants.PLAYER).getAsString();
    }

    public String getText() {
        return params.get(Constants.TEXT).getAsString();
    }
}
