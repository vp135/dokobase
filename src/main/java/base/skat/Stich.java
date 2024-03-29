package base.skat;

import base.BaseCard;
import base.Player;
import base.Statics;
import base.skat.messages.GameSelected;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Stich {

    private final transient HashMap<Card, Integer> cardMap = new HashMap<>();
    private int points;
    private int winner;
    private final int stichnumber;
    private Card winningCard;
    private int summe;
    private final List<Player> players;
    private final GameSelected.GAMES gameType;


    public Stich(List<Player> players, int stichnumber, GameSelected.GAMES gameType){
        this.players = players;
        this.stichnumber = stichnumber;
        this.gameType = gameType;
    }

    public void addCard(Player player, Card card){
        card.trumpf = card.isTrumpf(gameType);
        card.order= cardMap.size();
        cardMap.put(card, player.getNumber());
    }

    public HashMap<Card, Integer> getCardMap() {
        return cardMap;
    }

    public HashMap<BaseCard,Integer> getBaseCardMap(){
        HashMap<BaseCard, Integer> baseMap = new HashMap<>();
        cardMap.keySet().forEach(key-> baseMap.put(key,cardMap.get(key)));
        return baseMap;
    }


    public int getWinner() {
        Card currentWinner = cardMap.keySet().stream().filter(card -> card.order==0).findFirst().get();
        if(cardMap.size()==3){
            for(int i = 1; i<cardMap.size();i++){
                int finalI = i;
                Card nextCard = cardMap.keySet().stream().filter(card -> card.order== finalI).findFirst().get();
                switch (gameType) {
                    case Karo:
                        currentWinner = Compare.karo(currentWinner,nextCard);
                        break;
                    case Herz:
                        currentWinner = Compare.herz(currentWinner,nextCard);
                        break;
                    case Pik:
                        currentWinner = Compare.pik(currentWinner,nextCard);
                        break;
                    case Kreuz:
                        currentWinner = Compare.kreuz(currentWinner,nextCard);
                        break;
                    case Null:
                        currentWinner = Compare.nullspiel(currentWinner,nextCard);
                        break;
                    case Ramsch:
                    case Grand:
                        currentWinner = Compare.grand(currentWinner,nextCard);
                        break;
                }
            }
        }
        this.points = calculatePoints();
        winner = cardMap.get(currentWinner);
        winningCard = currentWinner;
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

    public Card getWinningCard(){
        return winningCard;
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
