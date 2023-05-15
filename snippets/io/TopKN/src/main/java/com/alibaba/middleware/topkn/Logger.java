package com.alibaba.middleware.topkn;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple Logger for this project
 *
 * Created by yfu on 6/28/17.
 */
public class Logger {

    private final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private PrintStream out;

    private Logger(String path) {
        try {
            out = new PrintStream(new FileOutputStream(path, true));
        } catch (IOException ex) {
            out = System.out; // For local test
        }
    }

    public static final Logger MASTER_LOGGER = new Logger(Constants.MASTER_LOG_PATH);
    public static final Logger WORKER_LOGGER = new Logger(Constants.WORKER_LOG_PATH);

    public synchronized void info(String format, Object... args) {
        out.println("[" + timeFormat.format(new Date()) + "] INFO  " + String.format(format, args));
    }

    public synchronized void warn(String format, Object... args) {
        out.println("[" + timeFormat.format(new Date()) + "] WARN  " + String.format(format, args));
    }

    public synchronized void error(String message, Throwable ex) {
        out.println(message);
        ex.printStackTrace(out);
    }
}
