package framework.pageElements;

import framework.driver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import framework.utils.Waiters;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

public abstract class BaseElement {

    private WebElement webElement;
    protected By locator;
    private String elementName;

    public BaseElement(By locator, String elementName) {
        this.locator = locator;
        this.elementName = elementName;
    }

    public void waitAndClick() {
        webElement = Waiters.clickableWaiter(locator);
        webElement.click();
    }

    public void moveCursorHere() {
        webElement = Waiters.visibilityWaiter(locator);
        new Actions(Browser.getDriver()).moveToElement(webElement).build().perform();
    }

    public void actionClick() {
        webElement = Waiters.clickableWaiter(locator);
        new Actions(Browser.getDriver()).moveToElement(webElement).click().build().perform();
    }

    public String getAttribute(String attributeName) {
       return webElement.getAttribute(attributeName);
    }

    public void click() {
        Browser.getDriver().findElement(locator).click();
    }

    public String getElementName() {
        return elementName;
    }

    public boolean isDisplayed() {
        return Browser.getDriver().findElements(locator).size() > 0;
    }

    public ArrayList<WebElement> getAllElements() {
        return new ArrayList<>(Browser.getDriver().findElements(locator));
    }


}

