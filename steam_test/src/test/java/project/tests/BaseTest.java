package project.tests;

import framework.driver.Browser;
import framework.logger.MyLogger;
import framework.utils.PropertyReader;
import framework.utils.XmlReader;
import org.testng.annotations.*;
import project.enums.Genre;
import project.enums.TableTab;
import project.models.Dictionary;

import static framework.logger.MyLogger.logger;

public abstract class BaseTest {
    Dictionary dictionary;
    TableTab tab = TableTab.TOP_SELLERS;
    Genre genre = Genre.INDIE;


    @BeforeSuite
    public void setupBrowser() throws Exception {
        logger.info("Setting up logger and browser");
        MyLogger.setupLogger();
        Browser.setupBrowser(PropertyReader.getProp("Browser"));

    }

    @AfterSuite
    public void closeBrowser() {
        Browser.closeBrowser();
    }

    @BeforeMethod
    public void setupTest() throws Exception {
        String lang = PropertyReader.getProp("Lang");
        dictionary = XmlReader.readDictionary(lang, tab, genre);
        Browser.goToUrl(PropertyReader.getProp("URL"));
    }

    @AfterMethod
    public void clearBrowser() {
        logger.info("Clearing Browser cookies");
        Browser.clearCookies();
    }
}
