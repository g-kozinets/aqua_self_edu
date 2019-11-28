package project.tests;


import framework.api.VkApi;
import framework.utils.TextGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import project.forms.LoginForm;
import project.forms.MainFeedForm;
import project.forms.MyProfileForm;
import project.steps.LoginSteps;
import java.io.IOException;
import java.util.ArrayList;


import static project.enums.SideMenuId.MY_PROFILE;

public class VkTest extends BaseTest{
    private LoginSteps loginSteps = new LoginSteps();
    private MainFeedForm mainFeedForm = new MainFeedForm();
    private MyProfileForm myProfileForm;

    @Test
    public void loginTest() throws Exception {
        String postText = TextGenerator.generate(7);
        String postComment = TextGenerator.generate(7);
        int postId;

        Assert.assertTrue(new LoginForm().isFormDisplayed(), "Not on login page");
        loginSteps.doLogin();

        mainFeedForm.selectOnSideMenu(MY_PROFILE);
        myProfileForm = new MyProfileForm();
        Assert.assertTrue(myProfileForm.isFormDisplayed(), "Not on profile page");

        postId = VkApi.sendWallPost(postText);
        Assert.assertEquals(myProfileForm.getPostTextById(postId), postText, "Post text doesn't match");

        postText = TextGenerator.generate(7);
        VkApi.editPostPhoto(postId, "/home/ITRANSITION.CORP/g.kozinets/IdeaProjects/testng-template-project-develop/vkAPI_Test/TestPhoto/GitHub-Mark.png");
        VkApi.editPostText(postId, postText);
        Assert.assertEquals(myProfileForm.getPostTextById(postId), postText, "Edited post text doesn't match");

        VkApi.addLikeToPost(postId);
        VkApi.editPostPhoto(postId, "/home/ITRANSITION.CORP/g.kozinets/IdeaProjects/testng-template-project-develop/vkAPI_Test/TestPhoto/GitHub-Mark.png");
        VkApi.sendCommentToPost(postId, postComment);
        ArrayList arrayList = VkApi.getPostLikes(postId);
        Assert.assertTrue(arrayList.contains(381108928));
        VkApi.deleteWallPost(postId);
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
