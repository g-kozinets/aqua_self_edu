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
    public void categoryTest() {
        mainForm = new MainForm();

        //Загрузка главной страницы
        Assert.assertTrue(mainForm.isOnThePage());


        mainForm.goToResearch();
        researchPage = new ResearchPage();
        Assert.assertTrue(researchPage.isOnThePage(), "Not on research page");

        firstCar = new Steps().getRandCarInfo(mainForm);

        researchPage.goToMainPage();
        Assert.assertTrue(mainForm.isOnThePage(), "Not on main page");

        secondCar = new Steps().getRandCarInfo(mainForm);

        mainForm.goToResearch();
        Assert.assertTrue(researchPage.isOnThePage(), "Not on research page");

        researchPage.goToCompare();
        ComparePage comparePage = new ComparePage();
        Assert.assertTrue(comparePage.isOnThePage(), "Not on car page");

        comparePage.initiateCarComparison(firstCar);
        comparePage.startComparing();
        Assert.assertEquals(comparePage.getCarNames().get(0), firstCar.getFullName(), "First car name in comparison doesn't match");

        comparePage.addCarToCompare(secondCar);
        Assert.assertEquals(comparePage.getCarNames().get(1), secondCar.getFullName(), "Second car name in comparison doesn't match\"");

        Assert.assertTrue(new Steps().validateCarTrim(firstCar, secondCar), "Trims in compare table doesn't match");
    }
}
