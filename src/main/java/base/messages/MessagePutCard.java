package base.messages;

import base.Card;
import base.Statics;
import base.doko.DokoCards;
import base.skat.SkatCards;

public class MessagePutCard extends Message {

    public final static String COMMAND = "PutCard";

    public MessagePutCard(int player, Card card){
        this.command = COMMAND;
        this.params.addProperty("player",player);
        this.params.addProperty("suit",card.suit);
        this.params.addProperty("kind",card.kind);
        this.params.addProperty("cardNumber", card.cardNumber);
    }

    public MessagePutCard(Message message){
        this.command = COMMAND;
        this.params = message.getParams();
    }


    public Card getCard(Statics.game game){
        switch (game){
            case DOKO:
                return DokoCards.ALL_CARDS.get(params.get("cardNumber").getAsInt());
            case SKAT:
                return SkatCards.ALL_CARDS.get(params.get("cardNumber").getAsInt());
            default:
                return new Card(Statics.DAME,Statics.KARO,0,0);
        }
    }

    public int getPlayerNumber(){
        return this.params.get("player").getAsInt();
    }
}
