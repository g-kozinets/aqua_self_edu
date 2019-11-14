package carsProject.pages;

import framework.driver.Browser;
import framework.pageElements.BaseElement;
import framework.pageElements.Button;
import framework.pageElements.Spinner;
import org.openqa.selenium.By;
import static framework.logger.MyLogger.log;

public class MainForm extends BaseForm {
    private By MAIN_LOGO_LOCATOR = By.id("//*[@id='cars-com-logo']");
    private By MOBILE_APP_LOCATOR = By.xpath("//a[@data-linkname='mobile-app']");
    private Button researchBtn = new Button(By.xpath("//nav/ul/li/a[@data-linkname='header-research']"), "Research button");
    protected By spinner = By.id("spinner");

    public MainForm() {
        uniqueElement =  new Button(MOBILE_APP_LOCATOR, "");
    }

    public void goToMainPage() {
        log.info("Going to main page");
        new Button(MAIN_LOGO_LOCATOR, "Button for main page").click();
    }

    public void goToResearch() {
        log.info("Going to research");
        researchBtn.waitAndClick();
    }
}
