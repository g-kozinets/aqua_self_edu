package project.api.data.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PostInfo {
    private String imageUrl;
    private String text;
    private ArrayList user_likes;

    @SuppressWarnings("unchecked")
    @JsonProperty("response")
    private void unpackNested(Map<String,Object> response) {
        this.text = (String) response.get("text");
        Map<String,String> attachments = (Map<String,String>)response.get("attachments");
        Map<String,String> owner = (Map<String,String>)brand.get("owner");
        this.PostInfo = owner.get("name");
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("attachments")
    private void unpackNested(Map<String,Object> attachments) {
        Map<String,String> sizes = (Map<String,String>) attachments.get("sizes");
        this.imageUrl = (Map<String,String>)brand.get("owner");
        this.PostInfo = owner.get("name");
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList getUser_likes() {
        return user_likes;
    }

    public void setUser_likes(ArrayList user_likes) {
        this.user_likes = user_likes;
    }
}
