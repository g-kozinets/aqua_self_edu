package carsProject.pages;

import framework.pageElements.Button;
import framework.pageElements.Text;
import org.openqa.selenium.By;

import static framework.logger.MyLogger.log;

public class CarPage extends MainForm{

    private Button trimsBtn = new Button(By.xpath("//a[@data-linkname='trim-compare']"), "Trims button");
    private Text price = new Text(By.className("header-info__row-price"), "Car price");

    public CarPage() {
        uniqueElement = price;
    }

    public Boolean checkTrims() {
        return trimsBtn.isDisplayed();
    }

    public void goToTrims() {
        log.info("Going to trims");
        trimsBtn.waitAndClick();
    }
}
