package base;

import base.messages.Message;
import base.messages.MessageAddPlayer;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class ComClient {

    private static final long TIMEOUT = 5000;
    private final AutoResetEvent evOut = new AutoResetEvent(true);
    private final AutoResetEvent evIn = new AutoResetEvent(true);
    private final ConcurrentLinkedDeque<Message> outMessages = new ConcurrentLinkedDeque<>();
    private final ConcurrentLinkedDeque<Message> inMessages = new ConcurrentLinkedDeque<>();

    private Socket socket;
    private final String hostname;
    private final int port;
    private String name;
    private IInputputHandler client;

    private Thread outThread;
    private Thread inThread;

    public final AtomicBoolean wait = new AtomicBoolean(false);

    private final Logger log ;

    public ComClient(String hostname, int port, IInputputHandler client, String name, int logLevel){
        log = new Logger("Client",logLevel,true);
        this.hostname = hostname;
        this.port = port;
        this.client = client;
        this.name = name;
    }


    public void outMessageHandling(){
        outThread = new Thread(() -> {
            while (true){
                try {
                    evOut.waitOne(TIMEOUT);
                    if((socket==null || socket.isClosed())&&!wait.get()){
                        openTCPConnection();
                    }
                    if(socket!=null && socket.isConnected()) {
                        while (outMessages.peek() != null) {
                            if (SendByTCP(outMessages.peek())) {
                                outMessages.poll();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error(e.toString());
                }
            }
        });
        outThread.setName("Client2Server("+this.name+")");
        outThread.start();
    }

    public void queueOutMessage(Message message) {
        message.sender = this.name;
        if (message.getCommand().equals(MessageAddPlayer.COMMAND)) {
            outMessages.forEach(requestObject -> {
                if (requestObject.getCommand().equals(MessageAddPlayer.COMMAND)) {
                    outMessages.remove(requestObject);
                }
            });
            outMessages.addFirst(message);
        } else {
            outMessages.offer(message);
        }
        evOut.set();
    }

    private boolean SendByTCP(Message message) {
        boolean sent = false;
        if (socket!=null && !socket.isClosed()) {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);
                String s = message.toJson();
                log.info("Sending to server: " + message.getCommand());
                out.println(s);
                sent = true;
            } catch (IOException ex) {
                log.error(ex.toString());
            }
        }
        else{
            log.warn("socket was unexpectedly closed - Trying to reopen connection");
            socket = null;
        }
        return sent;
    }


    private void inMessagehandling(){
        inThread = new Thread(() -> {
            while (true){
                try {
                    evIn.waitOne(TIMEOUT);
                    while (inMessages.peek() != null) {
                        log.info("received " + inMessages.peek().getCommand()+ " from Server");
                        client.handleInput(inMessages.poll());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error(e.toString());
                }
            }
        });
        inThread.setName("Server2Client");
        inThread.start();
    }

    private void Listen() {
        inThread = new Thread(() -> {
            if (socket != null) {
                String ServerReply;
                BufferedReader in = null;
                try {
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream(),
                            StandardCharsets.UTF_8));
                } catch (Exception ex) {
                    log.error(ex.toString());
                }
                if (in != null) {
                    while (socket != null) {
                        try {
                            if ((ServerReply = in.readLine()) != null) {
                                if (ServerReply.length() > 0) {
                                    inMessages.offer(Message.fromString(ServerReply));
                                    evIn.set();
                                }
                            }
                        } catch (Exception ex) {
                            log.error(ex.toString());
                            socket = null;
                        }
                    }
                }
            }
        });
        inThread.setName("Server2Client");
        inThread.start();
    }

    public void openTCPConnection() {
        wait.set(true);
        new Thread(() -> {
            while (socket == null) {
                try {
                    socket = new Socket(hostname, port);
                    Listen();
                    log.info("Connected to Server");
                } catch (IOException e) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    log.warn("Could not connect to Server: " + e);
                }
            }
            wait.set(false);
            queueOutMessage(new MessageAddPlayer(name));
            evOut.set();
        },"openConnection").start();

    }


    public void setClient(IInputputHandler handler) {
        this.client = handler;
    }

    public void clearQueue() {
        outMessages.clear();
    }

    public boolean socketNotNull() {
        return socket!=null;
    }

    public void start() {
        inMessagehandling();
        openTCPConnection();
        outMessageHandling();
    }
}
