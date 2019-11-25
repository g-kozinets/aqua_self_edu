package project.forms.menus;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SideMenu extends Form {
    private IButton menuBtn;

    public SideMenu() {
        super(By.id("side_bar_inner"), "Side menu");
    }

    public void goTo(String id) {
        menuBtn = getElementFactory().getButton(By.id(id), "Menu button");
        menuBtn.click();
    }

}
