package project.SteamElements;

import framework.driver.Browser;
import framework.pageElements.BaseElement;
import framework.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GamesListItem extends BaseElement {
    WebElement game = Waiters.clickableWaiter(locator);

    public GamesListItem(By locator, String elementName) {
        super(locator, elementName);
    }

}
