package marketTest.customElements;

import framework.driver.Browser;
import framework.pageElements.BaseElement;
import framework.pageElements.Button;
import framework.utils.TestUtils;
import marketTest.models.CarSpecs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CarChooser {
    private static String LIST_ELEM_NAME = "[text()='%s']";
    private static String LIST_POSTFIX = "/option";
    private static String MAKERS_LIST = "//select[@name='makeId']";
    private static String MODELS_LIST = "//select[@name='modelId']";
    private static String YEARS_LIST = "//select[@name='year']";
    private List<WebElement> makers = new ArrayList<>();
    private List<WebElement> models = new ArrayList<>();
    private List<WebElement> years = new ArrayList<>();
    public ArrayList<CarSpecs> carsList = new ArrayList<>();
    private Button searchBtn = new Button(By.xpath("//input[@value='Search']"), "Search button");
    private Button makerBtn = new Button(By.xpath(MAKERS_LIST), "");
    private Button modelBtn = new Button(By.xpath(MODELS_LIST), "");
    private Button yearBtn = new Button(By.xpath(YEARS_LIST), "");


    public CarSpecs getRandomCar() {
        Button listElemBtn;

        String makerName = (String) TestUtils.getRandomElement(getMakers());
        makerBtn.waitAndClick();
        listElemBtn = new Button(By.xpath(String.format(MAKERS_LIST + LIST_POSTFIX + LIST_ELEM_NAME, makerName)), "");
        listElemBtn.click();

        String modelName = (String) TestUtils.getRandomElement(getModels());
        modelBtn.click();
        listElemBtn = new Button(By.xpath(String.format(MODELS_LIST + LIST_POSTFIX + LIST_ELEM_NAME, modelName)), "");
        listElemBtn.click();

        String year = (String) TestUtils.getRandomElement(getYears());
        yearBtn.click();
        listElemBtn = new Button(By.xpath(String.format(YEARS_LIST + LIST_POSTFIX + LIST_ELEM_NAME, year)), "");
        listElemBtn.click();

        return new CarSpecs(makerName, modelName, Integer.parseInt(year));
    }

    public void doSearch() {
        searchBtn.click();
    }

    public ArrayList getMakers() {
        makers = Browser.getDriver().findElements(By.xpath(MAKERS_LIST + LIST_POSTFIX));
        makers.remove(0);
        ArrayList<String> makersString = new ArrayList<>();

        for (WebElement item : makers) {
            makersString.add(item.getText());
        }
        return makersString;
    }

    public ArrayList getModels() {
        models = Browser.getDriver().findElements(By.xpath(MODELS_LIST + LIST_POSTFIX));
        models.remove(0);
        ArrayList<String> modelsString = new ArrayList<>();

        for (WebElement item : models) {
            modelsString.add(item.getText());
        }
        return modelsString;
    }

    public ArrayList getYears() {
        years = Browser.getDriver().findElements(By.xpath(YEARS_LIST + LIST_POSTFIX));
        years.remove(0);
        ArrayList<String> yearsString = new ArrayList<>();

        for (WebElement item : years) {
            yearsString.add(item.getText());
        }
        return yearsString;
    }
}
