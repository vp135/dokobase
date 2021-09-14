package base.communication;

import base.helper.Logger;
import base.messages.Message;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ComHelper {

    public static void readBufferedReaderAndCheck(BufferedReader br, StringBuilder builder) throws IOException {
        int c;
        int brakets=0;
        while((c= br.read())>-1&&c!=10&&c!=13){
            switch (c) {
                case 123:
                    brakets++;
                    break;
                case 125:
                    brakets--;
                    break;
            }
            builder.append((char)c);
            //System.out.println(builder);
            if (brakets==0){
                break;
            }
        }
    }


    public static boolean sendByTCP(Socket socket, Message message, Logger log) {
        if (socket!=null && !socket.isClosed()) {
            try {
                PrintWriter out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)), true);
                String s = message.toJson();
                log.error(s);
                out.println(s);
                out.flush();
                log.info(s);
                return true;
            } catch (IOException ex) {
                log.error(ex.toString());
                return false;
            }
        } else {
            log.warn("socket was unexpectedly closed - Trying to reopen connection");
            return false;
        }
    }
}
