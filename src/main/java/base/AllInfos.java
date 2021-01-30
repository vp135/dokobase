package base;

import com.google.gson.*;

import java.util.List;

public class AllInfos extends RequestObject{

    public final static String COMMAND = "AllInfos";

    public AllInfos(Player player,
                    List<Card> cardList,
                    boolean waiting4Player,
                    int currentPlayer,
                    boolean wait4NextRound,
                    boolean wait,
                    String selectedGame,
                    List<Card> armutCards) {
        this.command = COMMAND;
        this.params.add("cards",new Gson().toJsonTree(cardList));
        this.params.addProperty("wait",waiting4Player);
        this.params.addProperty("currentPlayer",currentPlayer);
        this.params.addProperty("wait4NextRound",wait4NextRound);
        this.params.addProperty("wait4Gesund",wait);
        this.params.addProperty("selectedGame",selectedGame);
        this.params.add("armutCards", new Gson().toJsonTree(armutCards));
    }
}
