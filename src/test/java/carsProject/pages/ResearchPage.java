package carsProject.pages;

import framework.pageElements.Button;
import carsProject.customElements.CarChooser;
import carsProject.models.CarSpecs;
import org.openqa.selenium.By;
import static framework.logger.MyLogger.log;

public class ResearchPage extends MainForm{

    private Button mainPageBtn = new Button(By.className("global-nav__logo"), "Main page button");
    private Button compareBtn = new Button(By.xpath("//a[@data-linkname='compare-cars']"), "Compare cars button");
    private CarChooser carChooser = new CarChooser();

    public ResearchPage() {
        uniqueElement = compareBtn;
    }

    @Override
    public void goToMainPage() {
        log.info("Going to main page");
        mainPageBtn.click();
    }

    public CarSpecs getRandomCar(){
        return carChooser.getRandomCar();
    }

    public void doSearch(){
        carChooser.doSearch();
    }

    public void goToCompare() {
        compareBtn.waitAndClick();
    }

    public String getChosenParam() {
        return carChooser.getChosenParam();
    }

}
