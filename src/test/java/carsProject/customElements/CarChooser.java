package carsProject.customElements;

import carsProject.pages.MainForm;
import framework.driver.Browser;
import framework.pageElements.Button;
import framework.pageElements.DropDownList;
import framework.utils.TestUtils;
import carsProject.models.CarSpecs;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CarChooser extends MainForm {
    private static String MAKERS_LIST = "//select[@name='makeId']";
    private static String MODELS_LIST = "//select[@name='modelId']";
    private static String YEARS_LIST = "//select[@name='year']";
    DropDownList makers;
    DropDownList models;
    DropDownList years;
    private Button searchBtn = new Button(By.xpath("//input[@value='Search']"), "Search button");


    public CarSpecs getRandomCar() {
        makers = new DropDownList(By.xpath(MAKERS_LIST), "List of makers");
        models = new DropDownList(By.xpath(MODELS_LIST), "List of models");
        years = new DropDownList(By.xpath(YEARS_LIST), "List of years");

        String makerName = (String) TestUtils.getRandomElement(getOptionsList(makers));
        makers.selectItem(makerName);

        String modelName = (String) TestUtils.getRandomElement(getOptionsList(models));
        models.selectItem(modelName);

        String year = (String) TestUtils.getRandomElement(getOptionsList(years));
        years.selectItem(year);

        return new CarSpecs(makerName, modelName, Integer.parseInt(year));
    }

    public String getChosenParam() {
        return String.format("%s %s %s", years.getSelectedOption(), makers.getSelectedOption(), models.getSelectedOption());
    }

    public void doSearch() {
        searchBtn.click();
    }

    private ArrayList getOptionsList(DropDownList list) {
        ArrayList<String> listString = new ArrayList<>();

        for (WebElement item : list.getOptions()) {
            listString.add(item.getText());
        }
        listString.remove(0);
        return listString;
    }
}
