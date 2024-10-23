package log;

public class LogMessage {
    private String content;
    private LogLevel level;
    private String namespace;
    private String timestamp;

    public LogMessage(String content, LogLevel level, String namespace, String timestamp) {
        this.content = content;
        this.level = level;
        this.namespace = namespace;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + level + " (" + namespace + "): " + content;
    }

    public LogLevel getLevel() {
        return level;
    }
}
