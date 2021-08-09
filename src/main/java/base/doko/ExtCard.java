package base.doko;

import base.BaseCard;

public class ExtCard extends BaseCard {

    private int state;
    private boolean hand;

    public ExtCard (BaseCard card){
        super(card.kind, card.suit, 0,0);
        this.state =0;
    }
}
