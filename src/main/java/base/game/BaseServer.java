package base.game;

import base.Constants;
import base.Statics;
import base.communication.ComServer;
import base.config.Configuration;
import base.game.doko.DokoServer;
import base.game.skat.SkatServer;
import base.helper.Loggers;
import base.interfaces.IServerMessageHandler;
import base.messages.*;
import base.messages.admin.*;
import org.slf4j.Logger;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BaseServer implements IServerMessageHandler {

    protected final Logger log;
    protected Statics.game gameType;
    public final ComServer comServer;
    protected final List<Player> players = new ArrayList<>();
    public final Configuration c;
    protected String adminName;
    protected boolean gameRunning = false;
    protected int beginner = -1;

    protected int currentTrickNumber = 0;
    protected HashMap<Integer, Boolean> readyMap;
    protected int currentPlayer = 0;
    protected HashMap<Integer, Integer> points = new HashMap<>();
    protected boolean wait4NextRound = false;
    protected int spectator;

    public BaseServer(Configuration c, ComServer comServer) {
        this.c = c;
        this.log = Loggers.server();
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
        try {
            Message requestObject = Message.fromString(message.getInput());
            if (requestObject == null || requestObject.getCommand() == null) {
                log.warn("Ignoring malformed message");
                return;
            }
            log.info("received {} from {}", requestObject.getCommand(), requestObject.sender);
            Socket socket = message.getSocket();
            if (MessageAddPlayer.COMMAND.equals(requestObject.getCommand())) {
                MessageAddPlayer addPlayer = new MessageAddPlayer(requestObject);
                if (requestObject.sender == null || requestObject.sender.trim().isEmpty()
                        || !requestObject.sender.equals(addPlayer.getPlayerName())) {
                    log.warn("Rejected AddPlayer with mismatched sender");
                    return;
                }
                handleAddPlayer(requestObject, socket);
                return;
            }

            Player player = getAuthenticatedPlayer(message);
            if (player == null) {
                log.warn("Rejected message from an unauthenticated socket");
                return;
            }
            switch (requestObject.getCommand()) {
                case MessageStartGame.COMMAND:
                    if (isAdmin(player) && !gameRunning) {
                        handleStartGame(requestObject);
                    }
                    break;
                case MessageSetStartPlayer.COMMAND:
                    if (isAdmin(player)) {
                        handleSetStartPlayer(requestObject);
                    }
                    break;
                case MessageConfiguration.COMMAND:
                    if (isAdmin(player)) {
                        handleConfiguration(requestObject);
                    }
                    break;
                case MessagePlayerList.CHANGE_ORDER:
                    if (isAdmin(player) && !gameRunning) {
                        handleChangePlayerOrder(requestObject);
                    }
                    break;
                case MessagePutCard.COMMAND:
                    handlePutCard(requestObject);
                    break;
                case MessageAbortGame.COMMAND:
                    if (isAdmin(player)) {
                        endIt();
                    }
                    break;
                case MessageReadyForNextRound.COMMAND:
                    MessageReadyForNextRound ready = new MessageReadyForNextRound(requestObject);
                    if (ready.getPlayerNumber() == player.getNumber()) {
                        handleReadyForNextRound(requestObject);
                    }
                    break;
                case MessageAdminRequest.COMMAND:
                    if (isAdmin(player)) {
                        handleAdminRequest(requestObject);
                    }
                    break;
            }
        } catch (Exception ex) {
            log.warn("Rejected invalid message", ex);
        }
    }

    protected Player getAuthenticatedPlayer(MessageIn message) {
        if (message == null || message.getSocket() == null) {
            return null;
        }
        Message requestObject = Message.fromString(message.getInput());
        if (requestObject == null || requestObject.sender == null) {
            return null;
        }
        return players.stream()
                .filter(player -> player.getSocket() == message.getSocket()
                        && player.getName().equals(requestObject.sender))
                .findFirst()
                .orElse(null);
    }

    private boolean isAdmin(Player player) {
        String currentAdmin = adminName;
        if (currentAdmin == null && !players.isEmpty()) {
            currentAdmin = players.get(0).getName();
        }
        return player.getName().equals(currentAdmin);
    }

    private void handleSetStartPlayer(Message message) {
        MessageSetStartPlayer messageSetStartPlayer = new MessageSetStartPlayer(message);
        int playerNumber = messageSetStartPlayer.getPlayerNumber();
        if (playerNumber >= 0 && playerNumber < players.size()
                && players.get(playerNumber).isNotSpectator()) {
            beginner = playerNumber;
        }
    }

    private void EndServer() {
        gameRunning = false;
        new BaseServer(c, this.comServer);
    }

    private void handleStartGame(Message message) {
        MessageStartGame messageStartGame = new MessageStartGame(message);
        Statics.game requestedGame = messageStartGame.getGame();
        int requiredPlayers = requestedGame == Statics.game.DOKO ? 4 : 3;
        if (players.size() < requiredPlayers) {
            log.warn("Rejected start request: not enough players");
            return;
        }
        this.setAdmin(players.get(0).getName());
        this.gameType = requestedGame;
        switch (this.gameType) {
            case DOKO:
                new DokoServer(this).startGame();
                break;
            case SKAT:
                new SkatServer(this).startGame();
                break;
        }
    }

    private void handleConfiguration(Message message) {
        send2All(message);
    }


    protected void handlePutCard(Message message) {

    }

    protected void handleReadyForNextRound(Message message) {
        if (!wait4NextRound || readyMap == null) {
            return;
        }
        MessageReadyForNextRound readyMessage = new MessageReadyForNextRound(message);
        int playerNumber = readyMessage.getPlayerNumber();
        if (playerNumber < 0 || playerNumber >= players.size()
                || !readyMap.containsKey(playerNumber)
                || Boolean.TRUE.equals(readyMap.get(playerNumber))) {
            return;
        }
        readyMap.put(playerNumber, true);
        players.stream().filter(Player::isAdmin).forEach(p -> p.queue(new MessageDisplayMessage(
                players.get(playerNumber).getName() + Constants.IS_READY)));
        if (!readyMap.isEmpty() && readyMap.values().stream().allMatch(Boolean.TRUE::equals)) {
            wait4NextRound = false;
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
        currentTrickNumber = 0;
    }

    private void handleChangePlayerOrder(Message message) {
        MessagePlayerList messagePlayerList = new MessagePlayerList(message);
        List<String> requestedOrder = messagePlayerList.getPlayerNamesList();
        if (requestedOrder.size() != players.size()
                || requestedOrder.stream().distinct().count() != players.size()
                || !requestedOrder.containsAll(players.stream().map(Player::getName).collect(Collectors.toList()))) {
            log.warn("Rejected invalid player order");
            return;
        }
        List<Player> pList = new ArrayList<>();
        List<String> sList = new ArrayList<>();
        requestedOrder.forEach(s -> {
            players.stream().filter(p -> p.getName().equals(s)).findFirst().ifPresent(pList::add);
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
        if (socket == null) {
            return;
        }
        MessageAddPlayer messageAddPlayer = new MessageAddPlayer(message);
        String name = messageAddPlayer.getPlayerName();
        if (name == null || name.trim().isEmpty()) {
            return;
        }
        Player existingPlayer = players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElse(null);
        if (existingPlayer == null) {
            players.add(new Player(messageAddPlayer.getPlayerName(),
                    players.size(), socket, false, comServer));
            List<String> list = new ArrayList<>();
            players.forEach(p -> list.add(p.getName()));
            send2All(MessagePlayerList.playersInLobby(list));
        } else {
            Socket oldSocket = existingPlayer.getSocket();
            if (oldSocket != socket && oldSocket != null && !oldSocket.isClosed()) {
                log.warn("Rejected reconnect while previous socket is still active");
                return;
            }
            existingPlayer.setSocket(socket);
            if (gameRunning) {
                updateReconnectedPlayer(existingPlayer);
            } else {
                List<String> list = new ArrayList<>();
                players.forEach(p -> list.add(p.getName()));
                existingPlayer.queue(MessagePlayerList.playersInLobby(list));
            }
        }

        players.stream().filter(player -> player.getName().equals(name)).findFirst()
                .ifPresent(player -> queueOut(player, new MessageGetVersion("Server", Statics.VERSION)));
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
                acknowledge();
                break;
            case END_CLIENTS:
                endClients();
                break;
            case END_SERVER:
                EndServer();
                break;

        }
    }

    private void endClients() {
        send2All(new MessageEndClients());
    }

    private void acknowledge() {
        send2All(new MessageAcknowledge());
    }

    public void setAdmin(String name) {
        this.adminName = name;
    }

    public String getAdminName() {
        return adminName;
    }

}
