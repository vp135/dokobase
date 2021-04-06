package base.skat.messages;

import base.messages.RequestObject;

public class GameSelected extends RequestObject {

    public enum GAMES{
        UNDEFINED(0),
        KARO (9),
        HERZ (10),
        PIK (11),
        KREUZ(12),
        NULL(23),
        GRAND(24),
        NULL_HAND(35),
        NULL_OUVERT(46),
        NULL_OUVERT_HAND(59);


        private final int value;
        GAMES(int value) {
            this.value = value;
        }

        public static GAMES getName(int val){
            for (GAMES s: GAMES.values()){
                if(s.value==val){
                    return s;
                }
            }
            return UNDEFINED;
        }
    }
}
