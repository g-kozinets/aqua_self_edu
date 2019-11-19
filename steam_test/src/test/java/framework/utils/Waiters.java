package framework.utils;

import framework.driver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.function.Function;

public class Waiters {

    protected static int timeOut = Integer.parseInt(PropertyReader.getProp("WaitTime"));
    private static WebDriver driver = Browser.getDriver();

    public static WebElement fluentWaiter(By locator) {

        Wait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(timeOut))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);

        WebElement webElement = (WebElement) wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        return webElement;
    }

    public static WebElement visibilityWaiter(By locator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement clickableWaiter(By locator) {
        Wait<WebDriver> wait = new WebDriverWait(driver, timeOut);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void invisibilityWaiter(By by) {
        Wait<WebDriver> wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
