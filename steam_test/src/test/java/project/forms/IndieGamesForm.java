package project.forms;

import framework.pageElements.Button;
import framework.pageElements.Text;
import org.openqa.selenium.By;
import project.enums.SortBy;
import project.models.Game;

import java.util.ArrayList;

public class IndieGamesForm extends MainForm {
    private Game game;
    private ArrayList<Game> games = new ArrayList<>();
    private String tabId = "tab_select_TopSellers";
    private String listId = "TopSellersRows";
    private String gameItemInfoTag = String.format("//div[@id='%s']//div[@class='%s']", listId, "%s");
    //private String tabListTag = "//div[@id='%s']// div[contains(@class, '%s')]";
    private Text genreHeader = new Text(By.xpath("// *[@class='pageheader' and contains(text(), 'Indie')]"), "Genre header");
    private Text gameName = new Text(By.xpath(String.format(gameItemInfoTag, "tab_item_name")), "game name");
    private Text discount = new Text(By.xpath(String.format(gameItemInfoTag, "discount_pct")), "discount");
    private Text finalPrice = new Text(By.xpath(String.format(gameItemInfoTag, "discount_final_price")), "final price");
    private Text originalPrice = new Text(By.xpath(String.format(gameItemInfoTag, "discount_original_price")), "original price");
    private Button tabBtn = new Button(By.id(tabId), "Tab button");



    public IndieGamesForm() {
        uniqueElement = genreHeader;
    }

    public void chooseTab (TableTab tab) {

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

    public Game getDiscountedGameBy(SortBy sortBy) {
        getDiscountedGames().sort(game.byDiscount);

        switch (sortBy) {
            case MIN:
                return game = games.get(0);
            case MAX:
                return game = games.get(games.size()-1);
            default:
                throw new IllegalStateException("Unexpected value: " + sortBy);
        }
    }

}
