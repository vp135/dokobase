package base.messages.skat;

import base.messages.Message;

public class MessageGrandHand extends Message {

    public final static String COMMAND = "grandHand";

    public MessageGrandHand(String player, boolean grandHand) {
        this.command = COMMAND;
        this.params.addProperty("player", player);
        this.params.addProperty("grandHand", grandHand);
    }

    public MessageGrandHand(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName() {
        return params.get("player").getAsString();
    }

    public boolean getGrandHand() {
        return params.get("grandHand").getAsBoolean();
    }
}
