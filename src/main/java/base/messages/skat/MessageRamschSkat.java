package base.messages.skat;

import base.Constants;
import base.messages.Message;

public class MessageRamschSkat extends Message {

    public final static String COMMAND = Constants.SKAT_RAMSCH_SKAT_COMMAND;

    public MessageRamschSkat() {
        this.command = COMMAND;
    }
}
