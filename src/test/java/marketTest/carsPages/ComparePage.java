package marketTest.carsPages;

import framework.pageElements.Button;
import framework.pageElements.DropDownList;
import org.openqa.selenium.By;

public class ComparePage {

    private DropDownList makersList = new DropDownList(By.id("make-dropdown"), "List of makers");
    private DropDownList modelsList = new DropDownList(By.id("model-dropdown"), "List of models");
    private DropDownList yearsList = new DropDownList(By.id("year-dropdown"), "List of years");
    private Button startComparingBtn = new Button(By.xpath("//button[@class='done-button']"), "Start Comparing");
    private static By ADD_CAR_LOCATOR = By.className("add-car-icon");
    private static By ADD_ANOTHER_LOCATOR = By.className("modal-button");
    private static By TABLE_LOCATOR = By.className("compare-categories");

    public void defineCarToCompare(String maker, String model, int year) {
        makersList.selectItem(maker);
        modelsList.selectItem(model);
        yearsList.selectItem(Integer.toString(year));
    }

    public void startComparing() {
        startComparingBtn.click();
    }

    public void addCarToCompare(String maker, String model, int year) {
        new Button(ADD_CAR_LOCATOR, "Add another car").click();


        new DropDownList(By.id("make-dropdown"), "List of makers").selectItem(maker);
        new DropDownList(By.id("model-dropdown"), "List of models").selectItem(model);
        new DropDownList(By.id("year-dropdown"), "List of years").selectItem(Integer.toString(year));

        new Button(ADD_ANOTHER_LOCATOR, "Add another car button").click();
    }
}
