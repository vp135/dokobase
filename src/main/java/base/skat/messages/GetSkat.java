package base.skat.messages;

import base.messages.RequestObject;

public class GetSkat extends RequestObject {

    public final static String COMMAND = "getSkat";
    public GetSkat(String player) {
        this.command = COMMAND;
        this.params.addProperty("player",player);
    }
}
