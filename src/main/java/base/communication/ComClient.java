package base.communication;

import base.config.Configuration;
import base.helper.AutoResetEvent;
import base.helper.Logger;
import base.interfaces.IClientMessageHandler;
import base.messages.Message;
import base.messages.MessageAddPlayer;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class ComClient {

    private final Logger log;

    private static final long TIMEOUT = 5000;
    private final AutoResetEvent evOut = new AutoResetEvent(true);
    private final AutoResetEvent evIn = new AutoResetEvent(true);
    private final ConcurrentLinkedDeque<Message> outMessages = new ConcurrentLinkedDeque<>();
    private final ConcurrentLinkedDeque<Message> inMessages = new ConcurrentLinkedDeque<>();

    private IClientMessageHandler client;

    private Socket socket;
    private final String hostname;
    private final int port;
    private final String name;
    public final AtomicBoolean wait = new AtomicBoolean(false);



    public ComClient(Configuration c, IClientMessageHandler client) {
        log = new Logger("Client", c.logLevel, true, c.logType);
        this.hostname = c.connection.server;
        this.port = c.connection.port;
        this.client = client;
        this.name = c.connection.name;
        inMessagehandling();
        openTCPConnection();
        outMessageHandling();
    }

    public ComClient(Configuration c, IClientMessageHandler client, String name) {
        log = new Logger("name", c.logLevel, true, c.logType);
        this.hostname = c.connection.server;
        this.port = c.connection.port;
        this.client = client;
        this.name = name;
        inMessagehandling();
        openTCPConnection();
        outMessageHandling();
    }


    private void Listen() {
         new Thread(() -> {
            if (socket != null) {
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
                            StringBuilder builder = new StringBuilder();
                            ComHelper.readBufferedReaderAndCheck(in,builder);
                                if (builder.length()>0) {
                                    inMessages.offer(Message.fromString(builder.toString()));
                                    evIn.set();
                                }
                        } catch (Exception ex) {
                            log.error(ex.toString());
                            socket = null;
                        }
                        System.out.println("waiting");
                    }
                }
            }
        },"Listen").start();
    }



    private void inMessagehandling() {
        Thread inThread = new Thread(() -> {
            while (true) {
                try {
                    evIn.waitOne(TIMEOUT);
                    while (inMessages.peek() != null) {
                        log.info("received " + inMessages.peek().getCommand() + " from Server");
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

    public void queueOut(Message message) {
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

    public void outMessageHandling() {
        Thread outThread = new Thread(() -> {
            while (true) {
                try {
                    evOut.waitOne(TIMEOUT);
                    if ((socket == null || socket.isClosed()) && !wait.get()) {
                        openTCPConnection();
                    }
                    if (socket != null && socket.isConnected()) {
                        while (outMessages.peek() != null) {
                            if (ComHelper.sendByTCP(socket, outMessages.peek(),log)) {
                                outMessages.poll();
                            }
                            else{
                                socket = null;
                                break;
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    log.error(e.toString());
                }
            }
        });
        outThread.setName("Client2Server("+name+")");
        outThread.start();
    }



    public void openTCPConnection() {
        wait.set(true);
        new Thread(() -> {
            while (socket == null) {
                try {
                    socket = new Socket(hostname, port);
                    socket.setTcpNoDelay(false);
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
            queueOut(new MessageAddPlayer(name));
            evOut.set();
        }, "openConnection").start();

    }

    public void setClient(IClientMessageHandler handler) {
        this.client = handler;
    }

    public void clearQueue() {
        outMessages.clear();
    }

    public boolean socketNotNull() {
        return socket != null;
    }


}
