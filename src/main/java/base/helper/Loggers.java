package base.helper;

import base.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Loggers {

    private Loggers() {
    }

    public static Logger client(String name) {
        return LoggerFactory.getLogger(Constants.CLIENT + (name == null ? Constants.EMPTY_STRING : name.trim()));
    }

    public static Logger server() {
        return LoggerFactory.getLogger(Constants.SERVER);
    }
}
