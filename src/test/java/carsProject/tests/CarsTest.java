package carsProject.tests;

import carsProject.pages.*;
import carsProject.models.Car;
import carsProject.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.*;

public class CarsTest extends BaseTest {

    private MainForm mainForm;
    private ResearchPage researchPage;
    private Car firstCar;
    private Car secondCar;

    @Test
    public void categoryTest() {
        mainForm = new MainForm();

        //Загрузка главной страницы
        Assert.assertTrue(mainForm.isOnThePage());

        mainForm.goToResearch();
        researchPage = new ResearchPage();
        Assert.assertTrue(researchPage.isOnThePage(), "Not on research page");

        firstCar = new Steps().getRandCarInfo();

        researchPage.goToMainPage();
        Assert.assertTrue(mainForm.isOnThePage(), "Not on main page");

        secondCar = new Steps().getRandCarInfo();

        mainForm.goToResearch();
        Assert.assertTrue(researchPage.isOnThePage(), "Not on research page");

        researchPage.goToCompare();
        ComparePage comparePage = new ComparePage();
        Assert.assertTrue(comparePage.isOnThePage(), "Not on car page");

        comparePage.initiateCarComparison(firstCar);
        comparePage.startComparing();
        Assert.assertEquals(comparePage.getCarNames().get(0), firstCar.getFullName(), "First car name in comparison doesn't match");

        comparePage.addCarToCompare(secondCar);
        Assert.assertEquals(comparePage.getCarNames().get(1), secondCar.getFullName(), "Second car name in comparison doesn't match");

        Assert.assertTrue(new Steps().carTrimIsSame(firstCar, secondCar), "Trims in compare table doesn't match");
    }
}
