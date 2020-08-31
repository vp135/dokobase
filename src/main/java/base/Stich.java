package base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stich {

    private final static String ZEHN = "10";
    private final static String BUBE = "Bube";
    private final static String DAME = "Dame";
    private final static String KOENIG = "Koenig";
    private final static String ASS = "Ass";

    private List<Integer> playerList;

    HashMap<Integer, Card> cardMap = new HashMap<>();


    public HashMap<Integer,Card> getCardMap() {
        return cardMap;
    }

    @Override
    public String toString() {
        return cardMap.get(0)+ "\n"+
                cardMap.get(1)+ "\n"+
                cardMap.get(2)+ "\n"+
                cardMap.get(3);
    }

    public int getWinner(String gameType,int playerFirstCard ,boolean schwein) {
        int currentWinner=playerFirstCard;
        boolean won = true;
        if(cardMap.size()==4){
            List<Card> otherCards = new ArrayList<>();
            int nextCard=playerFirstCard;
            playerList =new ArrayList<>();
            cardMap.keySet().forEach(p->playerList.add(p));
            int maxPlayerNumber= findMax(playerList.get(0),playerList.get(1),playerList.get(2),playerList.get(3));
            while(otherCards.size()<3){
                nextCard++;
                if(nextCard>maxPlayerNumber){
                    nextCard=0;
                }
                if(cardMap.containsKey(nextCard)) {
                    otherCards.add(cardMap.get(nextCard));
                }
            }
            int nextPlayer = playerList.indexOf(currentWinner);
            for (int i =0;i<otherCards.size();i++) {
                nextPlayer++;
                if(nextPlayer>3){
                    nextPlayer=0;
                }
                switch (gameType) {
                    case GameSelected.NORMAL:
                        won = Compare.normalGame(cardMap.get(currentWinner), otherCards.get(i), schwein);
                        break;
                    case GameSelected.DAMEN:
                        won = Compare.damen(cardMap.get(currentWinner), otherCards.get(i));
                        break;
                    case GameSelected.BUBEN:
                        won = Compare.buben(cardMap.get(currentWinner), otherCards.get(i));
                        break;
                    case GameSelected.BUBENDAMEN:
                        won = Compare.bubendamen(cardMap.get(currentWinner), otherCards.get(i));
                        break;
                    case GameSelected.FLEISCHLOS:
                        won = Compare.fleischlos(cardMap.get(currentWinner), otherCards.get(i));
                        break;
                    case GameSelected.KREUZ:
                        won = Compare.kreuz(cardMap.get(currentWinner), otherCards.get(i));
                        break;
                    case GameSelected.PIK:
                        won = Compare.pik(cardMap.get(currentWinner), otherCards.get(i));
                        break;
                    case GameSelected.HERZ:
                        won = Compare.herz(cardMap.get(currentWinner), otherCards.get(i));
                        break;
                    case GameSelected.KARO:
                        won = Compare.karo(cardMap.get(currentWinner), otherCards.get(i), schwein);
                        break;
                    case GameSelected.ARMUT:
                        won = Compare.normalGame(cardMap.get(currentWinner),otherCards.get(i),schwein);
                        break;
                }
                if (!won) {
                    currentWinner = playerList.get(nextPlayer);
                }
            }
        }
        return currentWinner;
    }

    public int calculatePoints(){
        int result=0;
        for (Card card:this.cardMap.values()){
            switch (card.value){
                case ZEHN:{
                    result+=10;
                    break;
                }
                case BUBE:{
                    result+=2;
                    break;
                }
                case DAME:{
                    result+=3;
                    break;
                }
                case KOENIG:{
                    result+=4;
                    break;
                }
                case ASS:{
                    result+=11;
                    break;
                }
            }
        }
        return result;
    }

    private int findMax(int... vals) {
        int max = Integer.MIN_VALUE;

        for (int d : vals) {
            if (d > max) max = d;
        }

        return max;
    }
}
