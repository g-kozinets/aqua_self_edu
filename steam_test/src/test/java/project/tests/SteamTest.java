package project.tests;

import project.pages.*;
import org.testng.Assert;
import org.testng.annotations.*;
import static framework.logger.MyLogger.logger;


public class SteamTest extends BaseTest {

    private MainForm mainForm;

    @Test
    public void categoryTest() {

        logger.info("Loading main page");
        mainForm = new MainForm();

        Assert.assertTrue(mainForm.isOnThePage());

        mainForm.goToInstall();
        logger.info("Finishing test");
    }
}
