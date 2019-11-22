package project.forms.menu;

import framework.pageElements.Button;
import org.openqa.selenium.By;
import project.forms.BaseForm;

public class MenuForm extends BaseForm {
    private Button gamesTabBtn = new Button(By.id("genre_tab"), "Games tab");
    private String listItemTag = "//a[@class='popup_menu_item' and contains(text(), '%s')]";

    public void selectGenre(String genreName) {
        gamesTabBtn.moveCursorHere();
        new Button(By.xpath(String.format(listItemTag, genreName)), "list item").click();
    }

}
