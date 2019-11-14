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
}
