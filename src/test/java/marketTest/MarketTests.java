package marketTest;

import framework.driver.Browser;
import marketTest.carsPages.*;
import marketTest.models.CarSpecs;
import org.testng.Assert;
import org.testng.annotations.*;
import framework.utils.PropertyReader;

import java.util.ArrayList;

public class MarketTests extends BaseTest {

    private MainPage mainPage;
    private ResearchPage researchPage;
    private CarPage carPage;
    private TrimPage trimPage;
    private CarSpecs firstCar;
    private CarSpecs secondCar;

    @Test
    public void categoryTest() throws Exception {
        mainPage = new MainPage();

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

        Assert.assertTrue(validateCarTrim());
    }

    private boolean validateCarTrim() {
        String engine = "Engine";
        String trans = "Transmission";
        CarSpecs firstCarInTable = firstCar;
        CarSpecs secondCarInTable = secondCar;
        ComparisonTable table = new ComparisonTable();

        firstCarInTable.setTrim(table.getTableAttributes(1, engine, trans));

        secondCarInTable.setTrim(table.getTableAttributes(2, engine, trans));

        return firstCar.equals(firstCarInTable) && secondCar.equals(secondCarInTable);
    }

    private CarSpecs getRandCarInfo() {
        CarSpecs carSpecs = getRandCarWithTrims();

        carPage.goToTrims();
        trimPage = new TrimPage();
        carSpecs.setEngine(trimPage.getEngineModel());
        carSpecs.setTransmission(trimPage.getTransmissionModel());

        return carSpecs;
    }

    private CarSpecs getRandCarWithTrims() {
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
