package sinks;

import log.LogMessage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.GZIPOutputStream;

public class TextFileLogSink implements LogSink {

    //Implement File Sink along with log-rotation enabled

    private String logFilePath;
    private long maxSize;
    private int fileCount = 0;

    public TextFileLogSink(String logFilePath, long maxSize) {
        this.logFilePath = logFilePath;
        this.maxSize = maxSize;
    }

    @Override
    public void log(LogMessage message) {
        try {
            File logFile = new File(logFilePath);
            if (logFile.length() >= maxSize) {
                rotateAndCompress();
            }
            try (FileWriter writer = new FileWriter(logFile, true)) {
                writer.write(message.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rotateAndCompress() throws IOException {
        File logFile = new File(logFilePath);
        String compressedFileName = logFilePath + "." + (fileCount++) + ".gz";
        try (GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(compressedFileName));
             FileInputStream in = new FileInputStream(logFile)) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        }

        // Clear the current log file
        Files.delete(Paths.get(logFilePath));
        logFile.createNewFile();
    }
}


