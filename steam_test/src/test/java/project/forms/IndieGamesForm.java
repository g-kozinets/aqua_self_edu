package project.forms;

import framework.pageElements.Button;
import framework.pageElements.Text;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import project.models.GamePrice;

public class IndieGamesForm extends MainForm {
    private GamePrice gamePrice;
    private Text genreHeader = new Text(By.xpath("// *[@class='pageheader' and contains(text(), 'Indie')]"), "Genre header");
    private Text discount = new Text(By.xpath(""), "discount");
    private Text finalPrice = new Text(By.xpath(""), "final price");
    private Text originalPrice = new Text(By.xpath(""), "original price");
    private String tabId = "tab_content_TopSellers";
    private String tabListTag = "//div[@id='%s']// div[contains(@class, '%s')]";
    private Button tabBtn = new Button(By.id(tabId), "Tab button");


    public IndieGamesForm() {
        uniqueElement = genreHeader;
    }


}
