package framework.utils;

import aquality.selenium.logger.Logger;

public class ExceptionHandler {
    public static void throwException(String message, Exception e) {
        Logger.getInstance().error(String.format("%s: %s", message, e.getMessage()));
        throw new RuntimeException();
    }
}
