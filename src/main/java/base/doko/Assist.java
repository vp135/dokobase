package base.doko;

import base.BaseCard;
import base.Statics;
import base.doko.messages.MessageGameSelected;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Assist {

    private HashMap<String, List<BaseCard>> map;
    private MessageGameSelected.GAMES selectedGame;

    public static void main(String[] args) {
        System.out.println(DokoCards.ALL_CARDS.get(0).hashCode());
        List<BaseCard> cardList = new ArrayList<>(DokoCards.ALL_CARDS);
        BaseCard card = cardList.get(0);
        System.out.println(card.hashCode());
        cardList.remove(card);
        System.out.println(DokoCards.ALL_CARDS.size());
        System.out.println(cardList.size());


    }

    public Assist(MessageGameSelected.GAMES selectedGame) {
        this.selectedGame = selectedGame;
        //DokoCards.createCardListByGame(selectedGame);
        //map = DevideCards(DokoCards.createCardListByGame(selectedGame));
    }

    private HashMap<String,List<BaseCard>> DevideCards(List<BaseCard> list){
        list.forEach(card -> card.trump = DokoCards.isTrumpf(card, selectedGame));
        HashMap<String,List<BaseCard>> buckets = new HashMap<>();
        buckets.put(Statics.TRUMPF,list.stream().filter(card -> card.trump).collect(Collectors.toList()));
        buckets.get(Statics.TRUMPF).forEach(list::remove);

        buckets.put(Statics.KREUZ,list.stream().filter(card -> card.suit.equals(Statics.KREUZ)).collect(Collectors.toList()));
        buckets.get(Statics.KREUZ).forEach(list::remove);


        buckets.put(Statics.PIK,list.stream().filter(card -> card.suit.equals(Statics.PIK)).collect(Collectors.toList()));
        buckets.get(Statics.PIK).forEach(list::remove);


        buckets.put(Statics.HERZ,list.stream().filter(card -> card.suit.equals(Statics.HERZ)).collect(Collectors.toList()));
        buckets.get(Statics.HERZ).forEach(list::remove);


        buckets.put(Statics.KARO,list.stream().filter(card -> card.suit.equals(Statics.KARO)).collect(Collectors.toList()));
        buckets.get(Statics.KARO).forEach(list::remove);


        return buckets;
    }
}
