package project.pages;

import project.enums.CarParameter;
import framework.pageElements.Button;
import project.models.Car;
import framework.pageElements.DropDownList;
import org.openqa.selenium.By;

import static framework.logger.MyLogger.log;

public class ResearchPage extends MainForm {
    private Button mainPageBtn = new Button(By.className("global-nav__logo"), "Main page button");
    private Button compareBtn = new Button(By.xpath("//a[@data-linkname='compare-cars']"), "Compare cars button");
    private static final String UNIVERSAL_DROPDOWN_TAG = "//select[@name='%s']";
    private DropDownList makersDrpDwnLst = new DropDownList(By.xpath("//select[@name='makeId']"), "List of makers");
    private DropDownList modelsDrpDwnLst = new DropDownList(By.xpath("//select[@name='modelId']"), "List of models");
    private DropDownList yearsDrpDwnLst = new DropDownList(By.xpath("//select[@name='year']"), "List of years");
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

        CarParameter[] parameters = CarParameter.values();
        for (CarParameter param : parameters) {
            selectByParameter(param);
        }
        return new Car(
                makersDrpDwnLst.getSelectedOption(),
                modelsDrpDwnLst.getSelectedOption(),
                Integer.parseInt(yearsDrpDwnLst.getSelectedOption()));
    }

    public void selectByParameter(CarParameter parameter) {
        DropDownList dropDown = new DropDownList(By.xpath(String.format(UNIVERSAL_DROPDOWN_TAG, parameter.getId())), "Dropdown list");
        dropDown.selectRandomItem();
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
