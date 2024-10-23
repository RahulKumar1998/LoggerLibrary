import log.LogLevel;
import sinks.ConsoleLogSink;
import sinks.LogSink;
import sinks.TextFileLogSink;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<LogLevel, List<LogSink>> levelSinkMap = new HashMap<>();

        // Configuring console sink
        LogSink consoleLogSink = new ConsoleLogSink();
        levelSinkMap.computeIfAbsent(LogLevel.DEBUG, k -> new ArrayList<>()).add(consoleLogSink);
        levelSinkMap.computeIfAbsent(LogLevel.INFO, k -> new ArrayList<>()).add(consoleLogSink);

        // Configuring rotating file sink
        LogSink fileSink = new TextFileLogSink("application.log", 1024L);  // 1KB size limit for rotation
        levelSinkMap.computeIfAbsent(LogLevel.ERROR, k -> new ArrayList<>()).add(fileSink);
        levelSinkMap.computeIfAbsent(LogLevel.FATAL, k -> new ArrayList<>()).add(fileSink);
        levelSinkMap.computeIfAbsent(LogLevel.INFO, k -> new ArrayList<>()).add(fileSink);

        // Creating the LogProcessor with level-to-sinks mapping
        LogProcessor logProcessor = new LogProcessor(levelSinkMap);

        // creating logger configuration
        LoggerConfig config = new LoggerConfig("yyyy-MM-dd HH:mm:ss", logProcessor);

        // Getting the singleton instance of Logger
        Logger logger = Logger.getInstance(config);

        // Logging sample messages
        logger.log("This is a debug message", LogLevel.DEBUG, "FrontEndModule");
        logger.log("This is an info message", LogLevel.INFO, "ApplicationModule");
        logger.log("This is an error message", LogLevel.ERROR, "DatabaseModule");

        // Simulating multiple logs to trigger file rotation
        for (int i = 0; i < 100; i++) {
            logger.log("Log message number " + i, LogLevel.ERROR, "BackendModule");
        }
    }
}