package base.messages;

import base.Constants;
import base.Statics;
import base.game.Card;
import base.game.doko.DokoCards;
import base.game.skat.SkatCards;
import base.messages.doko.MessageGameSelected;
import com.google.gson.JsonArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MessageCards extends Message {

    private static final Logger log = LoggerFactory.getLogger(MessageCards.class);
    public final static String COMMAND = Constants.CARDS_COMMAND;

    public MessageCards(List<Card> cardList) {
        this.command = COMMAND;
        JsonArray array = new JsonArray();
        cardList.forEach(p -> array.add(p.cardNumber));
        params.add(Constants.CARDS, array);
    }

    public MessageCards(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }


    public List<Card> getCards(Statics.game game) {
        List<Card> list = new ArrayList<>();
        this.params.get(Constants.CARDS).getAsJsonArray().forEach(j -> {
            switch (game) {
                case DOKO:
                    Card card = DokoCards.ALL_CARDS.get(j.getAsInt());
                    card.trump = DokoCards.isTrumpf(card, MessageGameSelected.GAMES.NORMAL);
                    log.debug("Card {} is trump: {}", card, card.trump);
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
