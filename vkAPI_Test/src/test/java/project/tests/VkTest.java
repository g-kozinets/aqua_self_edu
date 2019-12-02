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
    public void loginTest() throws Exception {
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

        post.setPostId(vkApi.sendWallPost(post));
        postForm = new PostForm(post);
        Assert.assertEquals(postForm.getPostMessage(), post.getMessage(), "Post text doesn't match");

        post.setMessage(TextGenerator.generate(7));
        vkApi.editPostText(post);
        vkApi.editPostPhoto(post);

        Assert.assertEquals(postForm.getPostMessage(), post.getMessage(), "Edited post text doesn't match");

        vkApi.sendCommentToPost(post);
        postForm.loadMoreComments();
        Assert.assertEquals(postForm.getPostComment(), post.getComment(), "Comments doesn't match");

        postForm.likePost();
        post.setLikesId(vkApi.getPostLikes(post));
        Assert.assertTrue(post.getLikesId().contains(post.getUserId()));

        vkApi.deleteWallPost(post);
        Assert.assertFalse(postForm.isFormDisplayed(), "Post was not deleted");
    }

}
