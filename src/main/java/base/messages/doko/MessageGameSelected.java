package base.messages.doko;

import base.Constants;
import base.messages.Message;

public class MessageGameSelected extends Message {

    public final static String COMMAND = Constants.DOKO_GAME_SELECTED_COMMAND;

    public MessageGameSelected(int p, GAMES game) {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER, p);
        this.params.addProperty(Constants.GAME, game.value);
    }

    public MessageGameSelected(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public int getPlayerNumber() {
        return params.get(Constants.PLAYER).getAsInt();
    }

    public GAMES getSelectedGame() {
        return GAMES.getName(params.get(Constants.GAME).getAsInt());
    }

    public enum GAMES {
        NORMAL(0),
        DAMEN(1),
        BUBEN(2),
        BUBENDAMEN(3),
        FLEISCHLOS(4),
        KREUZ(5),
        PIK(6),
        HERZ(7),
        KARO(8),
        ARMUT(9),
        KOENIGE(10),
        HOCHZEIT(11);

        public final int value;

        GAMES(int val) {
            this.value = val;
        }

        public static GAMES getName(int val) {
            for (GAMES s : GAMES.values()) {
                if (s.value == val) {
                    return s;
                }
            }
            return NORMAL;
        }
    }
}
