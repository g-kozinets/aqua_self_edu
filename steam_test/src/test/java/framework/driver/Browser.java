package framework.driver;

import framework.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.HashMap;

import static java.util.concurrent.TimeUnit.SECONDS;
import static framework.logger.MyLogger.logger;

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

    public static void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void goToUrl(String url) {
        logger.debug("Loading url: " + url);
        driver.get(url);
    }

    public static void closeBrowser() {
        logger.debug("Closing browser");
        driver.quit();
    }

    public static void refreshPage() {
        logger.debug("Refreshing page");
        driver.navigate().refresh();
    }

    public static void selectTab(int tabNumber) {
        logger.debug("Switching to tab №: " + tabNumber);
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(tabNumber));
    }

    public static void saveTabName() {
        logger.debug("Saving current tab name");
        previousTab = driver.getWindowHandle();
    }

    public static void goToPreviousTab() {
        logger.debug("Going to previous tab");
        driver.switchTo().window(previousTab);
    }
}
