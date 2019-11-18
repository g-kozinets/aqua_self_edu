package framework.pageElements;

import carsProject.pages.BaseForm;
import framework.driver.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import framework.utils.Waiters;

public abstract class BaseElement {

    private WebElement webElement;
    protected By locator;
    private String elementName;

    public BaseElement() {
    }

    public BaseElement(By locator, String elementName) {
        this.locator = locator;
        this.elementName = elementName;
    }

    public void waitAndClick() {
        webElement = Waiters.clickableWaiter(locator);
        webElement.click();
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


}

