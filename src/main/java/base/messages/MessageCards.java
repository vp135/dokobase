package base.messages;

import base.BaseCard;
import base.Statics;
import base.doko.DokoCards;
import base.doko.messages.MessageGameSelected;
import base.skat.SkatCards;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class MessageCards extends Message {

    public final static String COMMAND ="Cards";

    public MessageCards(List<BaseCard> cardList) {
        this.command = COMMAND;
        JsonArray array = new JsonArray();
        cardList.forEach(p->array.add(p.cardNumber));
        params.add("cards",array);
    }

    public MessageCards(Message message){
        this.command = COMMAND;
        this.params = message.getParams();
    }


    public List<BaseCard> getCards(Statics.game game){
        List<BaseCard> list = new ArrayList<>();
        this.params.get("cards").getAsJsonArray().forEach(j->{
            switch (game) {
                case DOKO:
                    BaseCard card = DokoCards.ALL_CARDS.get(j.getAsInt());
                    card.trump = DokoCards.isTrumpf(card, MessageGameSelected.GAMES.NORMAL);
                    list.add(card);
                    break;
                case SKAT:
                    list.add(SkatCards.ALL_CARDS.get(j.getAsInt()));
                    break;
                default:
                    list.add(new BaseCard(Statics.DAME,Statics.KARO,-1,0));
                    break;
            }
        });
        return list;
    }

}
