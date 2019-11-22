package project.tests;

import framework.utils.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import project.forms.DownloadForm;
import project.forms.MainForm;
import static framework.logger.MyLogger.logger;

public class DownloadInstallTest extends BaseTest{
    private MainForm mainForm;
    private DownloadForm downloadForm;

    @Test
    public void downloadInstallTest() {
        mainForm = new MainForm();
        Assert.assertTrue(mainForm.isOnThePage(), "Not on main page");

        logger.info("Selecting language");
        mainForm.selectLanguage(dictionary.getLanguage());

        logger.info("Going to install page");
        mainForm.goToInstall();
        downloadForm = new DownloadForm();
        Assert.assertTrue(downloadForm.isOnThePage(), "Not on download page");

        logger.info("Starting download");
        downloadForm.clickDownload();

        logger.info("Finishing test");
        FileUtils.clearOutput();
    }

}
