package project.pages;

import framework.pageElements.Banner;
import framework.pageElements.Button;
import org.openqa.selenium.By;
import static framework.logger.MyLogger.logger;

public class MainForm extends BaseForm {
    private Button mainPageBtn = new Button(By.id("logo-holder"), "Main page button");
    private Button installSteamBtn = new Button(By.xpath("//div[contains(@class, 'header_installsteam_btn')] "), "Install button");
    private Banner AdBanner = new Banner(By.xpath("//div[@class='fullscreen-bg'] "), "Advert banner");

    public MainForm() {
        uniqueElement = AdBanner ;
    }

    public void goToMainPage() {
        logger.debug("Going to main page");
        mainPageBtn.click();
    }



    public void goToInstall() {
        logger.debug("Going to install Steam");
        installSteamBtn.waitAndClick();
    }
}
