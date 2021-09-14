package base.helper;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Logger {

    private final String filename;
    private final int logLevel;
    private final int logType;

    public Logger(String name, int logLevel) {
        this(name, logLevel, false, 0);
    }

    public Logger(String name, int logLevel, boolean append,int logType) {
        this.filename = name + ".log";
        this.logType = logType;
        if (!append) {
            deleteLogFile(filename);
        }
        this.logLevel = logLevel;
    }

    private void writeLog(String logLevel, String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("DE"));
        calendar.setTimeInMillis(System.currentTimeMillis());
        String timestamp = sdf.format(calendar.getTime());
        if (logType == 0) {
            writeToFile(timestamp, logLevel, msg, sdf, calendar);
        } else {
            System.out.println(timestamp + " - " + logLevel
                    + " - " + Thread.currentThread().getName()
                    + "(" + Thread.currentThread().getId() + ") - "
                    + msg);
        }
    }

    private void writeToFile(String timestamp, String logLevel, String msg, SimpleDateFormat sdf, GregorianCalendar calendar) {
        try (FileWriter fw = new FileWriter(filename, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(
                    timestamp + " - " + logLevel + " - "
                            + Thread.currentThread().getName()
                            + "(" + Thread.currentThread().getId() + ") - "
                            + msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void error(String msg) {
        if (logLevel > 0) {
            writeLog("error", msg);
        }
    }

    public void warn(String msg) {
        if (logLevel > 1) {
            writeLog("warn", msg);
        }
    }

    public void info(String msg) {
        if (logLevel > 2) {
            writeLog("info", msg);
        }
    }

    private void deleteLogFile(String filename) {
        try {
            File f = new File(filename);
            if (f.exists()) {
                if (!f.delete()) {
                    error("logfile not removed");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
