package base;

public class SelectedGame extends RequestObject {
    public final static String COMMAND = "SelectedGame";

    public SelectedGame(Player player,String selectedGame) {
        this.command = COMMAND;
        this.params.addProperty("selectedGame", selectedGame);
    }
}
