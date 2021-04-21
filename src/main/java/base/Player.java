package base;

import java.net.Socket;
import java.util.List;

public class Player {
    private final String name;
    private final int number;
    private transient Socket socket;
    private boolean spectator;
    private List<BaseCard> hand;
    private boolean re;
    private int points;

    private final Logger log = new Logger("Players",3,true);


    public Player(String name, int number, Socket socket, boolean spectator, boolean re) {
        this.name = name;
        this.number = number;
        this.socket = socket;
        this.spectator = spectator;
        this.re = re;
    }

    public Player(String name, int number, Socket socket, boolean spectator) {
        this.name = name;
        this.number = number;
        this.socket = socket;
        this.spectator = spectator;
        this.re = false;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public boolean isSpectator() {
        return spectator;
    }

    public void setSpectator(boolean spectator) {
        this.spectator = spectator;
    }

    public List<BaseCard> getHand() {
        return hand;
    }

    public void setHand(List<BaseCard> hand) {
        this.hand = hand;
    }

    public boolean isRe() {
        return re;
    }

    public void setRe(boolean re) {
        this.re = re;
    }

    public void setRe(boolean re, String sender){
        this.re = re;
        if(re){
            log.info(this.getName()+ " is Re. Reason: "+ sender);
        }
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void addPoints(int points){
        this.points+=points;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("<html>"+name + "<br>" + number + "<br>");
        if(hand!= null) {
            hand.forEach(card -> s.append(card.toString()).append("<br>"));
        }
        return s.toString();
    }

    public boolean hasCard(String farbe, String value){
        for (BaseCard card : hand) {
            if (card.farbe.equals(farbe) && card.value.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
