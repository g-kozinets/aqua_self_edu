package framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

    static WebDriver setBrowser(String browserName) throws Exception {
        WebDriver webDriver;
        switch (browserName.toLowerCase()) {
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
            }
            break;

            case "chrome": {
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
            }
            break;

            default: {
                throw new Exception(
                        "Wrong browser name, chose between chrome and firefox. You entered: " + browserName);
            }
        }
        return webDriver;
    }
}
