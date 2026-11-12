package base.messages.skat;

import base.Constants;
import base.game.Card;
import base.game.skat.SkatCards;
import base.messages.Message;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

public class MessageSkatCards extends Message {

    public final static String OUVERT = Constants.SKAT_SKAT_CARDS_OUVERT;
    public final static String SKAT = Constants.SKAT;

    public MessageSkatCards(String command, String player, List<Card> cards) {
        this.command = command;
        this.params.addProperty(Constants.PLAYER, player);
        JsonArray array = new JsonArray();
        cards.forEach(p -> array.add(p.cardNumber));
        params.add(Constants.CARDS, array);
    }

    public MessageSkatCards(Message message) {
        this.command = message.getCommand();
        this.params = message.getParams();
    }

    public static MessageSkatCards OuvertCards(String player, List<Card> cards) {
        return new MessageSkatCards(OUVERT, player, cards);
    }

    public static MessageSkatCards SkatCards(String player, List<Card> cards) {
        return new MessageSkatCards(SKAT, player, cards);
    }

    public List<Card> getCards() {
        List<Card> list = new ArrayList<>();
        this.params.get(Constants.CARDS).getAsJsonArray().forEach(j -> list.add(SkatCards.ALL_CARDS.get(j.getAsInt())));
        return list;
    }

    public String getPlayerName() {
        return params.get(Constants.PLAYER).getAsString();
    }

}
