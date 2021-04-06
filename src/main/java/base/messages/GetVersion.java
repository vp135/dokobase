package base.messages;

public class GetVersion extends RequestObject {

    public final static String COMMAND = "getVersion";

    public GetVersion(String playername, String version) {
        this.command = COMMAND;
        this.params.addProperty("player", playername);
        this.params.addProperty("version", version);
    }
}
