package base.messages.doko;

import base.messages.Message;

public class MessageGetArmut extends Message {

    public final static String COMMAND = "GetArmut";

    public MessageGetArmut() {
        this.command = COMMAND;
        this.params.addProperty("player", -1);
    }

    public MessageGetArmut(int p, boolean getArmut) {
        this.command = COMMAND;
        this.params.addProperty("player", p);
        this.params.addProperty("getArmut", getArmut);
    }

    public MessageGetArmut(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public int getPlayerNumber() {
        return params.get("player").getAsInt();
    }

    public boolean getsArmut() {
        return params.get("getArmut").getAsBoolean();
    }
}
