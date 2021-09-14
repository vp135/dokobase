package base.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class DokoConfig {

    public Regeln regeln;
    public Sonderpunkte sonderpunkte;


    public DokoConfig() {
        sonderpunkte = new Sonderpunkte();
        regeln = new Regeln();
    }

    public static class Sonderpunkte {
        public boolean doppelkopf = true;
        public boolean karlchen = true;
        public boolean herzstich = true;
        public boolean fuchs = true;
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
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        Type token = new TypeToken<DokoConfig>() {
        }.getType();
        return (gson.toJson(this, token));
    }

}
