package project.tests;


import framework.api.VkApi;
import org.testng.Assert;
import org.testng.annotations.Test;
import project.forms.MainFeedForm;
import project.steps.LoginSteps;
import java.io.IOException;
import java.util.ArrayList;


import static project.enums.SideMenuId.MY_PROFILE;

public class VkTest extends BaseTest{
    private LoginSteps loginSteps = new LoginSteps();
    private MainFeedForm mainFeedForm = new MainFeedForm();

    @Test
    public void loginTest() throws Exception {
        loginSteps.doLogin();

        mainFeedForm.selectOnSideMenu(MY_PROFILE);

        int id = VkApi.sendWallPost("he110qweq");
        VkApi.addLikeToPost(id);
        VkApi.editPostPhoto(id, "/home/ITRANSITION.CORP/g.kozinets/IdeaProjects/testng-template-project-develop/vkAPI_Test/TestPhoto/GitHub-Mark.png");
        VkApi.sendCommentToPost(id, "lololol");
        ArrayList arrayList = VkApi.getPostLikes(id);
        Assert.assertTrue(arrayList.contains(381108928));
        VkApi.deleteWallPost(id);

    }

    @Test
    public void gson() throws Exception {
        VkApi.deleteWallPost(49063);

    }

    @Test
    public void sendComment() throws Exception {
        VkApi.sendCommentToPost(49058, "lololol");
        ArrayList arrayList = VkApi.getPostLikes(49058);
        Assert.assertTrue(arrayList.contains(381108928));
    }
}
