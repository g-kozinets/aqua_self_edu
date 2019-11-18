package carsProject.pages;

import static framework.logger.MyLogger.log;
import framework.pageElements.Button;
import framework.pageElements.Text;
import org.openqa.selenium.By;
import java.util.ArrayList;

public class ComparisonTable extends MainForm {
    private static final String HEADER_TAG = "//cars-compare-compare-info[@header='%s']";
    private static final String INFO_TAG = "//span[contains(@class, 'info-data')][%s]//p[contains(@ng-repeat, 'listItem')]";
    private Button lastCarImgBtn = new Button(By.xpath("//*[@id='image-header']/span[contains(@class, 'info-data')][last()]"), "Last car image");
    private Text tableText;

    public ComparisonTable() {
        uniqueElement = lastCarImgBtn;
    }

    public ArrayList<String> getTableAttributes(int carNumber, String... attribute) {
        log.info("Getting attribute info from table");
        ArrayList<String> trimArr = new ArrayList<String>();

        for (int i = 0; i < attribute.length; i++) {
            tableText = new Text(By.xpath(String.format(HEADER_TAG + INFO_TAG, attribute[i], carNumber)), "Text from table");
            trimArr.add(tableText.getText());
        }
        return trimArr;
    }

}
