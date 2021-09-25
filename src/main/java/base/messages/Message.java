package base.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.UUID;

public class Message {


    protected String command;
    protected JsonObject params = new JsonObject();
    //public UUID guid;
    public String sender;


    public Message() {
        //guid = UUID.randomUUID();
    }

    public String getCommand() {
        return command;
    }

    public JsonObject getParams() {
        return params;
    }

    public static Message fromString(String json) {
        Gson gson = new Gson();
        Type token = new TypeToken<Message>() {
        }.getType();
        return (gson.fromJson(json.trim(), token));
    }

    public String toJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type token = new TypeToken<Message>() {}.getType();
        return (gson.toJson(this, token));
    }

    public JsonObject toJsonObject() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJsonTree(this).getAsJsonObject();
    }

    public void newGUID() {
        //this.guid = UUID.randomUUID();
    }
}
