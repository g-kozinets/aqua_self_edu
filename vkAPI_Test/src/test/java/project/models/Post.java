package project.models;

import java.util.ArrayList;
import java.util.Objects;

public class Post {
    private String message;
    private String comment;
    private int postId;
    private String imageId;
    private int userId;
    private ArrayList likesId;
    private String imagePath;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
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

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return postId == post.postId &&
                userId == post.userId &&
                Objects.equals(message, post.message) &&
                Objects.equals(comment, post.comment) &&
                Objects.equals(imageId, post.imageId) &&
                Objects.equals(likesId, post.likesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, comment, postId, imageId, userId, likesId);
    }
}
