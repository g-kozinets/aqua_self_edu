package project.forms;

import aquality.selenium.elements.interfaces.IButton;
import org.openqa.selenium.By;

public class MyProfileForm extends BaseForm {
    private IButton profileBtn = getElementFactory().getButton(By.id("top_profile_link"), "profile button");

    public MyProfileForm() {
        super(By.id("profile_short"), "Profile");
    }

    public int getUserId() {
        String href = profileBtn.getAttribute("href");
        return Integer.parseInt(href.split("id")[1]);
    }

}
