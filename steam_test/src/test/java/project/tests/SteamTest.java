package project.tests;

import framework.utils.FileUtils;
import project.pages.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static framework.logger.MyLogger.logger;


public class SteamTest extends BaseTest {

    private MainForm mainForm;
    private DownloadForm downloadForm;

    @Test
    public void downloadInstallTest() throws IOException {

        logger.info("Loading main page");
        mainForm = new MainForm();

        Assert.assertTrue(mainForm.isOnThePage(), "Not on main page");

        mainForm.goToInstall();
        downloadForm = new DownloadForm();
        Assert.assertTrue(downloadForm.isOnThePage(), "Not on download page");

        downloadForm.clickDownload();
        logger.info("Finishing test");
        //FileUtils.clearOutput();
    }

    @Test
    public void discountsTest() {
        mainForm = new MainForm();
        Assert.assertTrue(mainForm.isOnThePage(), "Not on main page");



    }
}
