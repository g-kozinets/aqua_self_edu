package marketTest.customElements;

import framework.driver.Browser;
import framework.pageElements.BaseElement;
import framework.pageElements.Button;
import framework.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CarChooser extends BaseElement {
    private String listElemName;
    private static String LIST_ELEM_NAME = "[text()='%s']";
    private static String LIST_POSTFIX = "/option";
    private static String MAKERS_LIST = "//select[@name='makeId']";
    private static String MODELS_LIST = "//select[@name='modelId']";
    private static String YEARS_LIST = "//select[@name='year']";
    private List<WebElement> makers = new ArrayList<>();
    private List<WebElement> models = new ArrayList<>();
    private List<WebElement> years = new ArrayList<>();
    private Button searchBtn = new Button(By.xpath("//input[@value='Search']"), "Search button");
    private Button makerBtn = new Button(By.xpath(MAKERS_LIST), "");
    private Button modelBtn = new Button(By.xpath(MODELS_LIST), "");
    private Button yearBtn = new Button(By.xpath(YEARS_LIST), "");

    public CarChooser(By locator, String elementName) {
        super(locator, elementName);
    }

    public void loadData() {
        Button makersBtn = new Button(By.xpath(MAKERS_LIST), "list of makers");
        //makersBtn.click();
        makers = Browser.getDriver().findElements(By.xpath(MAKERS_LIST + LIST_POSTFIX));

    }

    public void chooseRandomCar() {
        Button listElemBtn;

        listElemName = (String) TestUtils.getRandomElement(getMakers());
        makerBtn.waitAndClick();
        listElemBtn = new Button(By.xpath(String.format(MAKERS_LIST + LIST_POSTFIX + LIST_ELEM_NAME, listElemName)), "");
        listElemBtn.click();

        listElemName = (String) TestUtils.getRandomElement(getModels());
        modelBtn.click();
        listElemBtn = new Button(By.xpath(String.format(MODELS_LIST + LIST_POSTFIX + LIST_ELEM_NAME, listElemName)), "");
        listElemBtn.click();

        listElemName = (String) TestUtils.getRandomElement(getYears());
        yearBtn.click();
        listElemBtn = new Button(By.xpath(String.format(YEARS_LIST + LIST_POSTFIX + LIST_ELEM_NAME, listElemName)), "");
        listElemBtn.click();

    }

    public void doSearch() {
        searchBtn.click();
    }

    public ArrayList getMakers() {
        makers = Browser.getDriver().findElements(By.xpath(MAKERS_LIST + LIST_POSTFIX));
        ArrayList<String> makersString = new ArrayList<>();

        for (WebElement item : makers) {
            makersString.add(item.getText());
        }
        return makersString;
    }

    public ArrayList getModels() {
        models = Browser.getDriver().findElements(By.xpath(MODELS_LIST + LIST_POSTFIX));
        ArrayList<String> modelsString = new ArrayList<>();

        for (WebElement item : models) {
            modelsString.add(item.getText());
        }
        return modelsString;
    }

    public ArrayList getYears() {
        years = Browser.getDriver().findElements(By.xpath(YEARS_LIST + LIST_POSTFIX));
        ArrayList<String> yearsString = new ArrayList<>();

        for (WebElement item : years) {
            yearsString.add(item.getText());
        }
        return yearsString;
    }
}
