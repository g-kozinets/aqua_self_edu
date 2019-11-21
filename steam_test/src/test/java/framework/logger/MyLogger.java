package framework.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
import java.io.IOException;

public class MyLogger {

    public static Logger logger = Logger.getLogger(MyLogger.class.getName());

    public static void setupLogger() throws IOException {
        //BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(org.apache.log4j.Level.INFO);
        String filePath = "./Output/steamTest_logs.txt";
        PatternLayout layout = new PatternLayout("%-5p %d %m%n");
        RollingFileAppender appender = new RollingFileAppender(layout, filePath);
        appender.setName("TestLogs");
        appender.setMaxFileSize("1MB");
        appender.activateOptions();
        Logger.getRootLogger().addAppender(appender);
    }
}
