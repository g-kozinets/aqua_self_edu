package project.forms;

import framework.pageElements.Button;
import framework.pageElements.Text;
import framework.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.enums.GameInfo;
import project.enums.SortBy;
import project.enums.TableTab;
import project.models.Game;

import java.util.ArrayList;

import static project.enums.GameInfo.*;

public class IndieGamesForm extends MainForm {
    private ArrayList<Game> games = new ArrayList<>();
    private String listId = "TopSellersRows";
    private String gameItemTag = String.format("//div[@id='%s']//a[contains(@class, 'tab_item')][%s]", listId, "%s");
    private String gameInfoTag = String.format(gameItemTag , "%s");
    ////div[@id='TopSellersRows']//a[contains(@class, 'tab_item')][1]//div[@class='discount_pct']
    //private String gameItemInfoTag = String.format("//div[@id='%s']//div[not(contains(@class, 'no_discount')) and contains(@*, 'tab_item_discount')]//div[@class='%s']", listId, "%s");
    private Text gameInfoTxt;
    private Text genreHeader = new Text(By.xpath("// *[@class='pageheader' and contains(text(), 'Indie')]"), "Genre header");
//    private Text gameName = new Text(By.xpath(String.format(gameItemInfoTag, tagIndex) + "//div[@class='discount_pct']"), "game name");
//    private Text discount = new Text(By.xpath(String.format(gameItemInfoTag, tagIndex)), "discount");
//    private Text finalPrice = new Text(By.xpath(String.format(gameItemInfoTag, tagIndex)), "final price");
//    private Text originalPrice = new Text(By.xpath(String.format(gameItemInfoTag, tagIndex)), "original price");




    public IndieGamesForm() {
        uniqueElement = genreHeader;
    }

    public void selectTab(TableTab tab) {
        new Button(By.id(tab.getId()), "tab button").click();
    }

    private Text getInfoByGameIndex(Object index, GameInfo info) {
        return gameInfoTxt = new Text(By.xpath(String.format(gameInfoTag, index) + info.getTag()), "");
    }

    public void goToGame(Game game) {
        ArrayList<WebElement> gameNames = getInfoByGameIndex("*", GAME_NAME).getAllElements();
        String name = game.getName();

        for (WebElement nameOnForm : gameNames) {
            if (nameOnForm.getText().equals(name)) {
                nameOnForm.click();
                break;
            }
        }
    }

    public ArrayList<Game> getDiscountedGames() {
        Text gameName = getInfoByGameIndex("*" , GAME_NAME);

        for (int i = 1; i <= gameName.getAllElements().size(); i++) {
            if (getInfoByGameIndex(i, DISCOUNT).isDisplayed()) {
                games.add(new Game(
                        getInfoByGameIndex(i, GAME_NAME),
                        getInfoByGameIndex(i, ORIGINAL_PRICE),
                        getInfoByGameIndex(i, FINAL_PRICE),
                        getInfoByGameIndex(i, DISCOUNT)));
            }
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
