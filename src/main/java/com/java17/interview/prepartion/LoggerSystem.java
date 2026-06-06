package com.java17.interview.prepartion;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

public class LoggerSystem {

    public static void main(String[] args) {
        Logger logger = Logger.getInstance();

        logger.log(LogLevel.INFO, "Application started");
        logger.log(LogLevel.DEBUG, "Debugging mode");
        logger.log(LogLevel.ERROR, "Something went wrong");
    }
}

enum LogLevel{
    INFO,
    DEBUG,
    ERROR
}
final class Logger implements Cloneable, Serializable {

    private static volatile Logger logger = null;
    private Logger(){
        if(logger != null){
            throw new IllegalStateException("No");
        }
    }

    public static Logger getInstance(){
        if(logger == null){
            synchronized (Logger.class){
                if(logger == null){
                    logger = new Logger();
                }
            }
        }
        return logger;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("NO");
    }

    @Serial
    protected Object readResolve(){
        return getInstance();
    }

    public static void log(LogLevel logLevel, String message){

        String logMsg = LocalDateTime.now() + " [" + logLevel + "] " + message;
        writeToConsole(logMsg);
    }

    private static void writeToConsole(String message) {
        System.out.println(message);
    }

}