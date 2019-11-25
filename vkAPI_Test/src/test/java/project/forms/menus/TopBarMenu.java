package project.forms.menus;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;


public class TopBarMenu extends Form {

    public TopBarMenu() {
        super(By.id("masthead"), "Header");
    }

    public void openHeaderMenu(Item topBarMenuItem) {
        getElementFactory().getButton(By.xpath(topBarMenuItem.getMenuItemXpath()), topBarMenuItem.toString()).clickAndWait();
    }

    public enum Item {
        CONTACT_US("//div[@id='primary-navigation']//li[contains(@class, 'ontact-us menu')]//a");

        private String menuItemLocator;

        Item(String menuItemLoc) {
            this.menuItemLocator = menuItemLoc;
        }

        public String getMenuItemXpath() {
            return menuItemLocator;
        }
    }
}
