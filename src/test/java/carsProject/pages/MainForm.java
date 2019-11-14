package carsProject.pages;

import framework.pageElements.Button;
import org.openqa.selenium.By;

public class MainForm extends BaseForm {
    private By MAIN_LOGO_LOCATOR = By.id("//*[@id='cars-com-logo']");
    private Button researchBtn = new Button(By.xpath("//nav/ul/li/a[@data-linkname='header-research']"), "Research button");

    public void goToMainPage() {
        new Button(MAIN_LOGO_LOCATOR, "Button for main page").click();
    }

    public void goToResearch() {
        researchBtn.waitAndClick();
    }
}
