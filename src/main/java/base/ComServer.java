package base;

import base.messages.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ComServer {

    protected Logger log;


    private final ConcurrentLinkedDeque<MessageIn> inMessages = new ConcurrentLinkedDeque<>();
    private final ConcurrentLinkedDeque<MessageOut> outMessages = new ConcurrentLinkedDeque<>();
    private final AutoResetEvent evOut = new AutoResetEvent(true);
    private final AutoResetEvent evIn = new AutoResetEvent(true);
    public final HashMap<UUID, Message> messageMap = new HashMap<>();
    public static final long TIMEOUT = 1000;

    private IServerMessageHandler server;

    private Thread outThread;
    private Thread inThread;

    public boolean listening = false;


    public ComServer(int port, int logLevel){
        log = new Logger("Server",logLevel,true);
        inMessageHandling();
        outMessageHandling();
        startTCPServer(port);
    }


    private void startTCPServer(int port) {
        inThread = new Thread(() -> {
            ServerSocket socket = null;
            log.info("Creating ServerSocket...");
            try {
                socket = new ServerSocket(port);
                log.info("ServerSocket created: " +socket.getInetAddress() +":"+socket.getLocalPort());
            } catch (IOException e) {
                log.info("Socket creation failed:\n"+ e);
            }
            while (true) {
                Socket connectionSocket;
                try {
                    assert socket != null;
                    listening = true;
                    connectionSocket = socket.accept();
                    log.info("Connection established");
                    if (connectionSocket != null) {
                        new Thread(() -> {
                            while (!connectionSocket.isClosed()) {
                                BufferedReader br;
                                try {
                                    br = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                                    String in = br.readLine();
                                    if (in != null) {
                                        inMessages.offer(new MessageIn(connectionSocket,in));
                                        evIn.set();
                                    } else {
                                        log.info("incoming data was null");
                                        //log.info("incoming data was null "+ players.stream().filter(player -> player.getSocket().equals(connectionSocket)).findFirst().get().getName());
                                        connectionSocket.close();
                                    }
                                } catch (IOException e) {
                                    if (e instanceof SocketException) {
                                        log.info(e.toString());
                                        e.printStackTrace();
                                        //log.info(e.toString()+ players.stream().filter(player -> player.getSocket().equals(connectionSocket)).findFirst().get().getName());
                                        try{
                                            connectionSocket.close();
                                        }catch (IOException ex){
                                            log.error(ex.toString());
                                        }
                                    }
                                }
                            }
                        },"ComServer_queueMessages").start();
                    }
                } catch (IOException e) {
                    log.error(e.toString());
                }
            }
        });
        inThread.setName("ConnectionHandling");
        inThread.start();
    }

    private void inMessageHandling(){
        new Thread(() -> {
            while(true){
                try {
                    evIn.waitOne(TIMEOUT);
                    while(inMessages.peek()!=null) {
                        server.handleInput(Objects.requireNonNull(inMessages.peek()));
                        inMessages.poll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"Client2Server").start();
    }

    public void outMessageHandling(){
        new Thread(() -> {
            while (true){
                try {
                    evOut.waitOne(TIMEOUT);
                    for (Message m : messageMap.values()) {

                    }
                    while(outMessages.peek()!=null){
                        if(sendReply(outMessages.peek())) {
                            outMessages.poll();
                        }
                    }
                    //System.out.println(messageMap.size());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public boolean sendReply(MessageOut message) {
        Socket socketConnection = message.getSocket();
        Message requestObject = message.getOutput();
        return sendReply(socketConnection, requestObject);
    }

    public boolean sendReply(Socket socketConnection, Message message) {
        boolean sent = false;
        if (!socketConnection.isClosed()) {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socketConnection.getOutputStream())), true);
                String s = message.toJson();
                out.println(s);
                log.info(s);
                sent = true;
            } catch (IOException ex) {
                log.error(ex.toString());
            }
        }
        return sent;
    }

    public void queueOut(Socket socket, Message message, boolean resetEvent){
        outMessages.offer(new MessageOut(socket,message));
        if(resetEvent) {
            evOut.set();
        }
    }

    public void setServer(IServerMessageHandler server) {
        this.server = server;
    }

    public void send2All(List<Socket> sockets, Message message) {
        sockets.forEach(socket ->{
            message.newGUID();
            queueOut(socket,message,false);
        });
        evOut.set();
    }
}
