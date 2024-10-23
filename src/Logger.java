import log.LogLevel;
import log.LogMessage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    private LoggerConfig loggerConfig;
    private static Logger instance;
    private Logger(LoggerConfig loggerConfig) {
        this.loggerConfig = loggerConfig;
    }

    public static Logger getInstance(LoggerConfig loggerConfig) {
        if (instance == null) {
            instance = new Logger(loggerConfig);
        }
        return instance;
    }

    public void log(String content, LogLevel level, String namespace) {
        String timestamp = new SimpleDateFormat(loggerConfig.getTimeFormat()).format(new Date());
        LogMessage message = new LogMessage(content, level, namespace, timestamp);
        loggerConfig.getLogProcessor().process(message);
    }
}
