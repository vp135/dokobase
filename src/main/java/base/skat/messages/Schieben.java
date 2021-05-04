package base.skat.messages;

import base.messages.RequestObject;

public class Schieben extends RequestObject {

    public final static String COMMAND = "schieben";

    public Schieben(String player) {
        this.command = COMMAND;
        this.params.addProperty("player",player);
    }
}
