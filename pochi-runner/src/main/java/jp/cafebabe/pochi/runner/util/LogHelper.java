package jp.cafebabe.pochi.runner.util;

import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogHelper {
    private LogHelper(){
    }

    public static void info(Object target, Supplier<String> message){
        Logger logger = logger(target.getClass());
        logger.log(Level.INFO, message.get());
    }

    public static void info(Object target, String message){
        Logger logger = logger(target.getClass());
        logger.log(Level.INFO, message);
    }

    public static void warn(Object target, Throwable throwable){
        warn(target, throwable.getMessage(), throwable);
    }
    public static void warn(Object target, String message, Throwable throwable){
        Logger logger = logger(target.getClass());
        logger.log(Level.WARNING, message, throwable);
    }

    private static Logger logger(Class<?> clazz){
        String className = clazz.getName();
        return Logger.getLogger(className);
    }
}
