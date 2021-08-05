package base;

import base.messages.*;
import base.messages.admin.AbortGame;
import base.messages.admin.SetAdmin;
import com.google.gson.JsonArray;

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
        send2All(new StartGame(gameType.name()));
        players.stream().filter(player -> player.getName().equals(adminName))
                .collect(Collectors.toList()).forEach(player ->{
            queueOut(player,new SetAdmin(true));
            player.setAdmin(true);
        });
    }

    protected void endIt(){

    }

    protected void shuffleCards(){

    }

    protected void queueOut(Player player, RequestObject message) {
        player.queue(message);
    }

    protected void send2All(RequestObject message){
        players.forEach(player -> player.queue(message));
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
            case ChangePlayerOrder.COMMAND:
                List<Player> pList = new ArrayList<>();
                List<String> sList = new ArrayList<>();
                JsonArray pArray = requestObject.getParams().get("players").getAsJsonArray();
                pArray.forEach(j-> {
                    pList.add(players.stream().filter(p->p.getName().equals(j.getAsString())).findFirst().get());
                    sList.add(j.getAsString());
                });
                players.clear();
                players.addAll(pList);
                send2All(new PlayersInLobby(sList));
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
        player.queue(new SetAdmin(player.isAdmin()));
    }

    private void handleAdminRequest(RequestObject requestObject) {
        switch (Statics.ADMINREQUESTS.getName(requestObject.getParams().get("request").getAsInt())){
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
        send2All(new EndClients());
    }

    private void ackowledge() {
        send2All(new Acknowledge());
    }

    public void setAdmin(String name) {
        this.adminName = name;
    }

    public void setStartPlayer(int selectedIndex) {
        beginner = selectedIndex;
    }


}
