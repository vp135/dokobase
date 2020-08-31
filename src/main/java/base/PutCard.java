package base;

import com.google.gson.JsonObject;

public class PutCard extends RequestObject {

    public final static String COMMAND = "PutCard";


    public PutCard(String farbe, String wert) {
        this.command = COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("farbe",farbe);
        this.params.addProperty("wert",wert);
    }
}
