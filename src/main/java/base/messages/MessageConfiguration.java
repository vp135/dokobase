package base.messages;

import base.config.Configuration;
import base.config.DokoConfig;
import base.config.OtherConfig;
import base.config.SkatConfig;

public class MessageConfiguration extends Message {

    public final static String COMMAND = "configuration";

    public MessageConfiguration(Configuration configuration) {
        this.command = COMMAND;
        this.params.addProperty("doko", configuration.doko.toJson());
        this.params.addProperty("skat", configuration.skat.toJson());
        this.params.addProperty("other", configuration.other.toJson());
    }

    public MessageConfiguration(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public DokoConfig getDokoConfig() {
        return DokoConfig.fromString(this.params.get("doko").getAsString());
    }

    public SkatConfig getSkatConfig() {
        return SkatConfig.fromString(this.params.get("skat").getAsString());
    }

    public OtherConfig getOtherConfig() {
        return OtherConfig.fromString(this.params.get("other").getAsString());
    }
}
