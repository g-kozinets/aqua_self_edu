package carsProject.pages;

import framework.driver.Browser;
import framework.pageElements.BaseElement;
import framework.pageElements.Button;
import org.openqa.selenium.By;

public class MainForm extends BaseForm {
    private By MAIN_LOGO_LOCATOR = By.id("//*[@id='cars-com-logo']");
    private By MOBILE_APP_LOCATOR = By.xpath("//a[@data-linkname='mobile-app']");
    private Button researchBtn = new Button(By.xpath("//nav/ul/li/a[@data-linkname='header-research']"), "Research button");

    public MainForm() {
        uniqueElement =  new Button(MOBILE_APP_LOCATOR, "");
    }

    public void goToMainPage() {
        new Button(MAIN_LOGO_LOCATOR, "Button for main page").click();
    }

    public void goToResearch() {
        researchBtn.waitAndClick();
    }
}
