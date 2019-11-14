package carsProject.pages;

import carsProject.customElements.TrimCard;
import org.openqa.selenium.By;

public class TrimPage extends BaseForm{

    private TrimCard trimCard = new TrimCard(By.className("trim-card"), "Trim card");

    public TrimPage() {
        uniqueElement = trimCard;
    }

    public String getEngineModel() {
        return trimCard.getEngine();
    }

    public String getTransmissionModel() {
        return trimCard.getTransmission();
    }

}
