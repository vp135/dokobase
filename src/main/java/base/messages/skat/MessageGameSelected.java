package base.messages.skat;

import base.Constants;
import base.messages.Message;

public class MessageGameSelected extends Message {
    public final static String COMMAND = Constants.SKAT_GAME_SELECTED_COMMAND;

    public MessageGameSelected(String player, GAMES game, boolean hand, boolean ouvert) {
        this.command = COMMAND;
        this.params.addProperty(Constants.PLAYER, player);
        this.params.addProperty(Constants.GAME, game.value);
        this.params.addProperty(Constants.HAND, hand);
        this.params.addProperty(Constants.OUVERT, ouvert);
    }

    public MessageGameSelected(Message message) {
        this.command = COMMAND;
        this.params = message.getParams();
    }

    public String getPlayerName() {
        return params.get(Constants.PLAYER).getAsString();
    }

    public GAMES getSelectedGame() {
        return GAMES.getName(params.get(Constants.GAME).getAsInt());
    }

    public boolean gethand() {
        return params.get(Constants.HAND).getAsBoolean();
    }

    public boolean getOuvert() {
        return params.get(Constants.OUVERT).getAsBoolean();
    }


    public enum GAMES {
        Ramsch(0),
        Karo(9),
        Herz(10),
        Pik(11),
        Kreuz(12),
        Null(23),
        Grand(24);


        private final int value;

        GAMES(int value) {
            this.value = value;
        }

        public static GAMES getName(int val) {
            for (GAMES s : GAMES.values()) {
                if (s.value == val) {
                    return s;
                }
            }
            return Ramsch;
        }
    }
}
