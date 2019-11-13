package marketTest.carsPages;

import framework.driver.Browser;
import framework.pageElements.BaseElement;
import org.openqa.selenium.WebDriver;

public abstract class BaseForm {
    protected WebDriver driver = Browser.getDriver();
    protected BaseElement uniqueElement;

    public boolean isOnThePage() {
        return this.uniqueElement.isDisplayed();
    }

}
