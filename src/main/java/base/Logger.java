package base;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Logger {

    private final String filename;
    private final String name;
    private int loglevel;
    private long lastlogged;

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
        this.lastlogged = System.currentTimeMillis();
    }

    private void writeLog(String logLevel, String msg){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("DE"));
        calendar.setTimeInMillis(System.currentTimeMillis());
        try(FileWriter fw = new FileWriter(filename, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            String timestamp = sdf.format(calendar.getTime());
            out.println(timestamp + " - " +logLevel
                    +" - "+ Thread.currentThread().getName()+ "("+Thread.currentThread().getId()+") - " + msg);
            lastlogged = System.currentTimeMillis();
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
