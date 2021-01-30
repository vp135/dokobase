package base;

public class Card {
    public int order;
    public String value;
    public String farbe;
    public boolean trumpf;


    public Card(String value, String farbe){
        this.value = value;
        this.farbe = farbe;
        this.trumpf = isTrumpf();
    }

    public Card(String value, String farbe, boolean trumpf) {
        this.value = value;
        this.farbe = farbe;
        this.trumpf = trumpf;
    }

    private boolean isTrumpf() {
        if(this.farbe.equals(Statics.KARO)){
            return true;
        }
        else if(this.value.equals(Statics.BUBE)||this.value.equals(Statics.DAME)){
            return true;
        }
        else if(this.farbe.equals(Statics.HERZ) && this.value.equals(Statics.ZEHN)){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        return farbe+ " "+ value;
    }
}


