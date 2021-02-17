package base;

import java.net.Socket;

public class MessageIn {
    private Socket socket;
    private String input;

    public MessageIn(Socket socket, String input) {
        this.socket = socket;
        this.input = input;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getInput() {
        return input;
    }
}
