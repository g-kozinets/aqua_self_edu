package project.forms;

import org.openqa.selenium.By;

public class MainFeedForm extends BaseForm {
    public MainFeedForm() {
        super(By.id("ui_rmenu_news"), "News form");
    }
}
