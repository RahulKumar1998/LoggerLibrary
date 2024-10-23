package sinks;

import log.LogMessage;

public interface LogSink {
    //Design of this Logger library allows users to provide their own implementation of sink
    public void log(LogMessage message);
}
