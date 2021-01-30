package base;

import java.util.HashMap;
import java.util.List;

public class AllCurrentInfos extends RequestObject {

    private Stich tisch;
    private int currentPlayer;
    private List<Card> hand;
    private List<Stich> stichList;
    private HashMap<String,String> labelMap;

    public AllCurrentInfos(Player player) {
    }
}
