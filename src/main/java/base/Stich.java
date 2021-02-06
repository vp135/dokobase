package base;

import java.util.HashMap;

public class Stich {


    private final transient HashMap<Card, Integer> cardMap = new HashMap<>();
    private int points;
    private int winner;


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
        //cardMap.get(currentWinner).addStich(this);
        //player = cardMap.get(currentWinner).getName();
        this.points = calculatePoints();
        winner = cardMap.get(currentWinner);
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
        return result;
    }

    public int getPoints() {
        return points;
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
