package project.tests;

import framework.driver.Browser;
import framework.logger.MyLogger;
import framework.utils.PropertyReader;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import static framework.logger.MyLogger.logger;

public abstract class BaseTest {

    @BeforeTest
    public void setupBrowser() throws Exception {
        MyLogger.setupLogger();
        Browser.setupBrowser(PropertyReader.getProp("Browser"));
    }

    @AfterTest
    public void closeBrowser() {
        Browser.closeBrowser();
    }

    @BeforeMethod
    public void setupTest() {
        logger.info("Loading URL:" + PropertyReader.getProp("URL"));
        Browser.goToUrl(PropertyReader.getProp("URL"));
    }

    @AfterMethod
    public void clearBrowser() {
        Browser.clearCookies();
    }
}
