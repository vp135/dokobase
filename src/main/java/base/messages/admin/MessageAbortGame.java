package base.messages.admin;

import base.messages.Message;

public class MessageAbortGame extends Message {

    public final static String COMMAND = "AbortGame";

    public MessageAbortGame() {
        this.command = COMMAND;
        this.params.addProperty("nextPlayer", true);
    }

    public MessageAbortGame(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

}
