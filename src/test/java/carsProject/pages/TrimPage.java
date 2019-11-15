package carsProject.pages;

import framework.pageElements.Text;
import org.openqa.selenium.By;
import static framework.logger.MyLogger.log;

public class TrimPage extends BaseForm{
    private Text engine = new Text(By.xpath("//div[@class='trim-details']/div[@class='trim-card']/div[4]"), "Engine info");
    private Text transmission = new Text(By.xpath("//div[@class='trim-details']/div[@class='trim-card']/div[5]"), "Transmission info");

    public TrimPage() {
        uniqueElement = engine;
    }

    public String getEngineModel() {
        log.info("Getting engine model");
        return engine.getText();
    }

    public String getTransmissionModel() {
        log.info("Getting transmission model");
        return transmission.getText();
    }

}
