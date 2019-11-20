package project.tests;

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

        mainForm.goToGameGenre();
        IndieGamesForm indieForm = new IndieGamesForm();
        Assert.assertTrue(indieForm.isOnThePage());

        indieForm.selectTab(TableTab.TOP_SELLERS);
        Game gameFromTable = indieForm.getDiscountedGame(SortBy.MIN);
        indieForm.goToGame(gameFromTable);

        GameForm gameForm = new GameForm();
        Assert.assertTrue(gameForm.isOnThePage(), "Not on the page of the game");

        Game gameFromPage = gameForm.getGameOnForm();
        Assert.assertEquals(gameFromTable.getName(), gameFromPage.getName(), "Game from table doesn't match with game from game page");
    }
}
