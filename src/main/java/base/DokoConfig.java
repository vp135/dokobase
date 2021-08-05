package base;

import java.util.HashMap;

public class DokoConfig {

    public HashMap<String,Object>  regeln;
    public HashMap<String,Boolean> sonderpunkte;

    public final static String FUCHS = "fuchsGefangen";
    public final static String HERZSTICH = "herzstich";
    public final static String KARLCHEN = "karlchen";
    public final static String DOPPELKOPF = "doppelkopf";

    public final static String BEDIENEN = "bedienen";


    public DokoConfig() {
        sonderpunkte = new HashMap<>();
        sonderpunkte.put(DOPPELKOPF,true);
        sonderpunkte.put(KARLCHEN,true);
        sonderpunkte.put(HERZSTICH,true);
        sonderpunkte.put(FUCHS,true);

        regeln = new HashMap<>();
        regeln.put("zweiteHerz10",true);
        regeln.put("schwein",true);
        regeln.put(BEDIENEN,true);
    }

}
