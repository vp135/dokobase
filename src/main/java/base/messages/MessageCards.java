package base.messages;

import base.Statics;
import base.game.Card;
import base.game.doko.DokoCards;
import base.game.skat.SkatCards;
import base.messages.doko.MessageGameSelected;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class MessageCards extends Message {

    public final static String COMMAND = "Cards";

    public MessageCards(List<Card> cardList) {
        this.command = COMMAND;
        JsonArray array = new JsonArray();
        cardList.forEach(p -> array.add(p.cardNumber));
        params.add("cards", array);
    }

    public MessageCards(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }


    public List<Card> getCards(Statics.game game) {
        List<Card> list = new ArrayList<>();
        this.params.get("cards").getAsJsonArray().forEach(j -> {
            switch (game) {
                case DOKO:
                    Card card = DokoCards.ALL_CARDS.get(j.getAsInt());
                    card.trump = DokoCards.isTrumpf(card, MessageGameSelected.GAMES.NORMAL);
                    list.add(card);
                    break;
                case SKAT:
                    list.add(SkatCards.ALL_CARDS.get(j.getAsInt()));
                    break;
                default:
                    list.add(new Card(Statics.DAME, Statics.KARO, -1, 0));
                    break;
            }
        });
        return list;
    }

}
