package Logger;

import org.apache.logging.log4j.Logger;

public class JLogManager {
//    private static final String ERROR_LOG_FILE = "error_log.txt";
//
//    public static void logException(Exception exception) {
//        logExceptionToFile(exception);
//    }
//
//    private static void logExceptionToFile(Exception exception) {
//        LocalDateTime currentTime = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        try (PrintWriter writer = new PrintWriter(new FileWriter(ERROR_LOG_FILE, true))) {
//            writer.println("Exception occurred at " + currentTime.format(formatter) + ":");
//            exception.printStackTrace(writer);
//        } catch (IOException ignored) {
//        }
//    }
    private static final Logger logger = org.apache.logging.log4j.LogManager.getLogger(JLogManager.class);

    public static void logException(Exception exception) {
            logger.error("Exception occurred", exception);
    }
}
