package carsProject.steps;

import carsProject.models.Car;
import carsProject.pages.*;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Steps {

    private CarPage carPage;
    private TrimPage trimPage;
    private MainForm mainForm;
    private String engine = "Engine";
    private String trans = "Transmission";
    private ArrayList<Car> cars;
    private ArrayList<Car> carsInTable = new ArrayList<>();

    public boolean carTrimIsSame(Car...cars) {
        this.cars = new ArrayList<>(Arrays.asList(cars));

        setCarsTrims();

        for (int i = 0; i < this.cars.size(); i++) {
            if (!this.cars.get(i).equals(carsInTable.get(i))) {
                return false;
            }
        }
        return true;
    }

    private void setCarsTrims() {
        ComparisonTable table = new ComparisonTable();

        Iterator<Car> iterator = cars.iterator();
        while(iterator.hasNext()){
            carsInTable.add(iterator.next().clone());
        }

        for (int i = 0; i < carsInTable.size(); i++) {
            carsInTable.get(i).setTrim(table.getTableAttributes(i + 1, engine, trans));
        }
    }

    public Car getRandCarInfo() {
        mainForm = new MainForm();
        Car car = getRandCarWithTrims();

        carPage.goToTrims();
        trimPage = new TrimPage();

        Assert.assertTrue(trimPage.isOnThePage(), "Not on trims page: ");

        car.setEngine(trimPage.getEngineModel());
        car.setTransmission(trimPage.getTransmissionModel());

        return car;
    }

    private Car getRandCarWithTrims() {
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
