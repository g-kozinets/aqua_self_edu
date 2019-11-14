package carsProject.tests;

import carsProject.pages.*;
import carsProject.models.CarSpecs;
import org.testng.Assert;
import org.testng.annotations.*;

public class CarsTest extends BaseTest {

    private MainForm mainForm;
    private ResearchPage researchPage;
    private CarPage carPage;
    private TrimPage trimPage;
    private CarSpecs firstCar;
    private CarSpecs secondCar;

    @Test
    public void categoryTest() throws Exception {
        mainForm = new MainForm();

        //Загрузка главной страницы
        Assert.assertTrue(mainForm.isOnThePage());


        firstCar = getRandCarInfo();

        researchPage.goToMainPage();

        secondCar = getRandCarInfo();

        mainForm.goToResearch();
        researchPage.goToCompare();

        ComparePage comparePage = new ComparePage();
        comparePage.initiateCarComparison(firstCar);
        comparePage.startComparing();
        comparePage.addCarToCompare(secondCar);

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
            mainForm.goToResearch();
            researchPage = new ResearchPage();
            carSpecs = researchPage.getRandomCar();
            researchPage.doSearch();
            carPage = new CarPage();
            hasTrim = carPage.checkTrims();
        }
        return carSpecs;
    }

}
