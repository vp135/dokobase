package base.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import java.beans.Transient;
import java.lang.reflect.Type;

public class Message {

    private String type;
    private String receiver;
    private String sender;


    public Message(String type, String receiver, String sender) {
        this.type = type;
        this.receiver = receiver;
        this.sender = sender;
    }

    public static Message fromString(String json){
        Gson gson = new Gson();
        Type token = new TypeToken<Message>(){}.getType();
        return (gson.fromJson(json,token));
    }

    public String toJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type token = new TypeToken<Message>(){}.getType();
        return (gson.toJson(this,token));
    }

    public String getType() {
        return type;
    }
}
