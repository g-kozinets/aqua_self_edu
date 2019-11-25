package project.forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MyProfileForm extends BaseForm {
    public MyProfileForm() {
        super(By.id("profile_short"), "Profile");
    }

}
