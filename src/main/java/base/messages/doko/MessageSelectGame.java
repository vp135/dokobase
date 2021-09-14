package base.messages.doko;

import base.messages.Message;

public class MessageSelectGame extends Message {

    public final static String COMMAND = "SelectGame";

    public MessageSelectGame() {
        this.command = COMMAND;
        this.params.addProperty("0", 0);
    }

    public MessageSelectGame(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }
}
