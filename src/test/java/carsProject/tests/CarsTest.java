package carsProject.tests;

import carsProject.pages.*;
import carsProject.models.CarSpecs;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

public class CarsTest extends BaseTest {

    private MainForm mainForm;
    private ResearchPage researchPage;
    private CarSpecs firstCar;
    private CarSpecs secondCar;

    @Test
    public void categoryTest() throws Exception {
        mainForm = new MainForm();

        //Загрузка главной страницы
        Assert.assertTrue(mainForm.isOnThePage());


        mainForm.goToResearch();
        researchPage = new ResearchPage();
        Assert.assertTrue(researchPage.isOnThePage());

        firstCar = new Steps().getRandCarInfo(mainForm);

        researchPage.goToMainPage();
        Assert.assertTrue(mainForm.isOnThePage());

        secondCar = new Steps().getRandCarInfo(mainForm);

        mainForm.goToResearch();
        Assert.assertTrue(researchPage.isOnThePage());

        researchPage.goToCompare();
        ComparePage comparePage = new ComparePage();
        Assert.assertTrue(comparePage.isOnThePage());

        comparePage.initiateCarComparison(firstCar);
        comparePage.startComparing();
        Assert.assertEquals(comparePage.getCarNames().get(0), firstCar.getFullName());

        comparePage.addCarToCompare(secondCar);
        Assert.assertEquals(comparePage.getCarNames().get(1), secondCar.getFullName());

        Assert.assertTrue(new Steps().validateCarTrim(firstCar, secondCar));
    }

//    private boolean validateCarTrim() {
//        String engine = "Engine";
//        String trans = "Transmission";
//        CarSpecs firstCarInTable = firstCar;
//        CarSpecs secondCarInTable = secondCar;
//        ComparisonTable table = new ComparisonTable();
//
//        firstCarInTable.setTrim(table.getTableAttributes(1, engine, trans));
//
//        secondCarInTable.setTrim(table.getTableAttributes(2, engine, trans));
//
//        return firstCar.equals(firstCarInTable) && secondCar.equals(secondCarInTable);
//    }
//
//    private CarSpecs getRandCarInfo() {
//        CarSpecs carSpecs = getRandCarWithTrims();
//
//        carPage.goToTrims();
//        trimPage = new TrimPage();
//        carSpecs.setEngine(trimPage.getEngineModel());
//        carSpecs.setTransmission(trimPage.getTransmissionModel());
//
//        return carSpecs;
//    }
//
//    private CarSpecs getRandCarWithTrims() {
//        CarSpecs carSpecs = null;
//        boolean hasTrim = false;
//        while (!hasTrim) {
//            mainForm.goToResearch();
//            researchPage = new ResearchPage();
//            carSpecs = researchPage.getRandomCar();
//            researchPage.doSearch();
//            carPage = new CarPage();
//            hasTrim = carPage.checkTrims();
//        }
//        return carSpecs;
//    }

}
