package base.messages.skat;

import base.messages.Message;

public class MessageSchieben extends Message {

    public final static String COMMAND = "schieben";

    public MessageSchieben(String player) {
        this.command = COMMAND;
        this.params.addProperty("player", player);
    }

    public MessageSchieben(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName() {
        return params.get("player").getAsString();
    }
}
