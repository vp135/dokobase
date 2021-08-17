package base;

import base.messages.Message;

import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Player {
    private final String name;
    private final int number;
    private transient Socket socket;
    private boolean spectator;
    private List<Card> hand;
    private boolean re;
    private int points;
    private boolean admin;
    public PLAYER_STATE state = PLAYER_STATE.IDLE;

    private final ConcurrentLinkedDeque<Message> outMessages = new ConcurrentLinkedDeque<>();
    private final AutoResetEvent evOut = new AutoResetEvent(true);
    private ComServer comServer;
    public static final long TIMEOUT = 1000;


    private final Logger log = new Logger("Players",3,true);

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }


    public enum PLAYER_STATE {
        IDLE(0),
        SELECTING_GAME(1),
        SELECTING_CARDS_GAME(2),
        SELECTING_RAMSCH(3),
        SELECTING_CARDS_RAMSCH(4),
        SELECTING_ARMUT(5),
        PLAY_CARD(6),
        END(7);

        private final int value;
        PLAYER_STATE(int value) {
            this.value = value;
        }
    }

    public Player(String name, int number, Socket socket, boolean spectator, ComServer comServer, boolean re) {
        this.name = name;
        this.number = number;
        this.socket = socket;
        this.spectator = spectator;
        this.re = re;
        this.comServer =comServer;
        outMessageHandling();
    }

    public Player(String name, int number, Socket socket, boolean spectator, ComServer comServer) {
        this.name = name;
        this.number = number;
        this.socket = socket;
        this.spectator = spectator;
        this.re = false;
        this.comServer = comServer;
        outMessageHandling();
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

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
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
        for (Card card : hand) {
            if (card.suit.equals(farbe) && card.kind.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public ConcurrentLinkedDeque<Message> getOutMessages() {
        return outMessages;
    }

    public void queue(Message message){
        outMessages.add(message);
        evOut.set();
    }

    public void outMessageHandling(){
        new Thread(() -> {
            while (true){
                try {
                    evOut.waitOne(TIMEOUT);
                    while(outMessages.peek()!=null){
                        if(comServer.sendReply(this.socket, outMessages.peek())) {
                            outMessages.poll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void removeCard(String farbe,String wert) {
        hand.stream().filter(baseCard -> baseCard.suit.equals(farbe) && baseCard.kind.equals(wert)).findFirst()
                .ifPresent(baseCard -> hand.remove(baseCard));
    }

    public void removeCard(Card card){
        removeCard(card.suit,card.kind);
    }
}
