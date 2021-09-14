package base.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SkatConfig {

    public boolean pflichtRamsch = false;
    public int beginnRamsch = 1;
    public int wiederholRamsch = 3;
    public boolean autoAbortNull = false;

    public SkatConfig() {

    }

    public static SkatConfig fromString(String json) {
        Gson gson = new Gson();
        Type token = new TypeToken<SkatConfig>() {
        }.getType();
        return (gson.fromJson(json, token));
    }

    public String toJson() {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        Type token = new TypeToken<SkatConfig>() {
        }.getType();
        return (gson.toJson(this, token));
    }
}
