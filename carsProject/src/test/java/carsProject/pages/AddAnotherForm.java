package carsProject.pages;

import carsProject.models.Car;
import framework.pageElements.Button;
import framework.pageElements.DropDownList;
import framework.utils.Waiters;
import org.openqa.selenium.By;

import static framework.logger.MyLogger.log;

public class AddAnotherForm extends BaseForm{
    private static final By ADD_ANOTHER_LOCATOR = By.className("modal-button");
    private static final By SPINNER_LOCATOR = By.id("spinner");

   public void addCarToCompare(Car car) {
        log.info("Adding another car for comparison");

        new DropDownList(By.id("make-dropdown"), "List of makers").selectItem(car.getMaker());
        new DropDownList(By.id("model-dropdown"), "List of models").selectItem(car.getModel());
        new DropDownList(By.id("year-dropdown"), "List of years").selectItem(Integer.toString(car.getYear()));
        new Button(ADD_ANOTHER_LOCATOR, "Add another car button").click();
        Waiters.invisibilityWaiter(SPINNER_LOCATOR);
    }
}
