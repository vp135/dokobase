package base;

import base.messages.RequestObject;

import java.net.Socket;

public class MessageOut {

    private Socket socket;
    private RequestObject output;

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
