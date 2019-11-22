package project.tests;

import framework.driver.Browser;
import framework.logger.MyLogger;
import framework.utils.PropertyReader;
import org.testng.annotations.*;
import static framework.logger.MyLogger.logger;

public abstract class BaseTest {
    String language;
    String genreName;
    String tabName;


    @BeforeSuite
    public void setupBrowser() throws Exception {
        logger.info("Setting up logger and browser");
        MyLogger.setupLogger();
        Browser.setupBrowser(PropertyReader.getProp("Browser"));

    }

    @AfterSuite
    public void closeBrowser() {
        Browser.closeBrowser();
    }

    @BeforeMethod
    public void setupTest() {
        String lang = PropertyReader.getProp("Lang");

        if (lang.equals("rus")) {
            language = "Русский";
            genreName = "Инди";
            tabName = "Лидеры продаж";
        }
        if (lang.equals("eng")) {
            language = "English";
            genreName = "Indie";
            tabName = "Top Selling";
        }
        Browser.goToUrl(PropertyReader.getProp("URL"));
    }

    @AfterMethod
    public void clearBrowser() {
        logger.info("Clearing Browser cookies");
        Browser.clearCookies();
    }
}
