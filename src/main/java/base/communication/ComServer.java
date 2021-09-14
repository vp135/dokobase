package base.communication;

import base.config.Configuration;
import base.helper.AutoResetEvent;
import base.helper.Logger;
import base.interfaces.IServerMessageHandler;
import base.messages.Message;
import base.messages.MessageIn;
import base.messages.MessageOut;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ComServer {

    private Logger log;

    public static final long TIMEOUT = 1000;
    private final AutoResetEvent evOut = new AutoResetEvent(true);
    private final AutoResetEvent evIn = new AutoResetEvent(true);
    private final ConcurrentLinkedDeque<MessageOut> outMessages = new ConcurrentLinkedDeque<>();
    private final ConcurrentLinkedDeque<MessageIn> inMessages = new ConcurrentLinkedDeque<>();

    private IServerMessageHandler server;

    public boolean listening = false;


    public ComServer(Configuration c) {
        log = new Logger("Server", c.logLevel, true, c.logType);
        inMessageHandling();
        outMessageHandling();
        startTCPServer(c.connection.port);
    }

    private void startTCPServer(int port) {
        Thread inThread = new Thread(() -> {
            ServerSocket socket = null;
            log.info("Creating ServerSocket...");
            try {
                socket = new ServerSocket(port);
                log.info("ServerSocket created: " + socket.getInetAddress() + ":" + socket.getLocalPort());
            } catch (IOException e) {
                log.info("Socket creation failed:\n" + e);
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
                                    br = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream(), StandardCharsets.UTF_8));
                                    StringBuilder builder = new StringBuilder();
                                    ComHelper.readBufferedReaderAndCheck(br, builder);
                                    inMessages.offer(new MessageIn(connectionSocket, builder.toString()));
                                    evIn.set();
                                } catch (Exception e) {
                                    if (e instanceof SocketException) {
                                        log.info(e.toString());
                                        try {
                                            connectionSocket.close();
                                        } catch (IOException ex) {
                                            log.error(ex.toString());
                                        }
                                    } else {
                                        log.error(Arrays.toString(e.getStackTrace()));
                                    }
                                }
                            }
                        }, "ComServer_queueMessages").start();
                    }
                } catch (IOException e) {
                    log.error(e.toString());
                }
            }
        });
        inThread.setName("ConnectionHandling");
        inThread.start();
    }

    private void inMessageHandling() {
        new Thread(() -> {
            while (true) {
                try {
                    evIn.waitOne(TIMEOUT);
                    while (inMessages.peek() != null) {
                        server.handleInput(Objects.requireNonNull(inMessages.peek()));
                        inMessages.poll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Client2Server").start();
    }

    public void queueOut(Socket socket, Message message, boolean resetEvent) {
        outMessages.offer(new MessageOut(socket, message));
        if (resetEvent) {
            evOut.set();
        }
    }

    public void outMessageHandling() {
        new Thread(() -> {
            while (true) {
                try {
                    evOut.waitOne(TIMEOUT);
                    while (outMessages.peek() != null) {
                        if (sendByTCP(outMessages.peek())) {
                            outMessages.poll();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public boolean sendByTCP(MessageOut message) {
        Socket socket = message.getSocket();
        Message requestObject = message.getOutput();
        return ComHelper.sendByTCP(socket, requestObject, log);
    }

    public boolean sendByTCP(Socket socket, Message message) {
        return ComHelper.sendByTCP(socket, message, log);
    }

    public void setServer(IServerMessageHandler server) {
        this.server = server;
    }


}
