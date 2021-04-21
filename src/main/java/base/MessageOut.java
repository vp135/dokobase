package base;

import base.messages.RequestObject;

import java.net.Socket;

public class MessageOut {

    private final Socket socket;
    private final RequestObject output;

    public MessageOut(java.net.Socket socket, RequestObject output) {
        this.socket = socket;
        this.output = output;
    }

    public Socket getSocket() {
        return socket;
    }

    public RequestObject getOutput() {
        return output;
    }
}
