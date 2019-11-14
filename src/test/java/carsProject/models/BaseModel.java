package carsProject.models;

import framework.driver.Browser;
import org.openqa.selenium.WebDriver;

public abstract class BaseModel {
    WebDriver driver;

    public BaseModel() {
        this.driver = Browser.getDriver();
    }
}
