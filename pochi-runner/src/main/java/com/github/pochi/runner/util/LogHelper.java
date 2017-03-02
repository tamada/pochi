package com.github.pochi.runner.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogHelper {
    private LogHelper(){
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
