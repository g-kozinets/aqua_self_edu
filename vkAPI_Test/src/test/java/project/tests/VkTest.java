package project.tests;

import framework.api.VkApi;
import org.testng.annotations.Test;
import project.enums.HttpMethod;
import project.forms.MainFeedForm;
import project.steps.LoginSteps;

import java.io.IOException;

import static project.enums.SideMenuId.MY_PROFILE;

public class VkTest extends BaseTest{
    private LoginSteps loginSteps = new LoginSteps();
    private MainFeedForm mainFeedForm = new MainFeedForm();

    @Test
    public void loginTest() throws IOException {
        loginSteps.doLogin();

        mainFeedForm.selectOnSideMenu(MY_PROFILE);

        VkApi.sendWallPost("hjimi");
    }
}
