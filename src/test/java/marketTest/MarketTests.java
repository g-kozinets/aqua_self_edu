package marketTest;

import framework.driver.Browser;
import marketTest.carsPages.MainPage;
import marketTest.carsPages.ResearchPage;
import marketTest.carsPages.CarPage;
import marketTest.carsPages.TrimPage;
import marketTest.customElements.CarChooser;
import marketTest.models.CarSpecs;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import framework.utils.PropertyReader;

public class MarketTests extends BaseTest {

    @Test
    public void categoryTest() throws Exception {
        MainPage mainPage = new MainPage();
        CarSpecs firstCar;
        CarSpecs secondCar;
        ResearchPage researchPage = new ResearchPage();
        CarChooser carChooser = new CarChooser(By.xpath(""), "");

        //Загрузка главной страницы
        Browser.goToUrl(PropertyReader.getProp("URL"));

        mainPage.goToResearch();

        firstCar = carChooser.getRandomCar();
        carChooser.doSearch();

        CarPage carPage = new CarPage();
        carPage.goToTrims();

        TrimPage trimPage = new TrimPage();
        firstCar.setEngine(trimPage.getEngineModel());
        firstCar.setTransmission(trimPage.getTransmissionModel());

        Browser.goToUrl(PropertyReader.getProp("URL"));

        mainPage.goToResearch();
        secondCar = carChooser.getRandomCar();
        carChooser.doSearch();
        carPage.goToTrims();
        secondCar.setEngine(trimPage.getEngineModel());
        secondCar.setTransmission(trimPage.getTransmissionModel());



    }

}
