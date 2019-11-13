package marketTest.carsPages;

import framework.pageElements.Button;
import org.openqa.selenium.By;

public class MainPage extends BaseForm {
    private Button mainPageBtn = new Button(By.id("navLogoLink"), "Main page button");
    private Button researchBtn = new Button(By.xpath("//nav/ul/li/a[@data-linkname='header-research']"), "Research button");

    public void goToMainPage() {
        mainPageBtn.click();
    }

    public void goToResearch() {
        researchBtn.waitAndClick();
    }
}
