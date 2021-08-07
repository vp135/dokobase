package base;

import base.messages.*;
import base.messages.admin.MessageAbortGame;
import base.messages.admin.MessageSetAdmin;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BaseServer implements IServerMessageHandler{

    protected Logger log = new Logger("Server",4);
    protected Statics.game gameType;
    public ComServer comServer;
    protected final List<Player> players = new ArrayList<>();
    protected Configuration c;
    protected String adminName;
    protected boolean gameRunning = false;
    protected int beginner=-1;

    public BaseServer(Configuration c, ComServer comServer) {
        this.c = c;
        this.comServer = comServer;
        comServer.setServer(this);

    }

    private void DisplayMessageQueues() {
        new Thread(() -> {
            while(true){
                StringBuilder builder = new StringBuilder();
                players.forEach(player -> builder.append(player.getName()).append(":")
                        .append(player.getOutMessages().size()).append("|"));
                System.out.println(builder.toString());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public void startGame() {
        if(beginner == -1){
            if (players.size()>1){
                beginner = 1;
            }
            else{
                beginner = 0;
            }
        }
        send2All(new MessageStartGame(gameType.name()));
        players.stream().filter(player -> player.getName().equals(adminName))
                .collect(Collectors.toList()).forEach(player ->{
            queueOut(player,new MessageSetAdmin(true));
            player.setAdmin(true);
        });
    }

    protected void endIt(){

    }

    protected void shuffleCards(){

    }

    protected void queueOut(Player player, Message message) {
        player.queue(message);
    }

    protected void send2All(Message message){
        players.forEach(player -> player.queue(message));
    }

    @Override
    public void handleInput(MessageIn message) {
        Message requestObject = Message.fromString(message.getInput());
        Socket socket = message.getSocket();
        switch (requestObject.getCommand()) {
            case MessageAddPlayer.COMMAND:
                handleAddPlayer(requestObject, socket);
                break;
            case MessagePlayerList.CHANGE_ORDER:
                handleChangePlayerOrder(requestObject);
                break;
            case MessageAbortGame.COMMAND:{
                endIt();
                break;
            }
            case MessageGetVersion.COMMAND:
                comServer.queueOut(socket,
                        new MessageGetVersion("Server", Statics.VERSION),true);
                break;
            case MessageAdminRequest.COMMAND:
                handleAdminRequest(requestObject);
                break;
        }
    }

    private void handleChangePlayerOrder(Message message) {
        MessagePlayerList messagePlayerList = new MessagePlayerList(message);
        List<Player> pList = new ArrayList<>();
        List<String> sList = new ArrayList<>();
        messagePlayerList.getPlayerNamesList().forEach(s-> {
            pList.add(players.stream().filter(p->p.getName().equals(s)).findFirst().get());
            sList.add(s);
        });
        players.clear();
        players.addAll(pList);
        send2All(MessagePlayerList.playerOrderChanged(sList));
    }

    private void handleAddPlayer(Message message, Socket socket) {
        MessageAddPlayer messageAddPlayer = new MessageAddPlayer(message);
        String name = messageAddPlayer.getPlayerName();
        if(players.stream().noneMatch(player -> player.getName().equals(name))) {
            players.add(new Player(messageAddPlayer.getPlayerName(),
                    players.size(), socket, false, comServer));

            List<String> list = new ArrayList<>();
            players.forEach(p -> list.add(p.getName()));
            send2All(MessagePlayerList.playersInLobby(list));
        }
        else{
            players.stream().filter(player -> player.getName().equals(name)).findFirst().ifPresent(player -> {
                player.setSocket(socket);
                if(gameRunning) {
                    updateReconnectedPlayer(player);
                }
                else {
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
        switch (messageAdminRequest.getRequest()){
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
