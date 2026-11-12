package base.messages.skat;

import base.Constants;
import base.game.skat.SkatEndDialog;
import base.interfaces.IDialogInterface;
import base.messages.Message;

public class MessageGameEnd extends Message {
    public final static String COMMAND = Constants.GAME_END;

    public MessageGameEnd(String re1, String kontra1, String player1, String player2, String player3, int remain) {
        this.command = COMMAND;
        this.params.addProperty(Constants.RE_ONE, re1);
        this.params.addProperty(Constants.KONTRA_ONE, kontra1);
        this.params.addProperty(Constants.PLAYER_ONE, player1);
        this.params.addProperty(Constants.PLAYER_TWO, player2);
        this.params.addProperty(Constants.PLAYER_THREE, player3);
        this.params.addProperty(Constants.REMAIN, remain);
    }

    public MessageGameEnd(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public SkatEndDialog getEndDialog(IDialogInterface client, MessageGameSelected.GAMES selectedGame) {
        return new SkatEndDialog(
                client,
                selectedGame,
                this.getParams().get(Constants.RE_ONE).getAsString(),
                this.getParams().get(Constants.KONTRA_ONE).getAsString(),
                this.getParams().get(Constants.PLAYER_ONE).getAsString(),
                this.getParams().get(Constants.PLAYER_TWO).getAsString(),
                this.getParams().get(Constants.PLAYER_THREE).getAsString(),
                this.getParams().get(Constants.REMAIN).getAsInt());
    }
}
