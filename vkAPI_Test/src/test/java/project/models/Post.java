package project.models;

import java.util.ArrayList;

public class Post {
    private String text;
    private String comment;
    private int postId;
    private int imageId;
    private int userId;
    private ArrayList likesId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList getLikesId() {
        return likesId;
    }

    public void setLikesId(ArrayList likesId) {
        this.likesId = likesId;
    }
}
