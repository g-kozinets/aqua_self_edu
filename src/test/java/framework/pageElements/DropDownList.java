package framework.pageElements;

import framework.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DropDownList extends BaseElement{
    public DropDownList(By locator, String elementName) {
        super(locator, elementName);
    }

    private Select select = new Select(Waiters.clickableWaiter(locator));

    public List<WebElement> getOptions() {
        return select.getOptions();
    }

    public void selectItem(String itemName) {
        select.selectByVisibleText(itemName);
    }
}
