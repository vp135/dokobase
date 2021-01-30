package base.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MessageStartGame extends Message {

    public final static String TYPE =  "StartGame";

    public MessageStartGame(String receiver, String sender) {
        super(TYPE, receiver, sender);
    }


}
