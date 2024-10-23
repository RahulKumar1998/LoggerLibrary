package sinks;
import log.LogMessage;

public class ConsoleLogSink implements LogSink{
    @Override
    public void log(LogMessage message) {
        System.out.println(message.toString());
    }
}
