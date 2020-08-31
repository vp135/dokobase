package base;

public class Card {
    public int order;
    public String value;
    public String farbe;

    public Card(String value, String farbe) {
        this.value = value;
        this.farbe = farbe;
    }

    @Override
    public String toString() {
        return farbe+ " "+ value;
    }
}


