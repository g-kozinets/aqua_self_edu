package framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.util.HashMap;

public class BrowserFactory {
    private static String downloadFilepath = System.getProperty("user.dir") + File.separator + "Output" + File.separator;

    static WebDriver setBrowser(String browserName) throws Exception {
        WebDriver webDriver;
        switch (browserName.toLowerCase()) {
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver(setFirefoxDownloadPath());
            }
            break;

            case "chrome": {
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(setChromeDownloadPath());
            }
            break;

            default: {
                throw new Exception(
                        "Wrong browser name, chose between chrome and firefox. You entered: " + browserName);
            }
        }
        return webDriver;
    }

    private static ChromeOptions setChromeDownloadPath() {
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilepath);
        chromePrefs.put("safebrowsing.enabled", "true");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        return options;
    }

    private static FirefoxOptions setFirefoxDownloadPath() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", downloadFilepath);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-debian-package");
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        return options;
    }
}
