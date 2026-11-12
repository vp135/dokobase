package base.messages;

import base.Constants;
import base.config.Configuration;
import base.config.DokoConfig;
import base.config.OtherConfig;
import base.config.SkatConfig;

public class MessageConfiguration extends Message {

    public final static String COMMAND = Constants.CONFIGURATION_COMMAND;

    public MessageConfiguration(Configuration configuration) {
        this.command = COMMAND;
        this.params.addProperty(Constants.DOKO, configuration.doko.toJson());
        this.params.addProperty(Constants.SKAT, configuration.skat.toJson());
        this.params.addProperty(Constants.OTHER, configuration.other.toJson());
    }

    public MessageConfiguration(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public DokoConfig getDokoConfig() {
        return DokoConfig.fromString(this.params.get(Constants.DOKO).getAsString());
    }

    public SkatConfig getSkatConfig() {
        return SkatConfig.fromString(this.params.get(Constants.SKAT).getAsString());
    }

    public OtherConfig getOtherConfig() {
        return OtherConfig.fromString(this.params.get(Constants.OTHER).getAsString());
    }
}
