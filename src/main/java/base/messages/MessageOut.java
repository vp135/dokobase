package base.messages;

import java.net.Socket;

public class MessageOut {

    private final Socket socket;
    private final Message output;

    public MessageOut(java.net.Socket socket, Message output) {
        this.socket = socket;
        this.output = output;
    }

    public Socket getSocket() {
        return socket;
    }

    public Message getOutput() {
        return output;
    }
}
