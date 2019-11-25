package project.tests;

import org.testng.annotations.Test;
import project.enums.SideMenuId;
import project.forms.MainFeedForm;
import project.steps.LoginSteps;

import static project.enums.SideMenuId.MY_PROFILE;

public class VkTest extends BaseTest{
    private LoginSteps loginSteps = new LoginSteps();
    private MainFeedForm mainFeedForm = new MainFeedForm();

    @Test
    public void loginTest() {
        loginSteps.doLogin();

        mainFeedForm.selectOnSideMenu(MY_PROFILE);
    }
}
