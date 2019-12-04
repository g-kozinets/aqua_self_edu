package project.tests;

import framework.resources.PropertiesResourceManager;
import project.api.VkApi;
import framework.utils.TextGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import project.forms.MainFeedForm;
import project.forms.MyProfileForm;
import project.forms.PostForm;
import project.models.Post;
import project.steps.LoginSteps;
import static project.enums.SideMenuId.MY_PROFILE;

public class VkTest extends BaseTest{
    PropertiesResourceManager prop = new PropertiesResourceManager("post.properties");
    private LoginSteps loginSteps = new LoginSteps();
    private MainFeedForm mainFeedForm = new MainFeedForm();
    private MyProfileForm myProfileForm;
    private PostForm postForm;

    @Test
    public void loginTest() {
        int textLength = 7;
        VkApi vkApi = new VkApi();
        Post post = new Post();
        post.setMessage(TextGenerator.generate(prop.getProperty("message_length")));
        post.setComment(TextGenerator.generate(prop.getProperty("comment_length")));
        post.setImagePath(prop.getProperty("image_path"));

        loginSteps.doLogin();

        mainFeedForm.selectOnSideMenu(MY_PROFILE);
        myProfileForm = new MyProfileForm();
        Assert.assertTrue(myProfileForm.isFormDisplayed(), "Not on profile page");
        post.setUserId(myProfileForm.getUserId());

        post = (vkApi.sendWallPost(post));
        postForm = new PostForm(post);
        Assert.assertEquals(postForm.getMessage(), post.getMessage(), "Post text doesn't match");

        post.setMessage(TextGenerator.generate(textLength));
        post = vkApi.editPostText(post);
        postForm.waitTillEdited();
        Assert.assertEquals(post.getMessage(), postForm.getMessage(), "Edited post text doesn't match");

        post = vkApi.editPostPhoto(post);
        Assert.assertEquals(post.getImageId(), postForm.getImgId(), "Edited post photo doesn't match");

        Assert.assertEquals(postForm.getMessage(), post.getMessage());

        vkApi.sendCommentToPost(post);
        postForm.loadMoreComments();
        Assert.assertEquals(postForm.getComment(), post.getComment(), "Comments doesn't match");

        postForm.likePost();
        post = vkApi.getPostLikes(post);
        Assert.assertTrue(post.getLikesId().contains(post.getUserId()));

        vkApi.deleteWallPost(post);
        postForm.waitTillDeleted();
        Assert.assertFalse(postForm.isFormDisplayed(), "Post was not deleted");
    }

}
