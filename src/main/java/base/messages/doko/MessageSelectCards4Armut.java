package base.messages.doko;

import base.Constants;
import base.messages.Message;

public class MessageSelectCards4Armut extends Message {
    public final static String COMMAND = Constants.DOKO_SELECT_CARDS4_ARMUT_COMMAND;

    public MessageSelectCards4Armut() {
        this.command = COMMAND;
        this.params.addProperty(Constants.MESSAGE, "Alle Truempfe auswaehlen");
    }

    public MessageSelectCards4Armut(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }
}
