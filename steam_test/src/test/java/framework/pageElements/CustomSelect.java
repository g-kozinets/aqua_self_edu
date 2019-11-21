package framework.pageElements;

import framework.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CustomSelect extends BaseElement{
    private By optionTag;

    public CustomSelect(By option, String elementName) {
        super(option, elementName);
        optionTag = option;
    }

    public void selectByText(String text) {
        WebElement options = Waiters.clickableWaiter(locator);
        List<WebElement> optionsList = options.findElements(locator);

        for (WebElement option : optionsList) {
            if (option.getText().contains(text)) {
                option.click();
                break;
            }
        }
    }
}
