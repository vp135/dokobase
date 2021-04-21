package base;

import java.net.Socket;

public class MessageIn {
    private final Socket socket;
    private final String input;

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
