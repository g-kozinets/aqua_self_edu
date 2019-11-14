package carsProject.tests;

import carsProject.models.CarSpecs;
import carsProject.pages.*;
import org.testng.Assert;

public class Steps {

    private CarPage carPage;
    private TrimPage trimPage;

    public boolean validateCarTrim(CarSpecs...car) {
        String engine = "Engine";
        String trans = "Transmission";
        CarSpecs firstCarInTable = car[0];
        CarSpecs secondCarInTable = car[1];
        ComparisonTable table = new ComparisonTable();

        firstCarInTable.setTrim(table.getTableAttributes(1, engine, trans));

        secondCarInTable.setTrim(table.getTableAttributes(2, engine, trans));

        return car[0].equals(firstCarInTable) && car[1].equals(secondCarInTable);
    }

    public CarSpecs getRandCarInfo(MainForm mainForm) {
        CarSpecs carSpecs = getRandCarWithTrims(mainForm);

        carPage.goToTrims();
        trimPage = new TrimPage();

        Assert.assertTrue(trimPage.isOnThePage(), "Not on trims page: ");

        carSpecs.setEngine(trimPage.getEngineModel());
        carSpecs.setTransmission(trimPage.getTransmissionModel());

        return carSpecs;
    }

    private CarSpecs getRandCarWithTrims(MainForm mainForm) {
        CarSpecs carSpecs = null;
        boolean hasTrim = false;
        while (!hasTrim) {
            mainForm.goToResearch();
            ResearchPage researchPage = new ResearchPage();
            carSpecs = researchPage.getRandomCar();

            Assert.assertEquals(researchPage.getChosenParam(), carSpecs.getFullName(), "Incorrect selection in combobox");

            researchPage.doSearch();
            carPage = new CarPage();

            Assert.assertTrue(carPage.isOnThePage(), "Not on selected car's page: ");

            hasTrim = carPage.checkTrims();
        }
        return carSpecs;
    }
}
