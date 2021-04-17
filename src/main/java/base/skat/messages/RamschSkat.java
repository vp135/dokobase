package base.skat.messages;

import base.messages.RequestObject;

public class RamschSkat extends RequestObject {

    public final static String COMMAND = "ramschSkat";

    public RamschSkat() {
        this.command =COMMAND;
    }
}
