package project.forms;

import org.openqa.selenium.By;
import project.forms.menus.SideMenu;
import project.tests.BaseTest;

public class MainFeedForm extends BaseForm {
    public MainFeedForm() {
        super(By.id("ui_rmenu_news"), "News form");
    }
}
