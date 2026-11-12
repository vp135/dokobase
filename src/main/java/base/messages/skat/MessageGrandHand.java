package base.messages.skat;

import base.Constants;
import base.messages.Message;

public class MessageGrandHand extends Message {

    public final static String COMMAND = Constants.GRAND_HAND;

    public MessageGrandHand(String player, boolean grandHand) {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER, player);
        this.params.addProperty(Constants.GRAND_HAND, grandHand);
    }

    public MessageGrandHand(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName() {
        return params.get(Constants.PLAYER).getAsString();
    }

    public boolean getGrandHand() {
        return params.get(Constants.GRAND_HAND).getAsBoolean();
    }
}
