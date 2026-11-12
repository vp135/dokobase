package base.messages.doko;

import base.Constants;
import base.game.Card;
import base.game.doko.DokoCards;
import base.messages.Message;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class MessageSendCards extends Message {
    public final static String COMMAND = Constants.DOKO_SEND_CARDS_COMMAND;
    public final static String RICH = Constants.DOKO_SEND_CARDS_RICH;
    public final static String POOR = Constants.DOKO_SEND_CARDS_POOR;

    public MessageSendCards(List<Card> cards, String receiver) {
        this.command = COMMAND;
        JsonArray array = new JsonArray();
        cards.forEach(card -> array.add(card.cardNumber));
        this.params.add(Constants.CARDS, array);
        this.params.addProperty(Constants.RECEIVER, receiver);
    }

    public MessageSendCards(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public List<Card> getCards() {
        List<Card> list = new ArrayList<>();
        this.params.get(Constants.CARDS).getAsJsonArray().forEach(j -> list.add(DokoCards.ALL_CARDS.get(j.getAsInt())));
        return list;
    }

    public String getReceiver() {
        return this.params.get(Constants.RECEIVER).getAsString();
    }
}
