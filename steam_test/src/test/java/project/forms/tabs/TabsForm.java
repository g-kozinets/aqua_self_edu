package project.forms.tabs;

import framework.pageElements.Button;
import framework.pageElements.Text;
import org.openqa.selenium.By;
import project.enums.TableTab;
import project.forms.BaseForm;

public class TabsForm extends BaseForm {
    private Button tabBtn;
    private By selectedTabLocator = By.xpath("//div[@class='tab  tab_filler active']");

    public void selectTab(TableTab tab) {
        tabBtn = new Button(By.id(tab.getId()), "tab button");
        tabBtn.click();
    }

    public String getSelectedTab() {
        String tab = new Text(selectedTabLocator, "Selected tab").getText();
        return tab.replaceAll("[\\s][^A-z]+", "");
    }

}
