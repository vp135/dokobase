package base.skat.messages;

import base.messages.Message;

public class MessageGrandHand extends Message {

    public final static String COMMAND = "grandHand";

    public MessageGrandHand(String player, boolean grandHand) {
        this.command = COMMAND;
        this.params.addProperty("player",player);
        this.params.addProperty("grandHand",grandHand);
    }
}
