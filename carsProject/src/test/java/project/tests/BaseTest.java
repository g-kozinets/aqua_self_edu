package project.tests;

import framework.driver.Browser;
import framework.utils.PropertyReader;
import org.apache.log4j.BasicConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {

    @BeforeTest
    public void setupTest() throws Exception {
        BasicConfigurator.configure();
        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.INFO);
        Browser.setupBrowser(PropertyReader.getProp("Browser"));
        Browser.goToUrl(PropertyReader.getProp("URL"));
    }

    @AfterTest
    public void closeBrowser() {
        //Browser.closeBrowser();
    }
}
