package base.skat.messages;

import base.messages.RequestObject;

public class SelectGame extends RequestObject {
    public final static String COMMAND = "selectGame";

    public SelectGame(){
        this.command = COMMAND;

    }
}
