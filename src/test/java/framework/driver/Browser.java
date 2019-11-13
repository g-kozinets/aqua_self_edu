package framework.driver;

import framework.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import static java.util.concurrent.TimeUnit.SECONDS;
import static framework.logger.MyLogger.log;

public class Browser {
    private static WebDriver driver;
    private static String previousTab;

    private static WebDriver getInstance(String browserName) throws Exception {
        if (driver == null) {
            driver = BrowserFactory.setBrowser(browserName);
        }
        driver.manage().window().maximize();
        return driver;
    }

    public static void setupBrowser(String browserName) throws Exception {
        driver = getInstance(browserName);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(PropertyReader.getProp("WaitTime")), SECONDS);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void goToUrl(String url) {
        log.info("Loading url: " + url);
        driver.get(url);
    }

    public static void closeBrowser() {
        log.info("Closing browser");
        driver.quit();
    }

    public static void refreshPage() {
        log.info("Refreshing page");
        driver.navigate().refresh();
    }

    public static void selectTab(int tabNumber) {
        log.info("Switching to tab №: " + tabNumber);
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(tabNumber));
    }

    public static void saveTabName() {
        log.info("Saving current tab name");
        previousTab = driver.getWindowHandle();
    }

    public static void goToPreviousTab() {
        log.info("Going to previous tab");
        driver.switchTo().window(previousTab);
    }
}
