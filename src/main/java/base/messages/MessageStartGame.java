package base.messages;

import base.Constants;
import base.Statics;

public class MessageStartGame extends Message {
    public final static String COMMAND = Constants.START_GAME_COMMAND;

    public MessageStartGame(String game) {
        this.command = COMMAND;
        this.params.addProperty(Constants.GAME, game);
    }

    public MessageStartGame(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public Statics.game getGame() {
        return Statics.game.valueOf(params.get(Constants.GAME).getAsString());
    }
}
