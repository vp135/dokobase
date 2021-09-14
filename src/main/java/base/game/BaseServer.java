package base.game;

import base.Statics;
import base.communication.ComServer;
import base.config.Configuration;
import base.helper.Logger;
import base.interfaces.IServerMessageHandler;
import base.messages.*;
import base.messages.admin.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BaseServer implements IServerMessageHandler {

    protected Logger log = new Logger("Server", 4, true);
    protected Statics.game gameType;
    public ComServer comServer;
    protected final List<Player> players = new ArrayList<>();
    public Configuration c;
    protected String adminName;
    protected boolean gameRunning = false;
    protected int beginner = -1;


    protected int currentStichNumber = 0;
    protected HashMap<Integer, Boolean> readyMap;
    protected int currentPlayer = 0;
    protected HashMap<Integer, Integer> points = new HashMap<>();
    protected boolean wait4NextRound = false;
    protected int spectator;

    public BaseServer(Configuration c, ComServer comServer) {
        this.c = c;
        this.comServer = comServer;
        comServer.setServer(this);
    }

    protected void prepareGameServer(BaseServer server) {
        this.beginner = server.beginner;
        server.comServer.setServer(this);
        this.players.addAll(server.players);
        c.other.lastGame = gameType.name();
        gameRunning = true;
    }


    public void startGame() {
        if (beginner == -1) {
            if (players.size() > 1) {
                beginner = 1;
            } else {
                beginner = 0;
            }
        }
        send2All(new MessageStartGame(gameType.name()));
        players.stream().filter(player -> player.getName().equals(adminName))
                .collect(Collectors.toList()).forEach(player -> {
                    queueOut(player, new MessageSetAdmin(true));
                    player.setAdmin(true);
                });
    }

    protected void endIt() {

    }

    protected void shuffleCards() {

    }

    protected void queueOut(Player player, Message message) {
        message.sender = player.getName();
        player.queue(message);
    }

    protected void send2All(Message message) {
        players.forEach(player -> player.queue(message));
    }

    @Override
    public void handleInput(MessageIn message) {
        Message requestObject = Message.fromString(message.getInput());
        log.info("received " + requestObject.getCommand() + " from " + requestObject.sender);
        Socket socket = message.getSocket();
        try {
            if (!comServer.messageMap.containsKey(requestObject.guid)) {
                switch (requestObject.getCommand()) {
                    case MessageConfiguration.COMMAND:
                        handleConfiguration(requestObject);
                        break;
                    case MessageAddPlayer.COMMAND:
                        handleAddPlayer(requestObject, socket);
                        break;
                    case MessagePlayerList.CHANGE_ORDER:
                        handleChangePlayerOrder(requestObject);
                        break;
                    case MessagePutCard.COMMAND:
                        handlePutCard(requestObject);
                        break;
                    case MessageAbortGame.COMMAND:
                        endIt();
                        break;
                    case MessageReadyForNextRound.COMMAND:
                        handleReadyForNextRound(requestObject);
                        break;
                    case MessageGetVersion.COMMAND:
                        queueOut(players.stream().filter(player -> player.getName().equals(requestObject.sender)).findFirst().get(),
                                new MessageGetVersion("Server", Statics.VERSION));
                        break;
                    case MessageAdminRequest.COMMAND:
                        handleAdminRequest(requestObject);
                        break;
                }
            } else {
                comServer.messageMap.remove(requestObject.guid);
                players.forEach(player -> player.messageMap.remove(requestObject.guid));
            }
        } catch (Exception ex) {
            log.error(ex.toString());
        }
    }

    private void handleConfiguration(Message message) {
        send2All(message);
    }


    protected void handlePutCard(Message message) {

    }

    protected void handleReadyForNextRound(Message message) {
        if (wait4NextRound) {
            MessageReadyForNextRound messageReadyForNextRound = new MessageReadyForNextRound(message);
            readyMap.put(messageReadyForNextRound.getPlayerNumber(), true);
            players.stream().filter(Player::isAdmin).forEach(p -> p.queue(new MessageDisplayMessage(
                    players.get(messageReadyForNextRound.getPlayerNumber()).getName() + " ist bereit")));
        }
        if (readyMap.values().stream().allMatch(p -> p)) {
            nextGame();
        }
    }

    protected void nextGame() {

    }

    protected void runGame(int player) {
        points = new HashMap<>();
        for (int i = 0; i < players.size(); i++) {
            points.put(i, 0);
        }
        currentPlayer = player;
        currentStichNumber = 0;
    }

    private void handleChangePlayerOrder(Message message) {
        MessagePlayerList messagePlayerList = new MessagePlayerList(message);
        List<Player> pList = new ArrayList<>();
        List<String> sList = new ArrayList<>();
        messagePlayerList.getPlayerNamesList().forEach(s -> {
            pList.add(players.stream().filter(p -> p.getName().equals(s)).findFirst().get());
            sList.add(s);
        });
        players.clear();
        players.addAll(pList);
        for (int i = 0; i < players.size(); i++) {
            players.get(i).setNumber(i);
        }
        send2All(MessagePlayerList.playerOrderChanged(sList));
    }

    private void handleAddPlayer(Message message, Socket socket) {
        MessageAddPlayer messageAddPlayer = new MessageAddPlayer(message);
        String name = messageAddPlayer.getPlayerName();
        if (players.stream().noneMatch(player -> player.getName().equals(name))) {
            players.add(new Player(messageAddPlayer.getPlayerName(),
                    players.size(), socket, false, comServer));

            List<String> list = new ArrayList<>();
            players.forEach(p -> list.add(p.getName()));
            send2All(MessagePlayerList.playersInLobby(list));
        } else {
            players.stream().filter(player -> player.getName().equals(name)).findFirst().ifPresent(player -> {
                player.setSocket(socket);
                if (gameRunning) {
                    updateReconnectedPlayer(player);
                } else {
                    List<String> list = new ArrayList<>();
                    players.forEach(p -> list.add(p.getName()));
                    comServer.queueOut(player.getSocket(), MessagePlayerList.playersInLobby(list), true);
                }
            });
        }
    }

    protected void updateReconnectedPlayer(Player player) {
        player.queue(new MessageSetAdmin(player.isAdmin()));
    }

    private void handleAdminRequest(Message message) {
        MessageAdminRequest messageAdminRequest = new MessageAdminRequest(message);
        switch (messageAdminRequest.getRequest()) {
            case NOTHING:
                break;
            case SHUFFLE:
                shuffleCards();
                break;
            case ACKNOWLEDGE:
                ackowledge();
                break;
            case END_CLIENTS:
                endClients();
                break;
        }
    }

    private void endClients() {
        send2All(new MessageEndClients());
    }

    private void ackowledge() {
        send2All(new MessageAcknowledge());
    }

    public void setAdmin(String name) {
        this.adminName = name;
    }

    public void setStartPlayer(int selectedIndex) {
        beginner = selectedIndex;
    }


}
