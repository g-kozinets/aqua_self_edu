package project.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import framework.utils.Waiters;
import org.openqa.selenium.By;
import project.models.Post;

public class PostForm extends BaseForm {
    private String postTag = locator.toString().replaceAll("By.xpath:", "");
    private String updatedColorTag = "//div[contains(@class, 'wall_post_cont')][contains(@style, 'background-color')]";
    private By activeLikeLocator = By.xpath("//a[contains(@class, 'active')]");
    private ITextBox wallPostTxb = getElementFactory().getTextBox(By.xpath(postTag + "//div[contains(@class, 'wall_post_text')]"), "post");
    private IButton likeBtn = getElementFactory().getButton(By.xpath(postTag + "//a[contains(@class, ' like')]"), "like");
    private IButton moreCommentBtn = getElementFactory().getButton(By.xpath(postTag + "//a[contains(@class, 'replies_next')]"), "more comments");
    private ITextBox postCommentTxb = getElementFactory().getTextBox(By.xpath(postTag + "//div[@class='wall_reply_text']"), "comment");
    private IButton imageBtn = getElementFactory().getButton(By.xpath(postTag + "//a[contains(@class, 'image_cover')]"), "Image");

    public PostForm(int userId, int postId) {
        super(By.xpath(String.format("//div[@id='post%s_%s']", userId, postId)), "post form");
    }

    public PostForm(Post post) {
        super(By.xpath(String.format("//div[@id='post%s_%s']", post.getUserId(), post.getPostId())), "post");
    }


    public String getMessage() {
        return wallPostTxb.getText();
    }

    public String getComment() {
        return postCommentTxb.getText();
    }

    public String getImgId() {
        String href = imageBtn.getAttribute("href");
        return href.replaceAll("[^0-9&_]+", "");
    }

    public void likePost() {
        likeBtn.click();
        Waiters.waitForVisibility(activeLikeLocator, "Like is not active");
    }

    public void loadMoreComments() {
        moreCommentBtn.click();
    }

    public void waitTillEdited() throws InterruptedException {
        Waiters.waitForVisibility(By.xpath(postTag + updatedColorTag), "Post was not edited");
    }

    public void waitTillDeleted() throws InterruptedException {
        Waiters.waitForInvisibility(By.xpath(postTag), "Post was not deleted");
    }

    public Post updateModelData(Post post) {
        post.setMessage(getMessage());
        try {
            post.setComment(getComment());
        }catch (Exception e) {

        }

        try {
            post.setImageId(getImgId());
        }catch (Exception e) {

        }

        return  post;
    }
}
