package project.tests;


import framework.api.VkApi;
import framework.utils.TextGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import project.forms.MainFeedForm;
import project.forms.MyProfileForm;
import project.forms.PostForm;
import project.steps.LoginSteps;

import java.util.ArrayList;


import static project.enums.SideMenuId.MY_PROFILE;

public class VkTest extends BaseTest{
    private LoginSteps loginSteps = new LoginSteps();
    private MainFeedForm mainFeedForm = new MainFeedForm();
    private MyProfileForm myProfileForm;
    private PostForm postForm;

    @Test
    public void loginTest() throws Exception {
        String postText = TextGenerator.generate(7);
        String postComment = TextGenerator.generate(7);
        int userId;
        int postId;

        loginSteps.doLogin();

        mainFeedForm.selectOnSideMenu(MY_PROFILE);
        myProfileForm = new MyProfileForm();
        Assert.assertTrue(myProfileForm.isFormDisplayed(), "Not on profile page");
        userId = myProfileForm.getUserId();

        postId = VkApi.sendWallPost(postText);
        postForm = new PostForm(userId, postId);
        Assert.assertEquals(postForm.getPostText(), postText, "Post text doesn't match");

        postText = TextGenerator.generate(7);
        VkApi.editPostText(postId, postText);
        VkApi.editPostPhoto(postId, "/home/ITRANSITION.CORP/g.kozinets/IdeaProjects/testng-template-project-develop/vkAPI_Test/TestPhoto/GitHub-Mark.png");
        Assert.assertEquals(postForm.getPostText(), postText, "Edited post text doesn't match");

        VkApi.sendCommentToPost(postId, postComment);
        postForm.loadMoreComments();
        Assert.assertEquals(postForm.getPostComment(), postComment, "Comments doesn't match");

        postForm.likePost();
        ArrayList arrayList = VkApi.getPostLikes(postId);
        Assert.assertTrue(arrayList.contains(userId));

        VkApi.deleteWallPost(postId);
        Assert.assertFalse(postForm.isFormDisplayed(5L), "Post was not deleted");
    }
}
