package project.forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import project.enums.SideMenuId;
import project.forms.menus.SideMenu;

public class BaseForm extends Form {
    protected BaseForm(By locator, String name) {
        super(locator, name);
    }

    public void selectOnSideMenu(SideMenuId menu) {
        new SideMenu().goTo(menu.getId());
    }

//    public boolean isInvisible() {
//        waitForTrue((BooleanSupplier) ExpectedConditions.invisibilityOfElementLocated(locator), "");
//    }
}
