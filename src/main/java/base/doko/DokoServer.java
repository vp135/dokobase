package base.doko;

import base.*;
import base.doko.messages.*;
import base.messages.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static base.doko.messages.MessageGameSelected.GAMES.*;

public class DokoServer extends BaseServer {

    Random random = new Random(System.currentTimeMillis());

    private List<Stich> stichList = new ArrayList<>();
    private Stich stich;
    private MessageGameSelected.GAMES selectedGame = NORMAL;


    private boolean wait4Gesund = false;
    private boolean schwein = false;
    private List<Card> armutCards;
    private HashMap<Integer, MessageGameSelected.GAMES> gameSelection = new HashMap<>();
    private int aufspieler = -1;
    private int armutplayer = -1;
    private int hochzeitSpieler = -1;
    private List<Player> players2Ask = new ArrayList<>();
    private boolean wait4Partner;


    public DokoServer(BaseServer server){
        super(server.c, server.comServer);
        gameType = Statics.game.DOKO;
        spectator = 4;
        prepareGameServer(server);
    }


    @Override
    public void handleInput(MessageIn message) {
        super.handleInput(message);
        Message requestObject = Message.fromString(message.getInput());
        try {
            switch (requestObject.getCommand()) {
                case MessageGameSelected.COMMAND:
                    handleGameSelected(requestObject);
                    break;
                case MessageCurrentStich.LAST:
                    handleCurrentStich(requestObject);
                    break;
                case MessageSendCards.COMMAND:
                    handleSendCards(requestObject);
                    break;
                case MessageGetArmut.COMMAND:
                    handleGetArmut(requestObject);
                    break;
                case MessageSchweinExists.COMMAND:
                    schwein = true;
                    break;
                case MessageCardsReturned.COMMAND:
                    runGame(beginner);
                    break;
            }
        }catch (Exception ex){
            log.error(requestObject +" => " + ex.toString());
        }
    }


    private void handleCurrentStich(Message requestObject) {
        if (stichList.size() > 0) {
            try {
                MessageCurrentStich cs = new MessageCurrentStich(stichList.get(stichList.size() - 1).getCardMap(), MessageCurrentStich.LAST);
                queueOut(players.get(new MessageCurrentStich(requestObject).getPlayerNumber()), cs);
            } catch (Exception ex) {
                log.warn(ex.toString());
            }
        }
    }


    private void handleGetArmut(Message requestObject) {
        MessageGetArmut messageGetArmut = new MessageGetArmut(requestObject);
        if (messageGetArmut.getsArmut()) {
            queueOut(players.get(messageGetArmut.getPlayerNumber()),
                    new MessageSendCards(armutCards, MessageSendCards.RICH));
            players.get(messageGetArmut.getPlayerNumber()).setRe(true, "ist arm");
            players.stream().filter(player -> player.getNumber() != messageGetArmut.getPlayerNumber())
                    .collect(Collectors.toList()).forEach(player -> queueOut(player, new MessageDisplayMessage(
                            Strings.getString(Strings.ARMUT_ACCEPT,
                                    players.get(messageGetArmut.getPlayerNumber()).getName()))));
        } else {
            send2All(new MessageDisplayMessage(
                    Strings.getString(Strings.ARMUT_REFUSE,
                            players.get(messageGetArmut.getPlayerNumber()).getName())));
            askNextPlayer2GetArmut();
        }
    }


    private void handleSendCards(Message requestObject) {
        MessageSendCards messageSendCards = new MessageSendCards(requestObject);
        armutCards = new ArrayList<>();
        if (messageSendCards.getReceiver().equals(MessageSendCards.RICH)) {
            messageSendCards.getCards().forEach(card -> {
                card.trump = DokoCards.isTrumpf(card, selectedGame);
                armutCards.add(card);
            });
            askNextPlayer2GetArmut();
        } else if (messageSendCards.getReceiver().equals(MessageSendCards.POOR)) {
            send2All(new MessageDisplayMessage(Strings.getString(Strings.ARMUT_RETURN,
                    players.get(armutplayer).getName(),
                    messageSendCards.getCards().stream().filter(card -> card.trump).count())));
            queueOut(players.get(armutplayer),
                    requestObject);
            players2Ask = new ArrayList<>();
        }
    }


