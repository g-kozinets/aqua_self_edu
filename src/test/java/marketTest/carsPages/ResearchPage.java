package marketTest.carsPages;

import framework.pageElements.Button;
import framework.utils.TestUtils;
import marketTest.customElements.CarChooser;
import marketTest.models.CarSpecs;
import org.openqa.selenium.By;

public class ResearchPage extends BaseForm{

    Button compareBtn = new Button(By.xpath("//a[@data-linkname='compare-cars']"), "Compare cars button");
    CarChooser carChooser = new CarChooser();

    public CarSpecs getRandomCar(){
        return carChooser.getRandomCar();
    }

    public void doSearch(){
        carChooser.doSearch();
    }

    public void goToCompare() {
        compareBtn.waitAndClick();
    }

}
