package base;

import base.Card;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int number;
    private transient Socket socket;
    private boolean spectator;
    private List<Card> hand;
    private boolean waiting4Player;
    private boolean re;
    private int points;
    private List<Stich> stichList;

    Logger log = new Logger(Player.class.getName());

    public Player(String name, int number, Socket socket, boolean spectator, boolean re) {
        this.name = name;
        this.number = number;
        this.socket = socket;
        this.spectator = spectator;
        this.waiting4Player = false;
        this.re = re;
        this.stichList = new ArrayList<>();
    }

    public Player(String name, int number, Socket socket, boolean spectator) {
        this.name = name;
        this.number = number;
        this.socket = socket;
        this.spectator = spectator;
        this.waiting4Player = false;
        this.re = false;
        this.stichList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public boolean isWaiting4Player() {
        return waiting4Player;
    }

    public void setWaiting4Player(boolean waiting4Player) {
        this.waiting4Player = waiting4Player;
    }

    public boolean isRe() {
        return re;
    }

    public void setRe(boolean re) {
        this.re = re;
        log.info(this.getName() + " ist jetzt " + (re?"Re":"Kontra"));
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

    public List<Stich> getStichList() {
        return stichList;
    }

    public void setStichList(List<Stich> stichList) {
        this.stichList = stichList;
    }

    public void addStich(Stich stich){
        stichList.add(stich);
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
        for (Card card : hand) {
            if (card.farbe.equals(farbe) && card.value.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
