package base;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Stich {

    private final static Logger log = new Logger("Stich",3, true);

    private final transient HashMap<Card, Integer> cardMap = new HashMap<>();
    private int points;
    private int winner;
    private final int stichnumber;
    private int doppelkopf =0 ;
    private int karlchen = 0;
    private int fuchsGefangen = 0;
    private int herzstich = 0;
    private Card winningCard;
    private int summe;
    private final List<Player> players;


    public Stich(List<Player> players, int stichnumber){
        this.players = players;
        this.stichnumber = stichnumber;
    }

    public void addCard(Player player, Card card){
        card.order= cardMap.size();
        cardMap.put(card, player.getNumber());
    }

    public HashMap<Card, Integer> getCardMap() {
        return cardMap;
    }

    public int getWinner(){
        return winner;
    }

    public int getWinner(String gameType,boolean schwein) {
        Card currentWinner = cardMap.keySet().stream().filter(card -> card.order==0).findFirst().get();
        if(cardMap.size()==4){
            for(int i = 1; i<cardMap.size();i++){
                int finalI = i;
                Card nextCard = cardMap.keySet().stream().filter(card -> card.order== finalI).findFirst().get();
                switch (gameType) {
                    case GameSelected.NORMAL:
                    case GameSelected.ARMUT:
                    case GameSelected.KARO:
                        currentWinner = Compare.normalGame(currentWinner, nextCard, schwein);
                        break;
                    case GameSelected.DAMEN:
                        currentWinner = Compare.damen(currentWinner, nextCard);
                        break;
                    case GameSelected.BUBEN:
                        currentWinner = Compare.buben(currentWinner, nextCard);
                        break;
                    case GameSelected.BUBENDAMEN:
                        currentWinner = Compare.bubendamen(currentWinner, nextCard);
                        break;
                    case GameSelected.FLEISCHLOS:
                        currentWinner = Compare.fleischlos(currentWinner, nextCard);
                        break;
                    case GameSelected.KREUZ:
                        currentWinner = Compare.kreuz(currentWinner, nextCard);
                        break;
                    case GameSelected.PIK:
                        currentWinner = Compare.pik(currentWinner, nextCard);
                        break;
                    case GameSelected.HERZ:
                        currentWinner = Compare.herz(currentWinner, nextCard);
                        break;
                }
            }
        }
        this.points = calculatePoints();
        winner = cardMap.get(currentWinner);
        winningCard = currentWinner;
        check4ExtraPoints();
        return cardMap.get(currentWinner);
    }

    public int calculatePoints(){
        int result=0;
        for (Card card:this.cardMap.keySet()){
            switch (card.value){
                case Statics.ZEHN:{
                    result+=10;
                    break;
                }
                case Statics.BUBE:{
                    result+=2;
                    break;
                }
                case Statics.DAME:{
                    result+=3;
                    break;
                }
                case Statics.KOENIG:{
                    result+=4;
                    break;
                }
                case Statics.ASS:{
                    result+=11;
                    break;
                }
            }
        }
        summe = result;
        return result;
    }

    public int getPoints() {
        return points;
    }

    public String getExtraPoints(){
        StringBuilder builder = new StringBuilder();
        if (doppelkopf>0){
            builder.append("Doppelkopf");
        }
        if (karlchen>0){
            if(builder.length()>0){
                builder.append(";");
            }
            builder.append("Karlchen");
        }
        if (herzstich>0){
            if(builder.length()>0){
                builder.append(";");
            }
            builder.append("Herzstich");
        }
        if (fuchsGefangen>0){
            if(builder.length()>0){
                builder.append(";");
            }
            builder.append("Fuchs gefangen(").append(fuchsGefangen).append(")");
        }
        if(builder.length()>0){
            return "("+builder.toString()+")";
        }
        else {
            return "";
        }
    }

    public void check4ExtraPoints(){
        checkKarlchen();
        checkDoppelkopf();
        checkHerzStich();
        checkFuchsGefangen();
    }

    private void checkFuchsGefangen() {
        fuchsGefangen = 0;
        cardMap.keySet().stream().filter(card -> (card.farbe.equals(Statics.KARO) && card.value.equals(Statics.ASS)))
                .collect(Collectors.toList()).forEach(card -> {
            if (players.get(cardMap.get(card)).isRe() != players.get(winner).isRe()) {
                fuchsGefangen++;
                log.info("Fuchs gefangen");
            }
        });
    }

    private void checkHerzStich() {
        if(winningCard.farbe.equals(Statics.HERZ) && !winningCard.trumpf){
            herzstich = 1;
        }
    }

    private void checkDoppelkopf() {
        if(summe>39){
            doppelkopf = 1;
            log.info("Doppelkopf");
        }
    }

    private void checkKarlchen() {
        if(stichnumber==9){
            if(winningCard.farbe.equals(Statics.KREUZ) && winningCard.value.equals(Statics.BUBE)){
                karlchen = 1;
                log.info("Karlchen");
            }
        }
    }



    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        cardMap.keySet().forEach(card -> s
                .append(cardMap.get(card))
                .append(":")
                .append(card.farbe)
                .append(" ")
                .append(card.value)
                .append("\n"));
        return s.toString();
    }
}
