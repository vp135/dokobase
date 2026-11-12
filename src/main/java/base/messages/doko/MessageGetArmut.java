package base.messages.doko;

import base.Constants;
import base.messages.Message;

public class MessageGetArmut extends Message {

    public final static String COMMAND = Constants.DOKO_GET_ARMUT_COMMAND;

    public MessageGetArmut() {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER, -1);
    }

    public MessageGetArmut(int p, boolean getArmut) {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER, p);
        this.params.addProperty(Constants.GET_ARMUT, getArmut);
    }

    public MessageGetArmut(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public int getPlayerNumber() {
        return params.get(Constants.PLAYER).getAsInt();
    }

    public boolean getsArmut() {
        return params.get(Constants.GET_ARMUT).getAsBoolean();
    }
}
