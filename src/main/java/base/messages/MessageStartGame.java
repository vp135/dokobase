package base.messages;

import base.Statics;

public class MessageStartGame extends Message {
    public final static String COMMAND = "startGame";

    public MessageStartGame(String game) {
        this.command = COMMAND;
        this.params.addProperty("game", game);
    }

    public MessageStartGame(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public Statics.game getGame() {
        return Statics.game.valueOf(params.get("game").getAsString());
    }
}