    private void handleGameSelected(Message requestObject) {
        if (wait4Gesund) {
            MessageGameSelected messageGameSelected = new MessageGameSelected(requestObject);
            gameSelection.put(messageGameSelected.getPlayerNumber(), messageGameSelected.getSelectedGame());
            if (messageGameSelected.getSelectedGame() == KOENIGE) {
                send2All(new MessageDisplayMessage(
                        Strings.getString(Strings.KOENIGE,
                                players.get(messageGameSelected.getPlayerNumber()).getName())));
            }
        }
        if (gameSelection.keySet().size() > 3) {
            setGameToPlay(gameSelection);
        } else {
            StringBuilder s = new StringBuilder("Warte auf " + (4 - gameSelection.size()) + " Spieler");
            players.forEach(player -> {
                if (gameSelection.containsKey(player.getNumber())) {
                    queueOut(player, new MessageDisplayMessage(s.toString()));
                }
            });
        }
    }


    @Override
    protected void handlePutCard(Message message) {
        MessagePutCard messagePutCard = new MessagePutCard(message);

        if (stich == null || stich.getCardMap().size() > 3) {
            stich = new Stich(players, currentStichNumber, selectedGame,c.doko);
            currentStichNumber++;
        }
        Card card = messagePutCard.getCard(Statics.game.DOKO);
        card.trump = DokoCards.isTrumpf(card,selectedGame);
        stich.addCard(players.get(messagePutCard.getPlayerNumber()), card);
        players.get(messagePutCard.getPlayerNumber()).removeCard(card);
        send2All(message);
        currentPlayer++;
        if(currentPlayer==spectator){
            currentPlayer++;
        }
        if (currentPlayer >= players.size()) {
            currentPlayer = 0;
        }
        if(currentPlayer==spectator){
            currentPlayer++;
        }

        if (stich.getCardMap().size() > 3) {
            try {
                int winner = stich.getWinner(schwein);

                stichList.add(stich);
                try{
                    points.put(winner, points.get(winner) + stich.calculatePoints());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                currentPlayer = winner;
                if(wait4Partner) {
                    if(currentStichNumber<4) {
                        if (hochzeitSpieler != winner) {
                            players.get(winner).setRe(true, "Ehepartner");
                            wait4Partner = false;
                        }
                    }
                    else {
                        wait4Partner = false;
                        log.info("kein Ehepartner gefunden");
                    }
                }
                send2All(new MessageUpdateUserPanel(players.stream().filter(p->p.getNumber()==winner)
                        .findFirst().get().getName()," hat Stich(e)"));
                if (currentStichNumber > 9) {
                    endIt();
                    return;
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        if (currentStichNumber < 11) {
            send2All(new MessageWait4Player(players.stream().filter(p->p.getNumber()==currentPlayer).findFirst().get().getName()));
        }
    }


    @Override
    public void endIt() {
        super.endIt();
        DokoEndDialog endDialog = new DokoEndDialog(players, stichList);
        send2All(new MessageGameEnd(endDialog.getReString1(), endDialog.getReString2(), endDialog.getKontraString1(), endDialog.getKontraString2(), endDialog.getRemaining()));
        wait4NextRound= true;
        readyMap = new HashMap<>();
        for (int i=0;i<players.size();i++){
            readyMap.put(i,false);
        }
    }


    @Override
    protected void runGame(int player){
        super.runGame(player);
        aufspieler = -1;
        send2All(new MessageGameSelected(player,selectedGame));
        send2All(new MessageWait4Player(players.stream().filter(p -> p.getNumber()==player).findAny().get().getName()));
    }


    @Override
     protected void nextGame() {
        if(selectedGame==NORMAL
                ||selectedGame==ARMUT){
            beginner++;
            if(beginner>players.size()-1){
                beginner=0;
            }
            if(players.size()>4) {
                spectator = beginner-1;
                if (spectator<0){
                    spectator = players.size()-1;
                }
            }
            players.forEach(player ->{
                player.setSpectator(false);
                player.setRe(false);
            });
            wait4Partner = false;
            hochzeitSpieler = -1;
            if(players.size()>4) {
                players.get(spectator).setSpectator(true);
            }
        }
        send2All(new MessageAnnounceSpectator(spectator,beginner));
        shuffleCards();
    }


    private void createListOfPotentialPartners(){
        players2Ask = new ArrayList<>();
        log.info("Armutplayer= " + armutplayer);
        int korrValue = 1;
        for(int i=0;i<players.size()-1;i++){
            log.info("korrValue= " + korrValue);
            log.info("i= " + i);
            int p = i+armutplayer+korrValue;
            log.info("p= " + p);
            if (p>players.size()-1){
                korrValue -= players.size();
                p = i+armutplayer+korrValue;
            }
            if(!players.get(p).isSpectator()){
                players2Ask.add(players.get(p));
            }
        }
    }

    private void askNextPlayer2GetArmut(){
        if(players2Ask.size()>0) {
            log.info("Ask " + players2Ask.get(0));
            queueOut(players2Ask.get(0), new MessageGetArmut());
            players2Ask.remove(0);
        }
        else{
            send2All(new MessageDisplayMessage(Strings.getString(Strings.NEU_GEBEN)));
            shuffleCards();
        }

    }

    private void setGameToPlay(HashMap<Integer, MessageGameSelected.GAMES> selection) {
        wait4Gesund = false;
        players.forEach(player -> player.setRe(false));
        if(selection.values().stream().allMatch(p->p==NORMAL)){
            selectedGame = NORMAL;
            aufspieler=-1;
            players.forEach(player -> {
                if(player.hasCard(Statics.KREUZ,Statics.DAME)){
                    player.setRe(true, "hat Kreuz Dame");
                }
            });
            send2All(new MessageDisplayMessage(Strings.getString(Strings.NORMALES_SPIEL)));
            runGame(beginner);
        }
        else if(selection.containsValue(KOENIGE)){
            int index =-1;
            for (int i: selection.keySet()){
                if(selection.get(i)==KOENIGE){
                    index= i;
                }
            }
            int finalIndex = index;
            send2All(new MessageDisplayMessage(Strings.getString(Strings.KOENIGE, players.get(finalIndex).getName())));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            shuffleCards();
        }
        else if(selection.containsValue(DAMEN)
                || selection.containsValue(BUBEN)
                || selection.containsValue(BUBENDAMEN)
                || selection.containsValue(FLEISCHLOS)
                || selection.containsValue(KREUZ)
                || selection.containsValue(PIK)
                || selection.containsValue(HERZ)
                || selection.containsValue(KARO)){
            int checkPlayer = beginner;
            while(true){
                if(selection.containsKey(checkPlayer)
                        && (selection.get(checkPlayer).equals(DAMEN)
                        ||selection.get(checkPlayer).equals(BUBEN)
                        ||selection.get(checkPlayer).equals(BUBENDAMEN)
                        ||selection.get(checkPlayer).equals(FLEISCHLOS)
                        ||selection.get(checkPlayer).equals(KREUZ)
                        ||selection.get(checkPlayer).equals(PIK)
                        ||selection.get(checkPlayer).equals(HERZ)
                        ||selection.get(checkPlayer).equals(KARO))){
                    selectedGame = selection.get(checkPlayer);
                    aufspieler = checkPlayer;
                    send2All(new MessageAnnounceSpectator(spectator,aufspieler));
                    send2All(new MessageUpdateUserPanel(players.get(beginner).getName(),""));
                    send2All(new MessageUpdateUserPanel(players.get(aufspieler).getName(),""));
                    send2All(new MessageDisplayMessage(
                            Strings.getString(
                            Strings.SOLO, players.get(aufspieler).getName(),
                            Strings.getGameName(selectedGame))));
                    players.get(aufspieler).setRe(true, "spielt Solo");
                    runGame(aufspieler);
                    return;
                }
                else{
                    checkPlayer++;
                    if (checkPlayer>players.size()){
                        checkPlayer=0;
                    }
                }
            }
        }
        else if (selection.containsValue(ARMUT)){
            int index =-1;
            for (int i: selection.keySet()){
                if(selection.get(i).equals(ARMUT)){
                    index= i;
                    selectedGame = ARMUT;
                }
            }
            int finalIndex = index;
            armutplayer = finalIndex;
            createListOfPotentialPartners();
            send2All(new MessageDisplayMessage(
                    Strings.getString(
                            Strings.ARMUT_GIVE,
                            players.get(armutplayer).getName(),
                            players.get(armutplayer).getHand().stream().filter(card->card.trump).count())));
            queueOut(players.stream().filter(p -> p.getNumber()==finalIndex).findAny().get(),
                    new MessageSelectCards4Armut());
        }
        else if(selection.containsValue(HOCHZEIT)){
            selectedGame = NORMAL;
            aufspieler=-1;
            for(Integer i :selection.keySet()) {
                if(selection.get(i).equals(HOCHZEIT)){
                    players.get(i).setRe(true, "moechte heiraten");
                    send2All(new MessageDisplayMessage(Strings.getString(Strings.HOCHZEIT,players.get(i).getName())));
                    hochzeitSpieler = i;
                    break;
                }
            }
            wait4Partner = true;
            runGame(beginner);
        }
    }

    @Override
    protected void shuffleCards() {
        for (Player player1 : players) {
            send2All(new MessageUpdateUserPanel(player1.getName(), ""));
        }
        stichList = new ArrayList<>();
        random = new Random(System.currentTimeMillis());
        boolean ok = shuffle(new ArrayList<>(DokoCards.ALL_CARDS));
        while (!ok) {
            ok = shuffle(new ArrayList<>(DokoCards.ALL_CARDS));
        }
        for (Player player : players) {
            if (!player.isSpectator()) {
                MessageCards cards = new MessageCards(player.getHand());
                queueOut(player, cards);
            }
        }
        wait4Gesund = true;
        armutplayer = -1;
        schwein = false;
        stich = null;
        gameSelection = new HashMap<>();
        send2All(new MessageSelectGame());
    }

    private boolean shuffle(List<Card> cardList) {
        players.forEach(player -> {
            player.setHand(new ArrayList<>());
            if(!player.isSpectator()) {
                for (int i = 0; i < 10; i++) {
                    Card card = cardList.get(random.nextInt(cardList.size()));
                    player.getHand().add(card);
                    cardList.remove(card);
                }
            }
        });
        return true;
    }


    @Override
    public void startGame() {
        super.startGame();
        if(players.size()>4) {
            if (beginner == 0) {
                spectator = 4;
            } else {
                spectator = beginner - 1;
            }
            players.get(spectator).setSpectator(true);
        }
        send2All(new MessageAnnounceSpectator(spectator,beginner));
        shuffleCards();
    }

    @Override
    public String toString() {
        return "<html>" + selectedGame + "<br>" +
                spectator + "<br>" +
                currentPlayer + "<br>";
    }

    @Override
    protected void updateReconnectedPlayer(Player player) {
        player.getOutMessages().addFirst(new MessageAnnounceSpectator(spectator,aufspieler));
        player.getOutMessages().addFirst(new MessageCards(player.getHand()));
        player.getOutMessages().addFirst(new MessageStartGame(gameType.name()));
        player.queue(new MessageGameSelected(player.getNumber(),selectedGame));
        List<String> playerList = new ArrayList<>();
        players.forEach(p->playerList.add(p.getName()));
        player.queue(MessagePlayerList.playersInLobby(playerList));
        if(stich!=null) {
            stich.getCardMap().keySet().forEach(baseCard -> {
                player.queue(new MessagePutCard(stich.getCardMap().get(baseCard), baseCard));
            });
        }
        if(wait4Gesund){
            player.queue(new MessageSelectGame());
        }
        else if(wait4NextRound){
            readyMap.put(player.getNumber(),true);
            players.stream().filter(Player::isAdmin).forEach(p->
                    p.queue(new MessageDisplayMessage(player.getName()+ " ist bereit")));
        }
        else {
            player.queue(new MessageWait4Player(players.get(currentPlayer).getName()));
        }
        super.updateReconnectedPlayer(player);
    }

}
