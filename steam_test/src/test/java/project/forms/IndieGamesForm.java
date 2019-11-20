package project.forms;

import framework.pageElements.Button;
import framework.pageElements.Text;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.enums.SortBy;
import project.enums.TableTab;
import project.models.Game;

import java.util.ArrayList;

public class IndieGamesForm extends MainForm {
    private ArrayList<Game> games = new ArrayList<>();
    private String listId = "TopSellersRows";
    private String gameItemInfoTag = String.format("//div[@id='%s']//a[contains(@class, 'tab_item')][%s]", listId, "%s");
    //private String gameItemInfoTag = String.format("//div[@id='%s']//div[not(contains(@class, 'no_discount')) and contains(@*, 'tab_item_discount')]//div[@class='%s']", listId, "%s");
    private Text genreHeader = new Text(By.xpath("// *[@class='pageheader' and contains(text(), 'Indie')]"), "Genre header");
    private Text gameName = new Text(By.xpath(String.format(gameItemInfoTag, "tab_item_name")), "game name");
    private Text discount = new Text(By.xpath(String.format(gameItemInfoTag, "discount_pct")), "discount");
    private Text finalPrice = new Text(By.xpath(String.format(gameItemInfoTag, "discount_final_price")), "final price");
    private Text originalPrice = new Text(By.xpath(String.format(gameItemInfoTag, "discount_original_price")), "original price");



    public IndieGamesForm() {
        uniqueElement = genreHeader;
    }

    public void selectTab(TableTab tab) {
        new Button(By.id(tab.getId()), "tab button").click();
    }

    public void goToGame(Game game) {
        ArrayList<WebElement> gameNames = gameName.getAllElements();
        String name = game.getName();

        for (WebElement nameOnForm : gameNames) {
            if (nameOnForm.getText().equals(name)) {
                nameOnForm.click();
                break;
            }
        }
    }

    public ArrayList<Game> getDiscountedGames() {
        for (int i = 0; i < discount.getAllElements().size(); i++) {
            games.add(new Game(
                    gameName.getAllElements().get(i).getText(),
                    originalPrice.getAllElements().get(i).getText(),
                    finalPrice.getAllElements().get(i).getText(),
                    discount.getAllElements().get(i).getText()));
        }
        return games;
    }

    public Game getDiscountedGame(SortBy sortBy) {
        getDiscountedGames().sort(Game.byDiscount);

        switch (sortBy) {
            case MIN:
                return games.get(0);
            case MAX:
                return games.get(games.size()-1);
            default:
                throw new IllegalStateException("Unexpected value: " + sortBy);
        }
    }

}
