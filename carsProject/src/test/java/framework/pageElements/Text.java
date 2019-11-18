package framework.pageElements;

import framework.driver.Browser;
import org.openqa.selenium.By;

public class Text extends BaseElement {
    public Text(By locator, String elementName) {
        super(locator, elementName);
    }

    public String getText(){
        return Browser.getDriver().findElement(locator).getText();
    }
}
