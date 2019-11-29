package project.api;

import framework.resources.PropertiesResourceManager;
import project.api.data.ParametersMap;
import project.api.multipart.MultipartUtility;
import org.json.JSONArray;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import static project.enums.ApiMethod.*;
import static project.enums.UrlParam.*;

public class VkApi extends Api{
    private String apiUrl = "https://api.vk.com/method/";

    private String token;
    private String apiVer;
    private int userId;
    private HttpURLConnection con;
    private ParametersMap params;

    public VkApi() {
        PropertiesResourceManager properties = new PropertiesResourceManager("api");
        apiUrl = properties.getProperty("apiUrl");
        token = properties.getProperty("token");
        apiVer = properties.getProperty("apiVer");
        params.newPut(API_VER, )
        setBaseParams();
    }

    public  int getUserId() {
        return userId;
    }

    public  int sendWallPost(String message) throws IOException {
        params.newPut(MESSAGE, message);
        setNewParameters(POST, params);
        sendRequest();
        return ResponseReader.getResponse().getInt("post_id");
    }

    public  void deleteWallPost(int postId) throws IOException {
        params.newPut(POST_ID, postId);
        setNewParameters(DELETE_POST, params);
        sendRequest();
    }

    public  int sendCommentToPost(int postId, String text) throws IOException {
        params.newPut(POST_ID, postId).put(MESSAGE, text);
        setNewParameters(COMMENT, params);
        sendRequest();
        return ResponseReader.getResponse().getInt("comment_id");
    }

    public  void addLikeToPost(int postId) throws IOException {
        params.newPut(TYPE, "post").put(ITEM_ID, postId);
        setNewParameters(LIKE, params);
        sendRequest();
    }

    public  ArrayList getPostLikes(int postId) throws IOException {
        params.newPut(TYPE, "post").put(ITEM_ID, postId).put(FILTER, "likes");
        setNewParameters(GET_LIKES, params);
        sendRequest();
        JSONArray jsonArray = ResponseReader.getResponse().getJSONArray("items");
        return ResponseReader.convertJsonArrToArray(jsonArray);
    }

    public  int editPostPhoto(int postId, String filePath) throws Exception {
        String attachment = uploadPhotoToWall(filePath);
        String postText = getPostText(postId);

        params.newPut(ATTACHMENT, attachment).put(POST_ID, postId).put(MESSAGE, postText);
        setNewParameters(EDIT_POST, params);
        sendRequest();

        return ResponseReader.getResponse().getInt("post_id");
    }

    public  int editPostText(int postId, String postText) throws Exception {
        params.newPut(POST_ID, postId).put(MESSAGE, postText);
        setNewParameters(EDIT_POST, params);
        sendRequest();

        return ResponseReader.getResponse().getInt("post_id");
    }

    public  String uploadPhotoToWall(String filePath) throws Exception {
        setNewParameters(GEL_WALL_SERVER);
        sendRequest();
        String serverUrl = ResponseReader.getResponse().getString("upload_url");

        ResponseReader.setJsonResponse(MultipartUtility.sendFile(serverUrl, filePath));
        String serverId = ResponseReader.getField("server").toString();
        String photo = ResponseReader.getField("photo").toString();
        String hash = ResponseReader.getField("hash").toString();

        params.newPut(SERVER, serverId).put(PHOTO, photo).put(HASH, hash);
        setNewParameters(SAVE_WALL_PHOTO, params);
        sendRequest();
        int photoId = ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getInt("id");
        userId = ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getInt("owner_id");

        return "photo" + userId + "_" + photoId;
    }

    public  String getPostText(int postId) throws IOException {
        String fullId = userId + "_" + postId;
        params.newPut(POSTS, userId + "_" + postId).put(EXTENSION, "0");
        setNewParameters(GET_POST, params);
        sendRequest();
        return ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getString("text");
    }

    public  String getPostImg(int userId, int postId) throws IOException {
        params.newPut(POSTS, userId + "_" + postId).put(EXTENSION, "0");
        setNewParameters(GET_POST, params);
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
