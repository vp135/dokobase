package base;

public abstract class BaseCard {

    public int order;
    public String value;
    public String farbe;
    public boolean trumpf;

    public BaseCard(String value, String farbe, boolean trumpf) {
        this.value = value;
        this.farbe = farbe;
        this.trumpf = trumpf;
    }

    protected abstract boolean isTrumpf(Object object);
}
