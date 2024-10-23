import log.LogLevel;
import log.LogMessage;
import sinks.LogSink;

import java.util.List;
import java.util.Map;

public class LogProcessor {

    private Map<LogLevel, List<LogSink>> levelSinkMap;

    public LogProcessor(Map<LogLevel, List<LogSink>> levelSinkMap) {
        this.levelSinkMap = levelSinkMap;
    }

    //process the message to be sent to all sinks associated with a log level
    public void process(LogMessage message) {
        List<LogSink> sinks = levelSinkMap.get(message.getLevel());
        if (sinks != null) {
            for (LogSink sink : sinks) {
                sink.log(message);
            }
        }
    }
}
