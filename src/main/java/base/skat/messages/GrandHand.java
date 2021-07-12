package base.skat.messages;

import base.messages.RequestObject;

public class GrandHand extends RequestObject {

    public final static String COMMAND = "grandHand";

    public GrandHand(String player, boolean grandHand) {
        this.command = COMMAND;
        this.params.addProperty("player",player);
        this.params.addProperty("grandHand",grandHand);
    }
}
