package carsProject.pages;

import framework.pageElements.Button;
import carsProject.customElements.CarChooser;
import carsProject.models.CarSpecs;
import org.openqa.selenium.By;

public class ResearchPage extends MainForm{

    Button mainPageBtn = new Button(By.className("global-nav__logo"), "Main page button");
    Button compareBtn = new Button(By.xpath("//a[@data-linkname='compare-cars']"), "Compare cars button");
    CarChooser carChooser = new CarChooser();

    public ResearchPage() {
        uniqueElement = compareBtn;
    }

    @Override
    public void goToMainPage() {
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

}
