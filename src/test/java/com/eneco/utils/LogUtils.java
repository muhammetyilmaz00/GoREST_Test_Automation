package com.eneco.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {

    /**
     * initializes a logger instance for the "LogUtils" class
     */
    private static final Logger logger = LogManager.getLogger(LogUtils.class);

    /**
     * Logs an informational message using the logger instance
     *
     * @param text The message to be logged.
     */
    public static void info(String text) {
        logger.info(text);
    }

    public static void debug(String text) {
        logger.debug(text);
    }
}
