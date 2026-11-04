package base.communication;

import base.helper.Logger;
import base.messages.Message;

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
                byte[] payload = (message.toJson() + "\n").getBytes(StandardCharsets.UTF_8);
                OutputStream out = socket.getOutputStream();
                out.write(payload);
                out.flush();
                log.info("Sent " + message.getCommand());
                return true;
            } catch (IOException ex) {
                log.error(ex.toString());
                try {
                    socket.close();
                } catch (IOException closeException) {
                    log.warn(closeException.toString());
                }
                return false;
            }
        } else {
            log.warn("Socket is unavailable");
            return false;
        }
    }
}
