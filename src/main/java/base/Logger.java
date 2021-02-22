package base;

import java.io.*;
import java.time.LocalDateTime;

public class Logger {

    private final String filename;
    private final String name;
    private int loglevel;

    public Logger(String name, int loglevel){
        this(name, loglevel, false);
    }

    public Logger(String name, int loglevel, boolean append) {
        this.name = name;
        this.filename = name + ".log";
        if(!append) {
            deleteLogFile(filename);
        }
        this.loglevel = loglevel;
    }

    private void writeLog(String logLevel, String msg){
        try(FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            String timestamp = LocalDateTime.now().toString().
                    replace("T"," ").split("\\.")[0];
            out.println(timestamp+" - " +logLevel+" - "+ Thread.currentThread().getId()+" - " + name +" - "+ msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void error(String msg){
        if(loglevel>0) {
            writeLog("error", msg);
        }
    }

    public void warn(String msg){
        if(loglevel>1) {
            writeLog("warn", msg);
        }
    }

    public void info(String msg){
        if(loglevel>2) {
            writeLog("info", msg);
        }
    }

    private void deleteLogFile(String filename){
        try {
            File f = new File(filename);
            if(f.exists()) {
                f.delete();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void setLoglevel(int loglevel) {
        this.loglevel = loglevel;
    }
}
