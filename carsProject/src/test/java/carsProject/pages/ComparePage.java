package carsProject.pages;

import framework.driver.Browser;
import framework.pageElements.Button;
import framework.pageElements.DropDownList;
import carsProject.models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import static framework.logger.MyLogger.log;

public class ComparePage extends MainForm{
    private final DropDownList makersList = new DropDownList(By.id("make-dropdown"), "List of makers");
    private final DropDownList modelsList = new DropDownList(By.id("model-dropdown"), "List of models");
    private final DropDownList yearsList = new DropDownList(By.id("year-dropdown"), "List of years");
    private final Button startComparingBtn = new Button(By.xpath("//button[@class='done-button']"), "Start Comparing");
    private static final By ADD_CAR_LOCATOR = By.className("add-car-icon");
    private static final By CAR_NAME_LOCATOR = By.className("listing-name");
    private final AddAnotherForm addAnotherForm = new AddAnotherForm();

    public ComparePage() {
        uniqueElement = startComparingBtn;
    }

    public void initiateCarComparison(Car car) {
        log.info("Adding first car to compare");
        makersList.selectItem(car.getMaker());
        modelsList.selectItem(car.getModel());
        yearsList.selectItem(Integer.toString(car.getYear()));
    }

    public void startComparing() {
        log.info("Starting to compare");
        startComparingBtn.click();
    }

    public void addCarToCompare(Car car) {
        log.info("Adding another car for comparison");

        new Button(ADD_CAR_LOCATOR, "Add another car").click();
        addAnotherForm.addCarToCompare(car);
    }

    public ArrayList<String> getCarNames() {
        log.info("Getting car names from comparison");
        ArrayList<WebElement> carNames = (ArrayList<WebElement>) Browser.getDriver().findElements(CAR_NAME_LOCATOR);
        ArrayList<String> carNamesString = new ArrayList<>();

        for (WebElement name: carNames) {
            carNamesString.add(name.getText());
        }
        return carNamesString;
    }
}
