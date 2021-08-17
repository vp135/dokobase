package base.doko;

import base.Card;

public class ExtCard extends Card {

    private int state;
    private boolean hand;

    public ExtCard (Card card){
        super(card.kind, card.suit, 0,0);
        this.state =0;
    }
}
