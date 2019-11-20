package project.forms;

import framework.pageElements.Text;
import org.openqa.selenium.By;
import project.models.Game;

public class GameForm extends BaseForm {
    private String gamePricesTag = "//div[@class='game_area_purchase_game_wrapper']";
    private Text gameName = new Text(By.className("apphub_AppName"), "game name");
    private Text discount = new Text(By.xpath(gamePricesTag + "//div[@class='discount_pct']"), "game name");
    private Text originalPrice = new Text(By.xpath(gamePricesTag + "//div[@class='discount_original_price']"), "game name");
    private Text finalPrice = new Text(By.xpath(gamePricesTag + "//div[@class='discount_final_price']"), "game name");

    public GameForm() {
        uniqueElement = gameName;
    }

    public Game getGameOnForm() {
        return new Game(
                gameName.getText(),
                originalPrice.getText(),
                finalPrice.getText(),
                discount.getText()
        );
    }
}
