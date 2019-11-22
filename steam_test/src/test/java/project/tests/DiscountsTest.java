package project.tests;

import project.enums.SortBy;
import project.enums.TableTab;
import project.forms.*;
import org.testng.Assert;
import org.testng.annotations.*;
import project.models.Game;
import static framework.logger.MyLogger.logger;


public class DiscountsTest extends BaseTest {

    private MainForm mainForm;
    private GenreForm genreForm;

    @Test
    public void discountsTest() {
        mainForm = new MainForm();
        Assert.assertTrue(mainForm.isOnThePage(), "Not on main page");

        logger.info("Selecting language");
        mainForm.selectLanguage(dictionary.getLanguage());

        logger.info("Selecting genre");
        mainForm.menu().selectGenre(dictionary.getGenreName());
        genreForm = new GenreForm();
        Assert.assertTrue(genreForm.isOnThePage(), "Not on game genre page");
        Assert.assertTrue(genreForm.getGenreHeader().contains(dictionary.getGenreName()), "Not on correct genre page");

        logger.info("Selecting tab");
        genreForm.tabs().selectTab(TableTab.TOP_SELLERS);
        Assert.assertEquals(genreForm.tabs().getSelectedTab(), dictionary.getTabName(), "Selected wrong tab");

        logger.info("Getting game on sale");
        Game gameFromTable = genreForm.getDiscountedGame(SortBy.MIN);

        logger.info("Going to game page");
        genreForm.goToGame(gameFromTable);
        GameForm gameForm = new GameForm();
        Game gameFromPage = gameForm.getGameOnForm();
        Assert.assertEquals(gameFromTable.getName(), gameFromPage.getName(), "Not on the right game page");

        Assert.assertEquals(gameFromTable, gameFromPage, "Game prices doesn't match");
        logger.info("Finishing test");
    }
}
