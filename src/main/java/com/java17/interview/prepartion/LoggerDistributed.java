package com.java17.interview.prepartion;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LoggerDistributed {
    /**
     * Logger (File Logging + Async Queue + Rotation)
     * 🔹 What’s Added
     * Async logging using BlockingQueue
     * Background worker thread
     * File writing
     * Log rotation when file size exceeds limit
     */
    /**
     *
     * Add Total Number of Vehicles (Capacity Tracking)
     *
     * Right now, your system only checks availability, but doesn’t track capacity like 499 cars / 2000 bikes.
     *
     * 🔧 Improvement:
     * Add capacity per vehicle type
     * Track current count
     * Reject parking if limit reached
     */

    public static void main(String[] args) {
        AsyncLogger logger = AsyncLogger.getInstance();

        for (int i = 0; i < 100; i++) {
            logger.log(LoggerLevel.INFO, "Log message " + i);
        }
    }
}

// Log Levels
enum LoggerLevel { INFO, DEBUG, ERROR }

// Logger
class AsyncLogger {
    private static AsyncLogger instance;
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private final String fileName = "app.log";
    private final long MAX_SIZE = 1024 * 5; // 5 KB for demo

    private AsyncLogger() {
        startWorker();
    }

    public static AsyncLogger getInstance() {
        if (instance == null) {
            synchronized (AsyncLogger.class) {
                if (instance == null) {
                    instance = new AsyncLogger();
                }
            }
        }
        return instance;
    }

    public void log(LoggerLevel loggerLevel, String message) {
        String logMsg = LocalDateTime.now() + " [" + loggerLevel + "] " + message;
        queue.offer(logMsg);
    }

    private void startWorker() {
        new Thread(() -> {
            while (true) {
                try {
                    String msg = queue.take();
                    writeToFile(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private synchronized void writeToFile(String message) throws IOException {
        File file = new File(fileName);

        // Rotate if file too big
        if (file.exists() && file.length() > MAX_SIZE) {
            File newFile = new File("app_" + System.currentTimeMillis() + ".log");
            file.renameTo(newFile);
        }

        FileWriter fw = new FileWriter(fileName, true);
        fw.write(message + "\n");
        fw.close();
    }
}