package base.skat.messages;

import base.messages.Message;

public class MessageGameEnd extends Message {
    public final static String COMMAND = "GameEnd";

    public MessageGameEnd(String re1, String kontra1, String player1, String player2, String player3, int remain){
        this.command = COMMAND;
        this.params.addProperty("re1",re1);
        this.params.addProperty("kontra1", kontra1);
        this.params.addProperty("player1", player1);
        this.params.addProperty("player2", player2);
        this.params.addProperty("player3", player3);
        this.params.addProperty("remain", remain);
    }
}
