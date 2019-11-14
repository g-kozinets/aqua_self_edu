package marketTest;

import framework.driver.Browser;
import marketTest.carsPages.*;
import marketTest.models.CarSpecs;
import org.testng.annotations.*;
import framework.utils.PropertyReader;

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

        firstCar = getRandCarInfo();

        Browser.goToUrl(PropertyReader.getProp("URL"));

        secondCar = getRandCarInfo();

        mainPage.goToResearch();
        researchPage.goToCompare();

        ComparePage comparePage = new ComparePage();
        comparePage.defineCarToCompare(firstCar.getMaker(), firstCar.getModel(), firstCar.getYear());
        comparePage.startComparing();
        comparePage.addCarToCompare(secondCar.getMaker(), secondCar.getModel(), secondCar.getYear());


    }

    public CarSpecs getRandCarInfo() {
        CarSpecs carSpecs = getRandCarWithTrims();

        carPage.goToTrims();
        trimPage = new TrimPage();
        carSpecs.setEngine(trimPage.getEngineModel());
        carSpecs.setTransmission(trimPage.getTransmissionModel());

        return carSpecs;
    }

    public CarSpecs getRandCarWithTrims() {
        CarSpecs carSpecs = null;
        boolean hasTrim = false;
        while (!hasTrim) {
            mainPage.goToResearch();
            carSpecs = researchPage.getRandomCar();
            researchPage.doSearch();
            carPage = new CarPage();
            hasTrim = carPage.checkTrims();
        }
        return carSpecs;
    }

}
