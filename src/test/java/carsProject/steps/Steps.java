package carsProject.steps;

import carsProject.models.Car;
import carsProject.pages.*;
import org.testng.Assert;

public class Steps {

    private CarPage carPage;
    private TrimPage trimPage;

    public boolean validateCarTrim(Car...car) {
        String engine = "Engine";
        String trans = "Transmission";
        Car firstCarInTable = car[0];
        Car secondCarInTable = car[1];
        ComparisonTable table = new ComparisonTable();

        firstCarInTable.setTrim(table.getTableAttributes(1, engine, trans));

        secondCarInTable.setTrim(table.getTableAttributes(2, engine, trans));

        return car[0].equals(firstCarInTable) && car[1].equals(secondCarInTable);
    }

    public Car getRandCarInfo(MainForm mainForm) {
        Car car = getRandCarWithTrims(mainForm);

        carPage.goToTrims();
        trimPage = new TrimPage();

        Assert.assertTrue(trimPage.isOnThePage(), "Not on trims page: ");

        car.setEngine(trimPage.getEngineModel());
        car.setTransmission(trimPage.getTransmissionModel());

        return car;
    }

    private Car getRandCarWithTrims(MainForm mainForm) {
        Car car = null;
        boolean hasTrim = false;
        while (!hasTrim) {
            mainForm.goToResearch();
            ResearchPage researchPage = new ResearchPage();
            car = researchPage.getRandomCar();

            Assert.assertEquals(researchPage.getChosenParam(), car.getFullName(), "Incorrect selection in combobox");

            researchPage.doSearch();
            carPage = new CarPage();

            Assert.assertTrue(carPage.isOnThePage(), "Not on selected car's page: ");

            hasTrim = carPage.checkTrims();
        }
        return car;
    }
}
