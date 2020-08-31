package base;

import com.google.gson.JsonObject;

public class SelectCards4Armut extends RequestObject {
    public final static String COMMAND = "SelectCards4Armut";

    public SelectCards4Armut() {
        this.command =COMMAND;
        this.params = new JsonObject();
        this.params.addProperty("message","Alle Trümpfe auswählen");

    }
}
