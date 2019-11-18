package project.tests;

import framework.driver.Browser;
import framework.utils.PropertyReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {

    @BeforeTest
    public void setupTest() throws Exception {
        Browser.setupBrowser(PropertyReader.getProp("Browser"));
        Browser.goToUrl(PropertyReader.getProp("URL"));
    }

    @AfterTest
    public void closeBrowser() {
        Browser.closeBrowser();
    }

}
