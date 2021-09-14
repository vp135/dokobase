package base.messages.doko;

import base.game.doko.DokoEndDialog;
import base.interfaces.IDialogInterface;
import base.messages.Message;

public class MessageGameEnd extends Message {
    public final static String COMMAND = "GameEnd";

    public MessageGameEnd(String re1, String re2, String kontra1, String kontra2, int remain) {
        this.command = COMMAND;
        this.params.addProperty("re1", re1);
        this.params.addProperty("re2", re2);
        this.params.addProperty("kontra1", kontra1);
        this.params.addProperty("kontra2", kontra2);
        this.params.addProperty("remain", remain);
    }

    public MessageGameEnd(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public DokoEndDialog getEndDialog(IDialogInterface client) {
        return new DokoEndDialog(client,
                params.get("re1").getAsString(),
                params.get("re2").getAsString(),
                params.get("kontra1").getAsString(),
                params.get("kontra2").getAsString(),
                params.get("remain").getAsInt());
    }
}
