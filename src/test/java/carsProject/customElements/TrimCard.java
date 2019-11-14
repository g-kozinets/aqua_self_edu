package carsProject.customElements;

import framework.pageElements.BaseElement;
import framework.pageElements.Text;
import org.openqa.selenium.By;

public class TrimCard extends BaseElement {

    private Text engine = new Text(By.xpath("//div[@class='trim-details']/div[@class='trim-card']/div[4]"), "Engine info");
    private Text transmission = new Text(By.xpath("//div[@class='trim-details']/div[@class='trim-card']/div[5]"), "Transmission info");

    public TrimCard(By locator, String elementName) {
        super(locator, elementName);
    }

    public String getEngine() {
        return engine.getText();
    }

    public String getTransmission() {
        return transmission.getText();
    }
}