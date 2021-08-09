package base;

import java.util.HashMap;

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

    public static class Regeln{
        public boolean zweiteHerz10 = true;
        public boolean schwein = true;
        public boolean bedienen = false;
    }
}
