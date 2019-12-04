package project.api;

import framework.resources.PropertiesResourceManager;
import project.api.data.ParametersMap;
import project.api.multipart.MultipartUtility;
import org.json.JSONArray;
import project.enums.HttpMethod;
import project.models.Post;
import static project.enums.ApiMethod.*;
import static project.enums.UrlParam.*;

public class VkApi {
    private String token;
    private String apiVer;
    private int userId;
    private ParametersMap params = new ParametersMap();
    private ApiConnection api;

    public VkApi() {
        PropertiesResourceManager properties = new PropertiesResourceManager("api.properties");
        ParametersMap baseParams = new ParametersMap();
        api = new ApiConnection(properties.getProperty("apiUrl"));
        token = properties.getProperty("token");
        apiVer = properties.getProperty("apiVer");
        api.setBaseParams(baseParams.newPut(API_VER, apiVer).put(TOKEN, token));
    }

    public int getUserId() {
        return userId;
    }

    public Post sendWallPost(Post post) {
        params.newPut(MESSAGE, post.getMessage());
        api.sendNewParameters(HttpMethod.POST, POST, params);
        post.setPostId(ResponseReader.getResponse().getInt("post_id"));
        return post;
    }

    public void deleteWallPost(Post post) {
        params.newPut(POST_ID, post.getPostId());
        api.sendNewParameters(HttpMethod.POST, DELETE_POST, params);
    }

    public int sendCommentToPost(Post post) {
        params.newPut(POST_ID, post.getPostId()).put(MESSAGE, post.getComment());
        api.sendNewParameters(HttpMethod.POST, COMMENT, params);
        return ResponseReader.getResponse().getInt("comment_id");
    }

    public void addLikeToPost(int postId) {
        params.newPut(TYPE, "post").put(ITEM_ID, postId);
        api.sendNewParameters(HttpMethod.POST, LIKE, params);
    }

    public Post getPostLikes(Post post) {
        params.newPut(TYPE, "post").put(ITEM_ID, post.getPostId()).put(FILTER, "likes");
        api.sendNewParameters(HttpMethod.GET, GET_LIKES, params);
        JSONArray jsonArray = ResponseReader.getResponse().getJSONArray("items");
        post.setLikesId(ResponseReader.convertJsonArrToArray(jsonArray));
        return post;
    }

    public Post editPostPhoto(Post post) {
        post.setImageId(uploadPhotoToWall(post.getImagePath()));
        String postText = getPostText(post.getPostId());

        params.newPut(ATTACHMENT, "photo" + post.getImageId()).put(POST_ID, post.getPostId()).put(MESSAGE, postText);
        api.sendNewParameters(HttpMethod.POST, EDIT_POST, params);

        post.setPostId(ResponseReader.getResponse().getInt("post_id"));
        return post;
    }

    public Post editPostText(Post post) {
        params.newPut(POST_ID, post.getPostId()).put(MESSAGE, post.getMessage());
        api.sendNewParameters(HttpMethod.POST, EDIT_POST, params);

        post.setPostId(ResponseReader.getResponse().getInt("post_id"));
        return post;
    }

    public String uploadPhotoToWall(String filePath) {
        api.sendNewParameters(HttpMethod.POST, GEL_WALL_SERVER);
        String serverUrl = ResponseReader.getResponse().getString("upload_url");

        ResponseReader.setJsonResponse(MultipartUtility.sendFile(serverUrl, filePath));
        String serverId = ResponseReader.getField("server").toString();
        String photo = ResponseReader.getField("photo").toString();
        String hash = ResponseReader.getField("hash").toString();

        params.newPut(SERVER, serverId).put(PHOTO, photo).put(HASH, hash);
        api.sendNewParameters(HttpMethod.POST, SAVE_WALL_PHOTO, params);
        int photoId = ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getInt("id");
        userId = ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getInt("owner_id");

        return userId + "_" + photoId;
    }

    public String getPostText(int postId) {
        params.newPut(POSTS, userId + "_" + postId).put(EXTENSION, "0");
        api.sendNewParameters(HttpMethod.GET, GET_POST, params);
        return ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getString("text");
    }

    public String getPostImg(int userId, int postId) {
        params.newPut(POSTS, userId + "_" + postId).put(EXTENSION, "0");
        api.sendNewParameters(HttpMethod.GET, GET_POST, params);
        String imgUrl = ResponseReader.getJson()
                .getJSONArray("response")
                .getJSONObject(0)
                .getJSONArray("attachments")
                .getJSONObject(0)
                .getJSONObject("photo")
                .getJSONArray("sizes")
                .getJSONObject(6)
                .getString("url");
        return imgUrl;
    }


}
