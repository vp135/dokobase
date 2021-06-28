package base;

import base.messages.*;
import base.messages.admin.AbortGame;
import base.messages.admin.SetAdmin;

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
    protected int gamesPlayed = 0 ;
    protected String adminName;
    protected boolean gameRunning = false;

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
        send2All(new StartGame(gameType.name()));
        players.stream().filter(player -> player.getName().equals(adminName))
                .collect(Collectors.toList()).forEach(player -> queueOut(player,new SetAdmin(true)));
        DisplayMessageQueues();
    }

    protected void endIt(){

    }

    protected void shuffleCards(){

    }

    protected void queueOut(Player player, RequestObject message) {
        //comServer.queueOut(player.getSocket(),message,true);
        player.queue(message);
    }

    protected void send2All(RequestObject message){
        players.forEach(player -> player.queue(message));
        //List<Socket> socketList = new ArrayList<>();
        //players.forEach(player -> socketList.add(player.getSocket()));
        //comServer.send2All(socketList, message);
    }

    @Override
    public void handleInput(MessageIn message) {
        RequestObject requestObject = RequestObject.fromString(message.getInput());
        Socket socket = message.getSocket();
        switch (requestObject.getCommand()) {
            case AddPlayer.COMMAND:
                String name = requestObject.getParams().get("player").getAsString();
                if(players.stream().noneMatch(player -> player.getName().equals(name))) {
                    players.add(new Player(requestObject.getParams().get("player").getAsString(),
                            players.size(), socket, false, comServer));

                    List<String> list = new ArrayList<>();
                    players.forEach(p -> list.add(p.getName()));
                    send2All(new PlayersInLobby(list));
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
                            comServer.queueOut(player.getSocket(), new PlayersInLobby(list), true);
                        }

                    });
                }
                break;
            case AbortGame.COMMAND:{
                endIt();
                break;
            }
            case GetVersion.COMMAND:
                comServer.queueOut(socket,
                        new GetVersion("Server", Statics.VERSION),true);
                break;
            case AdminRequest.COMMAND:
                handleAdminRequest(requestObject);
                break;
        }
    }

    protected void updateReconnectedPlayer(Player player) {

    }

    private void handleAdminRequest(RequestObject requestObject) {
        switch (requestObject.getParams().get("request").getAsString()){
            case "shuffle":
                shuffleCards();
                break;
        }
    }

    public void setAdmin(String name) {
        this.adminName = name;
    }
}
