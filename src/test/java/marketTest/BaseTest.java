package marketTest;

import framework.driver.Browser;
import framework.utils.PropertyReader;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static framework.logger.MyLogger.log;

public abstract class BaseTest {

    @BeforeTest
    public void setupTest() throws Exception {
        Browser.setupBrowser(PropertyReader.getProp("Browser"));
    }

    @AfterTest
    public void closeBrowser() {
        //Browser.closeBrowser();
    }

}
