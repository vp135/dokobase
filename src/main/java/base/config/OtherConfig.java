package base.config;

import base.Statics;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OtherConfig {

    public String lastGame = Statics.game.DOKO.name();
    public boolean bedienen = false;
    public int comPlayer = 0;
    public List<String> players = new ArrayList<>();


    public OtherConfig() {

    }

    public static OtherConfig fromString(String json) {
        Gson gson = new Gson();
        Type token = new TypeToken<OtherConfig>() {
        }.getType();
        return (gson.fromJson(json, token));
    }

    public String toJson() {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        Type token = new TypeToken<OtherConfig>() {
        }.getType();
        return (gson.toJson(this, token));
    }

}
