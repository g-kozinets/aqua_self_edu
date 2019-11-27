package project.tests;


import framework.api.VkApi;
import framework.api.multipart.MultipartUtility;
import org.testng.annotations.Test;
import project.forms.MainFeedForm;
import project.steps.LoginSteps;

import java.io.File;
import java.io.IOException;


import static project.enums.SideMenuId.MY_PROFILE;

public class VkTest extends BaseTest{
    private LoginSteps loginSteps = new LoginSteps();
    private MainFeedForm mainFeedForm = new MainFeedForm();

    @Test
    public void loginTest() throws IOException {
        loginSteps.doLogin();

        mainFeedForm.selectOnSideMenu(MY_PROFILE);

        VkApi.sendWallPost("agbniul");
    }

    @Test
    public void gson() throws Exception {
        int id = VkApi.sendWallPost("he110qweq");
        VkApi.editPostPhoto(id, "/home/ITRANSITION.CORP/g.kozinets/IdeaProjects/testng-template-project-develop/vkAPI_Test/TestPhoto/GitHub-Mark.png");
    }
}
