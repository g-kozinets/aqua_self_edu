package project.pages;

import project.models.Car;
import framework.pageElements.Button;
import framework.pageElements.DropDownList;
import framework.utils.Waiters;
import org.openqa.selenium.By;

import static framework.logger.MyLogger.log;

public class AddAnotherForm extends BaseForm{
    private DropDownList makerDrpDwnLst = new DropDownList(By.id("make-dropdown"), "List of makers");
    private DropDownList modelDrpDwnLst = new DropDownList(By.id("model-dropdown"), "List of models");
    private DropDownList yearDrpDwnLst = new DropDownList(By.id("year-dropdown"), "List of years");
    private static final Button addAnotherBtn = new Button(By.className("modal-button"), "Add another car button");
    private static final By SPINNER_LOCATOR = By.id("spinner");

   public void addCarToCompare(Car car) {
        log.info("Adding another car for comparison");

        makerDrpDwnLst.selectItem(car.getMaker());
        modelDrpDwnLst.selectItem(car.getModel());
        yearDrpDwnLst.selectItem(Integer.toString(car.getYear()));
        addAnotherBtn.click();
        Waiters.invisibilityWaiter(SPINNER_LOCATOR);
    }
}
