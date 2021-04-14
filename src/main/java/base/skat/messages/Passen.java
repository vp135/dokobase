package base.skat.messages;

import base.messages.RequestObject;

public class Passen extends RequestObject {

    public final static String COMMAND = "passen";

    public Passen(String player) {
        this.command = COMMAND;
        this.params.addProperty("player",player);
    }
}
