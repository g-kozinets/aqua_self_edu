package marketTest.carsPages;

import framework.pageElements.Text;
import marketTest.customElements.TrimCard;
import org.openqa.selenium.By;

public class TrimPage extends BaseForm{

    private TrimCard trimCard = new TrimCard();

    public String getEngineModel() {
        return trimCard.getEngine();
    }

    public String getTransmissionModel() {
        return trimCard.getTransmission();
    }

}
