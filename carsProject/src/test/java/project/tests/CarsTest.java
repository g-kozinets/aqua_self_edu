package project.tests;

import project.pages.*;
import project.models.Car;
import project.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.*;
import static framework.logger.MyLogger.logger;


public class CarsTest extends BaseTest {

    private MainForm mainForm;
    private ResearchPage researchPage;
    private Car firstCar;
    private Car secondCar;

    @Test
    public void categoryTest() {

        logger.info("Loading main page");
        mainForm = new MainForm();

        Assert.assertTrue(mainForm.isOnThePage());

        logger.info("Loading research page");
        mainForm.goToResearch();
        researchPage = new ResearchPage();
        Assert.assertTrue(researchPage.isOnThePage(), "Not on research page");

        logger.info("Choosing first car");
        firstCar = new Steps().getRandCarInfo();

        logger.info("Loading main page");
        researchPage.goToMainPage();
        Assert.assertTrue(mainForm.isOnThePage(), "Not on main page");

        logger.info("Choosing second car");
        secondCar = new Steps().getRandCarInfo();

        logger.info("Loading research page");
        mainForm.goToResearch();
        Assert.assertTrue(researchPage.isOnThePage(), "Not on research page");

        logger.info("Loading compare page");
        researchPage.goToCompare();
        ComparePage comparePage = new ComparePage();
        Assert.assertTrue(comparePage.isOnThePage(), "Not on car page");

        logger.info("Starting comparison");
        comparePage.initiateCarComparison(firstCar);
        comparePage.startComparing();
        Assert.assertEquals(comparePage.getCarNames().get(0), firstCar.getFullName(), "First car name in comparison doesn't match");

        logger.info("Adding second car to comparison");
        comparePage.addCarToCompare(secondCar);
        Assert.assertEquals(comparePage.getCarNames().get(1), secondCar.getFullName(), "Second car name in comparison doesn't match");

        Assert.assertTrue(new Steps().carTrimIsSame(firstCar, secondCar), "Trims in compare table doesn't match");
        logger.info("Finishing test");
    }
}
