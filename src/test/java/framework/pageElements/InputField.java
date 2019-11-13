package framework.pageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import framework.utils.Waiters;

public class InputField extends BaseElement {

    public InputField(By locator, String elementName) {
        super(locator, elementName);
    }

    public void waitAndSendInput(String keys) {
        WebElement inputField;

        inputField = Waiters.clickableWaiter(locator);
        inputField.sendKeys(keys);
    }

    public void sendInput(String keys) {
        WebElement inputField;

        inputField = Waiters.clickableWaiter(locator);
        inputField.sendKeys(keys);
    }
}
