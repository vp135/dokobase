package base.communication;

import base.Constants;
import base.config.Configuration;
import base.helper.AutoResetEvent;
import base.helper.Loggers;
import base.interfaces.IClientMessageHandler;
import base.messages.Message;
import base.messages.MessageAddPlayer;
import org.slf4j.Logger;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public class ComClient {

    private final Logger log;

    private static final long TIMEOUT = 1000;
    private static final long INITIAL_RETRY_DELAY = 500;
    private static final long MAX_RETRY_DELAY = 10000;
    private final AutoResetEvent evOut = new AutoResetEvent(true);
    private final AutoResetEvent evIn = new AutoResetEvent(true);
    private final ConcurrentLinkedDeque<Message> outMessages = new ConcurrentLinkedDeque<>();
    private final ConcurrentLinkedDeque<Message> inMessages = new ConcurrentLinkedDeque<>();
    private final AtomicBoolean connecting = new AtomicBoolean(false);

    private IClientMessageHandler client;

    private volatile Socket socket;
    private final String hostname;
    private final int port;
    private final String name;
    public final AtomicBoolean wait = new AtomicBoolean(false);



    public ComClient(Configuration c, IClientMessageHandler client) {
        log = Loggers.client(c.connection.name);
        this.hostname = c.connection.server;
        this.port = c.connection.port;
        this.client = client;
        this.name = c.connection.name;
        inMessagehandling();
        openTCPConnection();
        outMessageHandling();
    }

    public ComClient(Configuration c, IClientMessageHandler client, String name) {
        log = Loggers.client(name);
        this.hostname = c.connection.server;
        this.port = c.connection.port;
        this.client = client;
        this.name = name;
        inMessagehandling();
        openTCPConnection();
        outMessageHandling();
    }


    private void listen(Socket connection) {
        new Thread(() -> {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder builder = new StringBuilder();
                while (true) {
                    builder.setLength(0);
                    ComHelper.readBufferedReaderAndCheck(in, builder);
                    inMessages.offer(Message.fromString(builder.toString()));
                    evIn.set();
                }
            } catch (IOException ex) {
                if (!connection.isClosed()) {
                    log.info("Connection to Server closed", ex);
                }
            } finally {
                closeConnection(connection);
            }
        }, "Listen").start();
    }



    private void inMessagehandling() {
        Thread inThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    evIn.waitOne(TIMEOUT);
                    while (inMessages.peek() != null) {
                        log.info("received {} from Server", inMessages.peek().getCommand());
                        client.handleInput(inMessages.poll());
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
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
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    evOut.waitOne(TIMEOUT);
                    if (!isSocketConnected() && !wait.get()) {
                        openTCPConnection();
                    }
                    Socket connection = socket;
                    if (isConnected(connection)) {
                        while (outMessages.peek() != null && isConnected(connection)) {
                            if (ComHelper.sendByTCP(connection, outMessages.peek(), log)) {
                                outMessages.poll();
                            }
                            else {
                                closeConnection(connection);
                                break;
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        outThread.setName("Client2Server("+name+Constants.CLOSE_PARENTHESIS);
        outThread.start();
    }



    public void openTCPConnection() {
        if (isSocketConnected() || !connecting.compareAndSet(false, true)) {
            return;
        }
        wait.set(true);
        new Thread(() -> {
            long retryDelay = INITIAL_RETRY_DELAY;
            while (!Thread.currentThread().isInterrupted() && !isSocketConnected()) {
                Socket connection = new Socket();
                try {
                    connection.connect(new InetSocketAddress(hostname, port), (int) (TIMEOUT * 5));
                    connection.setKeepAlive(true);
                    connection.setTcpNoDelay(true);
                    synchronized (this) {
                        socket = connection;
                    }
                    listen(connection);
                    log.info("Connected to Server");
                    retryDelay = INITIAL_RETRY_DELAY;
                    queueOut(new MessageAddPlayer(name));
                    break;
                } catch (IOException e) {
                    try {
                        connection.close();
                    } catch (IOException closeException) {
                        log.warn("Failed to close connection", closeException);
                    }
                    log.warn("Could not connect to Server", e);
                    try {
                        Thread.sleep(retryDelay);
                    } catch (InterruptedException interruptedException) {
                        Thread.currentThread().interrupt();
                    }
                    retryDelay = Math.min(retryDelay * 2, MAX_RETRY_DELAY);
                }
            }
            wait.set(false);
            connecting.set(false);
            evOut.set();
        }, "openConnection(" + name + Constants.CLOSE_PARENTHESIS).start();

    }

    public void setClient(IClientMessageHandler handler) {
        this.client = handler;
    }

    public void clearQueue() {
        outMessages.clear();
    }

    public boolean socketNotNull() {
        return isSocketConnected();
    }

    private boolean isSocketConnected() {
        return isConnected(socket);
    }

    private boolean isConnected(Socket connection) {
        return connection != null && connection.isConnected()
                && !connection.isClosed() && !connection.isInputShutdown()
                && !connection.isOutputShutdown();
    }

    private synchronized void closeConnection(Socket connection) {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (IOException ex) {
            log.warn("Connection attempt failed", ex);
        } finally {
            if (socket == connection) {
                socket = null;
                evOut.set();
            }
        }
    }


}
