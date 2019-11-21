package framework.pageElements;

import framework.utils.TestUtils;
import framework.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static framework.logger.MyLogger.logger;

public class SelectElement extends BaseElement{
    public SelectElement(By locator, String elementName) {
        super(locator, elementName);
    }

    private Select select = new Select(Waiters.clickableWaiter(locator));

    public List<WebElement> getOptions() {
        return select.getOptions();
    }

    public void selectItem(String itemName) {
        select.selectByVisibleText(itemName);
    }

    public void selectRandomItem() {
        selectItem((String) TestUtils.getRandomElement(getOptionsInString()));
    }

    public String getSelectedOption() {
        return select.getFirstSelectedOption().getText();
    }

    public ArrayList getOptionsInString() {
        logger.debug("Getting list of options from dropdown");
        ArrayList<String> listString = new ArrayList<>();

        for (WebElement item : select.getOptions()) {
            listString.add(item.getText());
        }
        listString.remove(0);
        return listString;
    }
}
