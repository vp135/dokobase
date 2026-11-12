package base.config;

import base.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Configuration {

    private static final Logger log = LoggerFactory.getLogger(Configuration.class);

    public final ConConfig connection = new ConConfig();
    public final UIConfig ui = new UIConfig();
    public SkatConfig skat = new SkatConfig();
    public DokoConfig doko = new DokoConfig();
    public OtherConfig other = new OtherConfig();

    public Configuration() {

    }

    public static Configuration fromFile() {
        StringBuilder builder = new StringBuilder();
        if (Files.exists(Paths.get(Constants.CONFIG_JSON))) {
            try (FileReader reader = new FileReader(Constants.CONFIG_JSON)) {
                int c;
                while ((c = reader.read()) != -1) {
                    builder.append((char) c);
                }
            } catch (IOException e) {
                log.error("Failed to read configuration file", e);
            }
            return fromString(builder.toString());
        } else {
            return new Configuration();
        }
    }

    public void saveConfig() {
        try (FileWriter fw = new FileWriter(Constants.CONFIG_JSON);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(this.toPrettyJson());
        } catch (IOException e) {
            log.error("Failed to save configuration file", e);
        }
    }

    public static Configuration fromString(String json) {
        Gson gson = new Gson();
        Type token = new TypeToken<Configuration>() {
        }.getType();
        return (gson.fromJson(json, token));
    }


    public String toJson() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Type token = new TypeToken<Configuration>() {
        }.getType();
        return (gson.toJson(this, token));
    }

    public String toPrettyJson() {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        Type token = new TypeToken<Configuration>() {
        }.getType();
        return (gson.toJson(this, token));
    }
}
