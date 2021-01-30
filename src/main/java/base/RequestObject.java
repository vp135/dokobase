package base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class RequestObject {

    protected String command;
    protected JsonObject params = new JsonObject();

    public String getCommand() {
        return command;
    }

    public JsonObject getParams() {
        return params;
    }

    public static RequestObject fromString(String json){
        Gson gson = new Gson();
        Type token = new TypeToken<RequestObject>(){}.getType();
        return (gson.fromJson(json,token));
    }

    public String toJson(){
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type token = new TypeToken<RequestObject>(){}.getType();
        return (gson.toJson(this,token));
    }

    public JsonObject toJsonObject(){
        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJsonTree(this).getAsJsonObject();
    }

}
