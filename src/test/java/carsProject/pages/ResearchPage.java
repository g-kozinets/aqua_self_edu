package carsProject.pages;

import framework.pageElements.Button;
import carsProject.models.Car;
import framework.pageElements.DropDownList;
import framework.utils.TestUtils;
import org.openqa.selenium.By;

import static framework.logger.MyLogger.log;

public class ResearchPage extends MainForm{

    private Button mainPageBtn = new Button(By.className("global-nav__logo"), "Main page button");
    private Button compareBtn = new Button(By.xpath("//a[@data-linkname='compare-cars']"), "Compare cars button");
    private static final By MAKERS_LIST_LOCATOR = By.xpath("//select[@name='makeId']");
    private static final By MODELS_LIST_LOCATOR = By.xpath("//select[@name='modelId']");
    private static final By YEARS_LIST_LOCATOR = By.xpath("//select[@name='year']");
    private DropDownList makersDrpDwnLst = new DropDownList(MAKERS_LIST_LOCATOR, "List of makers");
    private DropDownList modelsDrpDwnLst = new DropDownList(MODELS_LIST_LOCATOR, "List of models");
    private DropDownList yearsDrpDwnLst = new DropDownList(YEARS_LIST_LOCATOR, "List of years");
    private Button searchBtn = new Button(By.xpath("//input[@value='Search']"), "Search button");

    public ResearchPage() {
        uniqueElement = compareBtn;
    }

    @Override
    public void goToMainPage() {
        log.info("Going to main page");
        mainPageBtn.click();
    }

    public Car getRandomCar() {
        log.info("Getting random car");

        String makerName = (String) TestUtils.getRandomElement(makersDrpDwnLst.getOptionsInString());
        makersDrpDwnLst.selectItem(makerName);

        String modelName = (String) TestUtils.getRandomElement(modelsDrpDwnLst.getOptionsInString());
        modelsDrpDwnLst.selectItem(modelName);

        String year = (String) TestUtils.getRandomElement(yearsDrpDwnLst.getOptionsInString());
        yearsDrpDwnLst.selectItem(year);

        return new Car(makerName, modelName, Integer.parseInt(year));
    }

    public String getChosenParam() {
        log.info("Getting chosen parameters");
        return String.format("%s %s %s", yearsDrpDwnLst.getSelectedOption(), makersDrpDwnLst.getSelectedOption(), modelsDrpDwnLst.getSelectedOption());
    }

    public void doSearch() {
        log.info("Clicking search");
        searchBtn.click();
    }

    public void goToCompare() {
        compareBtn.waitAndClick();
    }
}
