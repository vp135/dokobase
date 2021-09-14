package base.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class DokoConfig {

    public final Regeln regeln;
    public final Sonderpunkte sonderpunkte;


    public DokoConfig() {
        sonderpunkte = new Sonderpunkte();
        regeln = new Regeln();
    }

    public static class Sonderpunkte {
        public final boolean doppelkopf = true;
        public final boolean karlchen = true;
        public final boolean herzstich = true;
        public final boolean fuchs = true;
    }

    public static class Regeln {
        public boolean zweiteHerz10 = true;
        public boolean schwein = true;
    }

    public static DokoConfig fromString(String json) {
        Gson gson = new Gson();
        Type token = new TypeToken<DokoConfig>() {
        }.getType();
        return (gson.fromJson(json, token));
    }

    public String toJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type token = new TypeToken<DokoConfig>() {
        }.getType();
        return (gson.toJson(this, token));
    }

    public String toPrettyJson() {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        Type token = new TypeToken<DokoConfig>() {
        }.getType();
        return (gson.toJson(this, token));
    }

}
