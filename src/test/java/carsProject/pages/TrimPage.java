package carsProject.pages;

import carsProject.customElements.TrimCard;

public class TrimPage extends BaseForm{

    private TrimCard trimCard = new TrimCard();

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
