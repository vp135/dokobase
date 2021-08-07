package base.messages;

import base.doko.messages.MessageGameSelected;
import base.doko.messages.MessageSelectGame;
import com.google.gson.JsonObject;

public class MessageGameType extends Message {

    public final static String COMMAND = "GameType";

    public MessageGameType(MessageGameSelected.GAMES gameType) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty(COMMAND,gameType.value);
    }

    public MessageGameType(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public MessageGameSelected.GAMES getSelectedGame(){
        return MessageGameSelected.GAMES.getName(params.get(COMMAND).getAsInt());
    }
}
