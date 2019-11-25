package project.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class BaseAppForm extends Form {
    private final IButton btnAcceptCookie = getElementFactory().getButton(By.id("cookie_action_close_header"), "Accept cookies");

    protected BaseAppForm(By locator, String name) {
        super(locator, name);
    }

    public void clickAcceptCookie() {
        btnAcceptCookie.click();
    }
}
