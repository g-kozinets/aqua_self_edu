package project.forms;

import framework.pageElements.Banner;
import framework.pageElements.Button;
import framework.pageElements.CustomSelect;
import framework.pageElements.SelectElement;
import framework.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static framework.logger.MyLogger.logger;

public class MainForm extends BaseForm {
    private Button mainPageBtn = new Button(By.id("logo-holder"), "Main page button");
    private Button installSteamBtn = new Button(By.xpath("//div[contains(@class, 'header_installsteam_btn')] "), "Install button");
    private Banner AdBanner = new Banner(By.xpath("//div[@class='home_page_content']"), "Advert banner");
    private Button languageBtn = new Button(By.id("language_pulldown"), "Language pull down button");
    private By overlayLocator = By.className("newmodal_background");
    private By optionLocator = By.xpath("//div[@id='language_dropdown']/div/*[contains(@class, 'popup_menu_item')]");
    private CustomSelect languageSelect;
    public MenuForm menuForm = new MenuForm();

    public MainForm() {
        uniqueElement = AdBanner ;
    }

    public void goToMainPage() {
        logger.debug("Going to main page");
        mainPageBtn.click();
    }

    public void selectLanguage(String lang) {
        languageBtn.click();
        languageSelect = new CustomSelect(optionLocator, "Language dropdown");
        languageSelect.selectByText(lang);
        Waiters.invisibilityWaiter(overlayLocator);
    }



    public void goToInstall() {
        logger.debug("Going to install Steam");
        installSteamBtn.click();
    }
}
