package project.forms;

import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

public class MyProfileForm extends BaseForm {
    private String wallPostTextTag = "//div[contains(@id, 'wpt') and contains(@id, '%s')]/div";
    private ITextBox postTextTxb;

    public MyProfileForm() {
        super(By.id("profile_short"), "Profile");
    }

    public String getPostTextById(int postId) {
        postTextTxb = getElementFactory().getTextBox(By.xpath(String.format(wallPostTextTag, postId)), "post text");
        return postTextTxb.getValue().toString();
    }
}
