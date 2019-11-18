package project.pages;

import framework.pageElements.Text;
import framework.utils.TestUtils;
import org.openqa.selenium.By;

import static framework.logger.MyLogger.log;

public class TrimPage extends BaseForm{
    private Text trimTitleTxt = new Text(By.className("trim-header__title"), "Trim header");
    private static String TABLE_HEADER_TAG = "(//*[@id='labels-row'])[1]/div[contains(@class, 'cell') and text()='%s']";
    private static String CELL_TAG = "//div[@class='trim-details']/div[@class='trim-card']/div[%s]";
    private String engineHeader = "Engine";
    private String transmissionHeader = "Trans";
    private int index;


    public TrimPage() {
        uniqueElement = trimTitleTxt;
    }

    public String getEngineModel() {
        log.info("Getting engine model");

        index = getColumnNumber(engineHeader);
        return new Text(By.xpath(String.format(CELL_TAG, index)), "Engine info").getText();
    }

    public String getTransmissionModel() {
        log.info("Getting transmission model");

        index = getColumnNumber(transmissionHeader);
        return new Text(By.xpath(String.format(CELL_TAG, index)), "Engine info").getText();
    }

    private int getColumnNumber(String columnName) {
       return TestUtils.getTagPosition(By.xpath(String.format(TABLE_HEADER_TAG + "/preceding-sibling::*", columnName)));
    }

}
