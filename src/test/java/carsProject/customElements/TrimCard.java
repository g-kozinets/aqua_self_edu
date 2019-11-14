package carsProject.customElements;

import framework.pageElements.Text;
import org.openqa.selenium.By;

public class TrimCard {

    private Text engine = new Text(By.xpath("//div[@class='trim-details']/div[@class='trim-card']/div[4]"), "Engine info");
    private Text transmission = new Text(By.xpath("//div[@class='trim-details']/div[@class='trim-card']/div[5]"), "Transmission info");


    public String getEngine() {
        return engine.getText();
    }

    public String getTransmission() {
        return transmission.getText();
    }
}
