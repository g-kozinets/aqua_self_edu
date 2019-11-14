package carsProject.pages;

import framework.pageElements.Button;
import org.openqa.selenium.By;

public class CarPage {

    private Button trimsBtn = new Button(By.xpath("//a[@data-linkname='trim-compare']"), "Trims button");

    public Boolean checkTrims() {
        return trimsBtn.isDisplayed();
    }

    public void goToTrims() {
        trimsBtn.waitAndClick();
    }
}
