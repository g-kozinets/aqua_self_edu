package framework.logger;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class MyLogger {

    public static Logger logger = Logger.getLogger(MyLogger.class.getName());

    public static void setupLogger() {
        BasicConfigurator.configure();
        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.INFO);
    }
}
