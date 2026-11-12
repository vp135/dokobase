package base.messages;

import base.Constants;
import base.Statics;
import base.game.Card;
import base.game.doko.DokoCards;
import base.game.skat.SkatCards;

public class MessagePutCard extends Message {

    public final static String COMMAND = Constants.PUT_CARD_COMMAND;

    public MessagePutCard(int player, Card card) {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER, player);
        this.params.addProperty("suit", card.suit);
        this.params.addProperty("kind", card.kind);
        this.params.addProperty(Constants.CARD_NUMBER, card.cardNumber);
    }

    public MessagePutCard(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }


    public Card getCard(Statics.game game) {
        switch (game) {
            case DOKO:
                return DokoCards.ALL_CARDS.get(params.get(Constants.CARD_NUMBER).getAsInt());
            case SKAT:
                return SkatCards.ALL_CARDS.get(params.get(Constants.CARD_NUMBER).getAsInt());
            default:
                return new Card(Statics.DAME, Statics.KARO, 0, 0);
        }
    }

    public int getPlayerNumber() {
        return this.params.get(Constants.PLAYER).getAsInt();
    }
}
