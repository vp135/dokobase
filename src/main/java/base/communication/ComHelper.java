package base.communication;

import base.Constants;
import base.messages.Message;
import org.slf4j.Logger;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ComHelper {

    public static void readBufferedReaderAndCheck(BufferedReader br, StringBuilder builder) throws IOException {
        String line = br.readLine();
        if (line == null) {
            throw new EOFException("Connection closed");
        }
        builder.append(line);
    }


    public static boolean sendByTCP(Socket socket, Message message, Logger log) {
        if (socket != null && socket.isConnected() && !socket.isClosed() && !socket.isOutputShutdown()) {
            try {
                byte[] payload = (message.toJson() + Constants.NEWLINE).getBytes(StandardCharsets.UTF_8);
                OutputStream out = socket.getOutputStream();
                out.write(payload);
                out.flush();
                log.info("Sent {}", message.getCommand());
                return true;
            } catch (IOException ex) {
                log.error("Failed to send message", ex);
                try {
                    socket.close();
                } catch (IOException closeException) {
                    log.warn("Failed to close socket after send failure", closeException);
                }
                return false;
            }
        } else {
            log.warn("Socket is unavailable");
            return false;
        }
    }
}
