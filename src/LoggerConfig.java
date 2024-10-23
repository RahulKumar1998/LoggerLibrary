public class LoggerConfig {

    //get TimeFormat from client

    private String timeFormat;
    private LogProcessor logProcessor;

    public LoggerConfig(String timeFormat, LogProcessor logProcessor) {
        this.timeFormat = timeFormat;
        this.logProcessor = logProcessor;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public LogProcessor getLogProcessor() {
        return logProcessor;
    }
}
