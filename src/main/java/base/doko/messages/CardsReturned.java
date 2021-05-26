package base.doko.messages;

import base.messages.RequestObject;

public class CardsReturned extends RequestObject {

    public final static String COMMAND = "cardReturned";

    public CardsReturned() {
        this.command = COMMAND;
    }
}
