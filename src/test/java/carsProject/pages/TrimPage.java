package carsProject.pages;

import carsProject.customElements.TrimCard;
import org.openqa.selenium.By;
import static framework.logger.MyLogger.log;

public class TrimPage extends BaseForm{

    private TrimCard trimCard = new TrimCard(By.className("trim-card"), "Trim card");

    public TrimPage() {
        uniqueElement = trimCard;
    }

    public String getEngineModel() {
        log.info("Getting engine model");
        return trimCard.getEngine();
    }

    public String getTransmissionModel() {
        log.info("Getting transmission");
        return trimCard.getTransmission();
    }

}
