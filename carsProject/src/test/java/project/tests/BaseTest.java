package project.tests;

import framework.driver.Browser;
import framework.logger.MyLogger;
import framework.utils.PropertyReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {

    @BeforeTest
    public void setupTest() throws Exception {
        MyLogger.setupLogger();
        Browser.setupBrowser(PropertyReader.getProp("Browser"));
        Browser.goToUrl(PropertyReader.getProp("URL"));
    }

    @AfterTest
    public void closeBrowser() {
        Browser.closeBrowser();
    }
}
