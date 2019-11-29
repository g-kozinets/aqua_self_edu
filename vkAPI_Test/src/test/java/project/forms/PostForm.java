package project.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

public class PostForm extends BaseForm {
    private String postTag = locator.toString().replaceAll("By.xpath:", "");
    private ITextBox wallPostTxb = getElementFactory().getTextBox(By.xpath(postTag + "//div[contains(@class, 'wall_post_text')]"), "post text");
    private IButton likeBtn = getElementFactory().getButton(By.xpath(postTag + "//a[contains(@class, ' like')]"), "like");
    private IButton moreCommentBtn = getElementFactory().getButton(By.xpath(postTag + "//a[contains(@class, 'replies_next')]"), "more comments");
    private ITextBox postCommentTxb = getElementFactory().getTextBox(By.xpath(postTag + "//div[@class='wall_reply_text']"), "comment text");

    public PostForm(int userId, int postId) {
        super(By.xpath(String.format("//div[@id='post%s_%s']", userId, postId)), "post form");
    }


    public String getPostText() {
        return wallPostTxb.getText();
    }

    public String getPostComment() {
        return postCommentTxb.getText();
    }

    public void likePost() {
        likeBtn.click();
    }

    public void loadMoreComments() {
        moreCommentBtn.click();
    }
}
