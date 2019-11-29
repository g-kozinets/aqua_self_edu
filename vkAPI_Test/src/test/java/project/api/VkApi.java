package project.api;

import framework.resources.PropertiesResourceManager;
import project.api.data.ParametersMap;
import project.api.multipart.MultipartUtility;
import org.json.JSONArray;
import java.io.IOException;
import java.util.ArrayList;
import static project.enums.ApiMethod.*;
import static project.enums.UrlParam.*;

public class VkApi extends Api{
    private String token;
    private String apiVer;
    private int userId;
    private ParametersMap params = new ParametersMap();

    public VkApi() {
        PropertiesResourceManager properties = new PropertiesResourceManager("api.properties");
        ParametersMap baseParams = new ParametersMap();
        apiUrl = properties.getProperty("apiUrl");
        token = properties.getProperty("token");
        apiVer = properties.getProperty("apiVer");
        setBaseParams(baseParams.newPut(API_VER, apiVer).put(TOKEN, token));
    }

    public  int getUserId() {
        return userId;
    }

    public  int sendWallPost(String message) throws IOException {
        params.newPut(MESSAGE, message);
        sendNewParameters(POST, params);
        sendRequest();
        return ResponseReader.getResponse().getInt("post_id");
    }

    public  void deleteWallPost(int postId) throws IOException {
        params.newPut(POST_ID, postId);
        sendNewParameters(DELETE_POST, params);
        sendRequest();
    }

    public  int sendCommentToPost(int postId, String text) throws IOException {
        params.newPut(POST_ID, postId).put(MESSAGE, text);
        sendNewParameters(COMMENT, params);
        sendRequest();
        return ResponseReader.getResponse().getInt("comment_id");
    }

    public  void addLikeToPost(int postId) throws IOException {
        params.newPut(TYPE, "post").put(ITEM_ID, postId);
        sendNewParameters(LIKE, params);
        sendRequest();
    }

    public  ArrayList getPostLikes(int postId) throws IOException {
        params.newPut(TYPE, "post").put(ITEM_ID, postId).put(FILTER, "likes");
        sendNewParameters(GET_LIKES, params);
        sendRequest();
        JSONArray jsonArray = ResponseReader.getResponse().getJSONArray("items");
        return ResponseReader.convertJsonArrToArray(jsonArray);
    }

    public  int editPostPhoto(int postId, String filePath) throws Exception {
        String attachment = uploadPhotoToWall(filePath);
        String postText = getPostText(postId);

        params.newPut(ATTACHMENT, attachment).put(POST_ID, postId).put(MESSAGE, postText);
        sendNewParameters(EDIT_POST, params);
        sendRequest();

        return ResponseReader.getResponse().getInt("post_id");
    }

    public  int editPostText(int postId, String postText) throws Exception {
        params.newPut(POST_ID, postId).put(MESSAGE, postText);
        sendNewParameters(EDIT_POST, params);
        sendRequest();

        return ResponseReader.getResponse().getInt("post_id");
    }

    public  String uploadPhotoToWall(String filePath) throws Exception {
        sendNewParameters(GEL_WALL_SERVER);
        sendRequest();
        String serverUrl = ResponseReader.getResponse().getString("upload_url");

        ResponseReader.setJsonResponse(MultipartUtility.sendFile(serverUrl, filePath));
        String serverId = ResponseReader.getField("server").toString();
        String photo = ResponseReader.getField("photo").toString();
        String hash = ResponseReader.getField("hash").toString();

        params.newPut(SERVER, serverId).put(PHOTO, photo).put(HASH, hash);
        sendNewParameters(SAVE_WALL_PHOTO, params);
        sendRequest();
        int photoId = ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getInt("id");
        userId = ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getInt("owner_id");

        return "photo" + userId + "_" + photoId;
    }

    public  String getPostText(int postId) throws IOException {
        params.newPut(POSTS, userId + "_" + postId).put(EXTENSION, "0");
        sendNewParameters(GET_POST, params);
        sendRequest();
        return ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getString("text");
    }

    public  String getPostImg(int userId, int postId) throws IOException {
        params.newPut(POSTS, userId + "_" + postId).put(EXTENSION, "0");
        sendNewParameters(GET_POST, params);
        sendRequest();
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
