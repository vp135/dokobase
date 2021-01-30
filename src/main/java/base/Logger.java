package base;

import java.io.*;
import java.time.LocalDateTime;

public class Logger {

    private final String filename;
    private final String name;

    public Logger(String name) {
        this.name = name;
        this.filename = name + ".log";
    }

    private void writeLog(String logLevel, String msg){
        try(FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            String timestamp = LocalDateTime.now().toString().
                    replace("T"," ").split("\\.")[0];
            out.println(timestamp+" - " +logLevel+" - "+ name +" - "+ msg);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public void error(String msg){
        writeLog("error",msg);
    }

    public void warn(String msg){
        writeLog("warn",msg);
    }

    public void info(String msg){
        writeLog("info",msg);
    }


}
