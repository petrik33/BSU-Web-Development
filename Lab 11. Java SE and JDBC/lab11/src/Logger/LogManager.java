package Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogManager {
    private static final String ERROR_LOG_FILE = "error_log.txt";

    public static void logException(Exception exception) {
        logExceptionToFile(exception);
    }

    private static void logExceptionToFile(Exception exception) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (PrintWriter writer = new PrintWriter(new FileWriter(ERROR_LOG_FILE, true))) {
            writer.println("Exception occurred at " + currentTime.format(formatter) + ":");
            exception.printStackTrace(writer);
        } catch (IOException ignored) {
        }
    }
}
