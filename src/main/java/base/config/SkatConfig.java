package base.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SkatConfig {

    public final boolean pflichtRamsch = false;
    public final int beginnRamsch = 1;
    public final int wiederholRamsch = 3;
    public final boolean autoAbortNull = false;

    public SkatConfig() {

    }

    public static SkatConfig fromString(String json) {
        Gson gson = new Gson();
        Type token = new TypeToken<SkatConfig>() {}.getType();
        return (gson.fromJson(json, token));
    }

    public String toJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type token = new TypeToken<SkatConfig>() {}.getType();
        return (gson.toJson(this, token));
    }

    public String toPrettyJson() {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        Type token = new TypeToken<SkatConfig>() {}.getType();
        return (gson.toJson(this, token));
    }
}
