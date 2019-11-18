package carsProject.pages;

import framework.driver.Browser;
import framework.pageElements.Button;
import framework.pageElements.DropDownList;
import carsProject.models.Car;
import framework.utils.Waiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import static framework.logger.MyLogger.log;

public class ComparePage extends MainForm{
    private DropDownList makersList = new DropDownList(By.id("make-dropdown"), "List of makers");
    private DropDownList modelsList = new DropDownList(By.id("model-dropdown"), "List of models");
    private DropDownList yearsList = new DropDownList(By.id("year-dropdown"), "List of years");
    private Button startComparingBtn = new Button(By.xpath("//button[@class='done-button']"), "Start Comparing");
    private static By ADD_CAR_LOCATOR = By.className("add-car-icon");
    private static By ADD_ANOTHER_LOCATOR = By.className("modal-button");
    private static By CAR_NAME_LOCATOR = By.className("listing-name");

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
        new DropDownList(By.id("make-dropdown"), "List of makers").selectItem(car.getMaker());
        new DropDownList(By.id("model-dropdown"), "List of models").selectItem(car.getModel());
        new DropDownList(By.id("year-dropdown"), "List of years").selectItem(Integer.toString(car.getYear()));

        new Button(ADD_ANOTHER_LOCATOR, "Add another car button").click();
        Waiters.invisibilityWaiter(spinner);
    }

    public ArrayList getCarNames() {
        log.info("Getting car names from comparison");
        ArrayList<WebElement> carNames = (ArrayList) Browser.getDriver().findElements(CAR_NAME_LOCATOR);
        ArrayList carNamesString = new ArrayList();

        for (WebElement name: carNames) {
            carNamesString.add(name.getText());
        }
        return carNamesString;
    }
}
