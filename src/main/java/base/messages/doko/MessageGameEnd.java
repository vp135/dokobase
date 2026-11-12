package base.messages.doko;

import base.Constants;
import base.game.doko.DokoEndDialog;
import base.interfaces.IDialogInterface;
import base.messages.Message;

public class MessageGameEnd extends Message {
    public final static String COMMAND = Constants.GAME_END;

    public MessageGameEnd(String re1, String re2, String kontra1, String kontra2, int remain) {
        this.command = COMMAND;
        this.params.addProperty(Constants.RE_ONE, re1);
        this.params.addProperty(Constants.RE_TWO, re2);
        this.params.addProperty(Constants.KONTRA_ONE, kontra1);
        this.params.addProperty(Constants.KONTRA_TWO, kontra2);
        this.params.addProperty(Constants.REMAIN, remain);
    }

    public MessageGameEnd(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public DokoEndDialog getEndDialog(IDialogInterface client) {
        return new DokoEndDialog(client,
                params.get(Constants.RE_ONE).getAsString(),
                params.get(Constants.RE_TWO).getAsString(),
                params.get(Constants.KONTRA_ONE).getAsString(),
                params.get(Constants.KONTRA_TWO).getAsString(),
                params.get(Constants.REMAIN).getAsInt());
    }
}
