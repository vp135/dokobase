package base.doko;

import base.BaseCard;
import base.Statics;
import base.doko.messages.MessageGameSelected;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DokoCards {

    public static boolean isTrumpf(BaseCard card, Object object) {
        MessageGameSelected.GAMES gametype = (MessageGameSelected.GAMES) object;
        switch(gametype) {
            case NORMAL:
            case HOCHZEIT:
            case ARMUT:
            case KARO:
                return ((card.suit.equals(Statics.KARO)
                        || card.kind.equals(Statics.BUBE)
                        || card.kind.equals(Statics.DAME)
                        || (card.kind.equals(Statics.ZEHN) && card.suit.equals(Statics.HERZ))));

            case DAMEN:
                return (card.kind.equals(Statics.DAME));
            case BUBEN:
                return (card.kind.equals(Statics.BUBE));
            case BUBENDAMEN:
                return (card.kind.equals(Statics.DAME)
                ||card.kind.equals(Statics.BUBE));
            case KREUZ:
                return ((card.suit.equals(Statics.KREUZ)
                        || card.kind.equals(Statics.BUBE)
                        || card.kind.equals(Statics.DAME)
                        || (card.kind.equals(Statics.ZEHN) && card.suit.equals(Statics.HERZ))));
            case PIK:
                return ((card.suit.equals(Statics.PIK)
                        || card.kind.equals(Statics.BUBE)
                        || card.kind.equals(Statics.DAME)
                        || (card.kind.equals(Statics.ZEHN) && card.suit.equals(Statics.HERZ))));
            case HERZ:
                return ((card.suit.equals(Statics.HERZ)
                        || card.kind.equals(Statics.BUBE)
                        || card.kind.equals(Statics.DAME)));
            case FLEISCHLOS:
                return false;
        }
        return false;
    }


    public static BaseCard randomCard(List<BaseCard> cards, Random r){
        return cards.get(r.nextInt(40));
    }

    public static BaseCard randomCard(Random r){
        return createCardList().get(r.nextInt(40));
    }

    public static BaseCard randomCard(){
        Random r = new Random(System.currentTimeMillis());
        return createCardList().get(r.nextInt(40));
    }

    public static List<BaseCard> createCardListBySuit(String suit, MessageGameSelected.GAMES game){
        return createCardListByGame(game).stream().filter(card ->
                card.suit.equals(suit) && !card.trump).collect(Collectors.toList());
    }

    public static List<BaseCard> createCardListByGame(MessageGameSelected.GAMES game){
        return new ArrayList<>(Arrays.asList(
                new BaseCard(Statics.ZEHN, Statics.KREUZ,game),
                new BaseCard(Statics.ZEHN, Statics.KREUZ,game),
                new BaseCard(Statics.BUBE, Statics.KREUZ,game),
                new BaseCard(Statics.BUBE, Statics.KREUZ,game),
                new BaseCard(Statics.DAME, Statics.KREUZ,game),
                new BaseCard(Statics.DAME, Statics.KREUZ,game),
                new BaseCard(Statics.KOENIG, Statics.KREUZ,game),
                new BaseCard(Statics.KOENIG, Statics.KREUZ,game),
                new BaseCard(Statics.ASS, Statics.KREUZ,game),
                new BaseCard(Statics.ASS, Statics.KREUZ,game),
        
                new BaseCard(Statics.ZEHN, Statics.PIK,game),
                new BaseCard(Statics.ZEHN, Statics.PIK,game),
                new BaseCard(Statics.BUBE, Statics.PIK,game),
                new BaseCard(Statics.BUBE, Statics.PIK,game),
                new BaseCard(Statics.DAME, Statics.PIK,game),
                new BaseCard(Statics.DAME, Statics.PIK,game),
                new BaseCard(Statics.KOENIG, Statics.PIK,game),
                new BaseCard(Statics.KOENIG, Statics.PIK,game),
                new BaseCard(Statics.ASS, Statics.PIK,game),
                new BaseCard(Statics.ASS, Statics.PIK,game),
        
                new BaseCard(Statics.ZEHN, Statics.HERZ, game),
                new BaseCard(Statics.ZEHN, Statics.HERZ,game),
                new BaseCard(Statics.BUBE, Statics.HERZ,game),
                new BaseCard(Statics.BUBE, Statics.HERZ,game),
                new BaseCard(Statics.DAME, Statics.HERZ,game),
                new BaseCard(Statics.DAME, Statics.HERZ,game),
                new BaseCard(Statics.KOENIG, Statics.HERZ,game),
                new BaseCard(Statics.KOENIG, Statics.HERZ,game),
                new BaseCard(Statics.ASS, Statics.HERZ,game),
                new BaseCard(Statics.ASS, Statics.HERZ,game),
        
                new BaseCard(Statics.ZEHN, Statics.KARO, game),
                new BaseCard(Statics.ZEHN, Statics.KARO,game),
                new BaseCard(Statics.BUBE, Statics.KARO,game),
                new BaseCard(Statics.BUBE, Statics.KARO,game),
                new BaseCard(Statics.DAME, Statics.KARO,game),
                new BaseCard(Statics.DAME, Statics.KARO,game),
                new BaseCard(Statics.KOENIG, Statics.KARO,game),
                new BaseCard(Statics.KOENIG, Statics.KARO,game),
                new BaseCard(Statics.ASS, Statics.KARO,game),
                new BaseCard(Statics.ASS, Statics.KARO,game)
        ));
    }

    public static List<BaseCard> createCardList(){
        List<BaseCard> cardList = new ArrayList<>();

        cardList.add(new BaseCard(Statics.ZEHN, Statics.KREUZ,false));
        cardList.add(new BaseCard(Statics.ZEHN, Statics.KREUZ,false));
        cardList.add(new BaseCard(Statics.BUBE, Statics.KREUZ,true));
        cardList.add(new BaseCard(Statics.BUBE, Statics.KREUZ,true));
        cardList.add(new BaseCard(Statics.DAME, Statics.KREUZ,true));
        cardList.add(new BaseCard(Statics.DAME, Statics.KREUZ,true));
        cardList.add(new BaseCard(Statics.KOENIG, Statics.KREUZ,false));
        cardList.add(new BaseCard(Statics.KOENIG, Statics.KREUZ,false));
        cardList.add(new BaseCard(Statics.ASS, Statics.KREUZ,false));
        cardList.add(new BaseCard(Statics.ASS, Statics.KREUZ,false));

        cardList.add(new BaseCard(Statics.ZEHN, Statics.PIK,false));
        cardList.add(new BaseCard(Statics.ZEHN, Statics.PIK,false));
        cardList.add(new BaseCard(Statics.BUBE, Statics.PIK,true));
        cardList.add(new BaseCard(Statics.BUBE, Statics.PIK,true));
        cardList.add(new BaseCard(Statics.DAME, Statics.PIK,true));
        cardList.add(new BaseCard(Statics.DAME, Statics.PIK,true));
        cardList.add(new BaseCard(Statics.KOENIG, Statics.PIK,false));
        cardList.add(new BaseCard(Statics.KOENIG, Statics.PIK,false));
        cardList.add(new BaseCard(Statics.ASS, Statics.PIK,false));
        cardList.add(new BaseCard(Statics.ASS, Statics.PIK,false));

        cardList.add(new BaseCard(Statics.ZEHN, Statics.HERZ, true));
        cardList.add(new BaseCard(Statics.ZEHN, Statics.HERZ,true));
        cardList.add(new BaseCard(Statics.BUBE, Statics.HERZ,true));
        cardList.add(new BaseCard(Statics.BUBE, Statics.HERZ,true));
        cardList.add(new BaseCard(Statics.DAME, Statics.HERZ,true));
        cardList.add(new BaseCard(Statics.DAME, Statics.HERZ,true));
        cardList.add(new BaseCard(Statics.KOENIG, Statics.HERZ,false));
        cardList.add(new BaseCard(Statics.KOENIG, Statics.HERZ,false));
        cardList.add(new BaseCard(Statics.ASS, Statics.HERZ,false));
        cardList.add(new BaseCard(Statics.ASS, Statics.HERZ,false));

        cardList.add(new BaseCard(Statics.ZEHN, Statics.KARO, true));
        cardList.add(new BaseCard(Statics.ZEHN, Statics.KARO,true));
        cardList.add(new BaseCard(Statics.BUBE, Statics.KARO,true));
        cardList.add(new BaseCard(Statics.BUBE, Statics.KARO,true));
        cardList.add(new BaseCard(Statics.DAME, Statics.KARO,true));
        cardList.add(new BaseCard(Statics.DAME, Statics.KARO,true));
        cardList.add(new BaseCard(Statics.KOENIG, Statics.KARO,true));
        cardList.add(new BaseCard(Statics.KOENIG, Statics.KARO,true));
        cardList.add(new BaseCard(Statics.ASS, Statics.KARO,true));
        cardList.add(new BaseCard(Statics.ASS, Statics.KARO,true));

        return cardList;
    }
}


