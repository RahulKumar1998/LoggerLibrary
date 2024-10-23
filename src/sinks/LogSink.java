package sinks;

import log.LogMessage;

public interface LogSink {
    public void log(LogMessage message);
}
