package project.tests;

import framework.utils.FileUtils;
import project.enums.SortBy;
import project.enums.TableTab;
import project.forms.*;
import org.testng.Assert;
import org.testng.annotations.*;
import project.models.Game;
import java.io.IOException;
import static framework.logger.MyLogger.logger;


public class SteamTest extends BaseTest {

    private MainForm mainForm;
    private IndieGamesForm indieForm;
    private DownloadForm downloadForm;

    @Test
    public void downloadInstallTest() throws IOException {
        mainForm = new MainForm();
        Assert.assertTrue(mainForm.isOnThePage(), "Not on main page");

        mainForm.goToInstall();
        downloadForm = new DownloadForm();
        Assert.assertTrue(downloadForm.isOnThePage(), "Not on download page");

        downloadForm.clickDownload();
        logger.info("Finishing test");
        FileUtils.clearOutput();
    }

    @Test
    public void discountsTest() {
        mainForm = new MainForm();
        Assert.assertTrue(mainForm.isOnThePage(), "Not on main page");

        mainForm.goToGameGenre();
        indieForm = new IndieGamesForm();
        Assert.assertTrue(indieForm.isOnThePage());

        indieForm.selectTab(TableTab.TOP_SELLERS);
        Game gameFromTable = indieForm.getDiscountedGame(SortBy.MIN);
        indieForm.goToGame(gameFromTable);

        GameForm gameForm = new GameForm();
        Game gameFromPage = gameForm.getGameOnForm();
        Assert.assertEquals(gameFromTable.getName(), gameFromPage.getName(), "Not on the right game page");

        Assert.assertEquals(gameFromTable, gameFromPage, "Game prices doesn't match");
    }
}
