package carsProject.pages;

import framework.driver.Browser;
import framework.pageElements.Button;
import carsProject.customElements.CarChooser;
import org.openqa.selenium.By;
import java.util.ArrayList;

public class ComparisonTable extends CarChooser {
    private static String HEADER_TAG = "//cars-compare-compare-info[@header='%s']";
    private static String INFO_TAG = "//span[contains(@class, 'info-data')][%s]//p[contains(@ng-repeat, 'listItem')]";
    private Button lastCarImgBtn = new Button(By.xpath("//*[@id='image-header']/span[contains(@class, 'info-data')][last()]"), "");
    private String indexAttribute = "index";

    public ComparisonTable() {
        uniqueElement = lastCarImgBtn;
    }

    public int getNumberOfCars() {
        return (int) lastCarImgBtn.getAttribute(indexAttribute);
    }

    public ArrayList<String> getTableAttributes(int carNumber, String... attribute) {
        ArrayList<String> trimArr = new ArrayList<String>();

        for (int i = 0; i < attribute.length; i++) {
            trimArr.add(Browser.getDriver().findElement(By.xpath(String.format(HEADER_TAG + INFO_TAG, attribute[i], carNumber))).getText());

        }
        return trimArr;
    }

}
