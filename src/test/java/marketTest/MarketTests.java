package marketTest;

import framework.driver.Browser;
import marketTest.carsPages.MainPage;
import marketTest.carsPages.ResearchPage;
import marketTest.customElements.CarChooser;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import framework.utils.PropertyReader;

public class MarketTests extends BaseTest {

    @Test
    public void categoryTest() throws Exception {
        MainPage mainPage = new MainPage();
        ResearchPage researchPage = new ResearchPage();
        CarChooser carChooser = new CarChooser(By.xpath(""), "");

        //Загрузка главной страницы
        Browser.goToUrl(PropertyReader.getProp("URL"));

        mainPage.goToResearch();

        carChooser.chooseRandomCar();
        carChooser.doSearch();

    }

}
