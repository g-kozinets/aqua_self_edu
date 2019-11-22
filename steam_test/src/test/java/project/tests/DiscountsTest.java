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
    private IndieGamesForm indieForm;

    @Test
    public void discountsTest() {
        mainForm = new MainForm();
        Assert.assertTrue(mainForm.isOnThePage(), "Not on main page");

        logger.info("Selecting language");
        mainForm.selectLanguage(language);

        logger.info("Selecting genre");
        mainForm.menu().selectGenre(genreName);
        indieForm = new IndieGamesForm();
        Assert.assertTrue(indieForm.isOnThePage(), "Not on game genre page");

        logger.info("Selecting tab");
        indieForm.tabs().selectTab(TableTab.TOP_SELLERS);
        Assert.assertEquals(indieForm.tabs().getSelectedTab(), tabName, "Selected wrong tab");

        logger.info("Getting game on sale");
        Game gameFromTable = indieForm.getDiscountedGame(SortBy.MIN);

        logger.info("Going to game page");
        indieForm.goToGame(gameFromTable);
        GameForm gameForm = new GameForm();
        Game gameFromPage = gameForm.getGameOnForm();
        Assert.assertEquals(gameFromTable.getName(), gameFromPage.getName(), "Not on the right game page");

        Assert.assertEquals(gameFromTable, gameFromPage, "Game prices doesn't match");
        logger.info("Finishing test");
    }
}
