package project.forms;

import framework.pageElements.Text;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import project.enums.GameInfo;
import project.enums.SortBy;
import project.models.Game;
import java.util.ArrayList;
import static project.enums.GameInfo.*;

public class IndieGamesForm extends MainForm {
    private ArrayList<Game> games = new ArrayList<>();
    private String listId = "TopSellersRows";
    private String gameItemTag = "//div[@id='TopSellersRows']//a[contains(@class, 'tab_item')][%s]";
    private String ItemInfoTag = String.format(gameItemTag , "%s");
    private Text pageHeaderTxt = new Text(By.xpath("//*[@class='pageheader']"), "Page header");
    public TabsForm tabsForm = new TabsForm();

    public IndieGamesForm() {
        uniqueElement = pageHeaderTxt;
    }

    private Text getInfoByGameIndex(Object index, GameInfo info) {
        return new Text(By.xpath(String.format(ItemInfoTag, index) + info.getTag()), "");
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
