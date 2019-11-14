package marketTest.carsPages;
   // "//cars-compare-compare-info[@header='Transmission']//span[@class='info-data col-three-cars']//p[contains(@ng-repeat, 'listItem')][1]"

import framework.driver.Browser;
import framework.pageElements.Button;
import marketTest.customElements.CarChooser;
import org.openqa.selenium.By;

import java.util.ArrayList;

public class ComparisonTable extends CarChooser {
    private static String HEADER_TAG = "//cars-compare-compare-info[@header='%s']";
    private static String INFO_TAG = "//span[@class='info-data col-three-cars'][%s]//p[contains(@ng-repeat, 'listItem')]";
    private Button lastCarImgBtn = new Button(By.xpath("//*[@id='image-header']/span[@class='info-data image col-three-cars'][last()]"), "");
    private String indexAttribute = "index";

    public int getNumberOfCars() {
        return (int) lastCarImgBtn.getAttribute(indexAttribute);
    }

    public ArrayList<String> getTableAttributes(int carNumber, String... attribute) {
        ArrayList<String> trimArr = new ArrayList<String>();
        //String[] trimArray = {Browser.getDriver().findElement(By.xpath(String.format(HEADER_TAG + INFO_TAG, attribute, carNumber))).getText()};

        for (int i = 0; i < attribute.length; i++) {
            trimArr.add(Browser.getDriver().findElement(By.xpath(String.format(HEADER_TAG + INFO_TAG, attribute[i], carNumber))).getText());

        }
        //return Browser.getDriver().findElement(By.xpath(String.format(HEADER_TAG + INFO_TAG, attribute, carNumber))).getText();
        return trimArr;
    }

}
