package marketTest;

import framework.driver.Browser;
import marketTest.carsPages.*;
import marketTest.customElements.CarChooser;
import marketTest.models.CarSpecs;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import framework.utils.PropertyReader;

import java.awt.image.CropImageFilter;

public class MarketTests extends BaseTest {

    private MainPage mainPage;
    private ResearchPage researchPage;
    private CarPage carPage;
    private TrimPage trimPage;

    @Test
    public void categoryTest() throws Exception {
        mainPage = new MainPage();
        CarSpecs firstCar;
        CarSpecs secondCar;
        researchPage = new ResearchPage();

        //Загрузка главной страницы
        Browser.goToUrl(PropertyReader.getProp("URL"));

        firstCar = getRandomCarInfo();

        Browser.goToUrl(PropertyReader.getProp("URL"));

        secondCar = getRandomCarInfo();

        mainPage.goToResearch();
        researchPage.goToCompare();

        ComparePage comparePage = new ComparePage();
        comparePage.defineCarToCompare(firstCar.getMaker(), firstCar.getModel(), firstCar.getYear());
        comparePage.startComparing();
        comparePage.addCarToCompare(secondCar.getMaker(), secondCar.getModel(), secondCar.getYear());


    }

    public CarSpecs getRandomCarInfo() {
        CarSpecs carSpecs;
        mainPage.goToResearch();
        carSpecs = researchPage.getRandomCar();
        researchPage.doSearch();
        carPage = new CarPage();
        carPage.goToTrims();
        trimPage = new TrimPage();
        carSpecs.setEngine(trimPage.getEngineModel());
        carSpecs.setTransmission(trimPage.getTransmissionModel());

        return carSpecs;
    }

}
