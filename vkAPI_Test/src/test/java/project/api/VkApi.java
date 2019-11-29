package project.api;

import project.api.multipart.MultipartUtility;
import org.json.JSONArray;
import project.models.ParametersMap;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import static project.enums.ApiMethod.*;

public class VkApi extends Api{



    private static int userId;
    private static HttpURLConnection con;
    private static String parameters = "";



    public static int getUserId() {
        return userId;
    }

//    public static int sendWallPost(String message) throws IOException {
//        setNewParameters(POST, "message=%s", message);
//        sendRequest();
//        return ResponseReader.getResponse().getInt("post_id");
//    }
//
//    public static void deleteWallPost(int postId) throws IOException {
//        setNewParameters(DELETE_POST, "post_id=%s", Integer.toString(postId));
//        sendRequest();
//    }
//
//    public static int sendCommentToPost(int postId, String text) throws IOException {
//        setNewParameters(COMMENT, "post_id=%s&message=%s", Integer.toString(postId), text);
//        sendRequest();
//        return ResponseReader.getResponse().getInt("comment_id");
//    }
//
//    public static void addLikeToPost(int postId) throws IOException {
//        setNewParameters(LIKE, "type=post&item_id=%s", Integer.toString(postId));
//        sendRequest();
//    }
//
//    public static ArrayList getPostLikes(int postId) throws IOException {
//        setNewParameters(GET_LIKES, "type=post&item_id=%s&filter=likes", Integer.toString(postId));
//        sendRequest();
//        JSONArray jsonArray = ResponseReader.getResponse().getJSONArray("items");
//        return ResponseReader.convertJsonArrToArray(jsonArray);
//    }
//
//    public static int editPostPhoto(int postId, String filePath) throws Exception {
//        String attachment = uploadPhotoToWall(filePath);
//        String postText = getPostText(postId);
//        setNewParameters(EDIT_POST, "attachment=%s&post_id=%s&message=%s", attachment, Integer.toString(postId), postText);
//        sendRequest();
//
//        return ResponseReader.getResponse().getInt("post_id");
//    }
//
//    public static int editPostText(int postId, String postText) throws Exception {
//        setNewParameters(EDIT_POST, "post_id=%s&message=%s", Integer.toString(postId), postText);
//        sendRequest();
//
//        return ResponseReader.getResponse().getInt("post_id");
//    }

    public static String uploadPhotoToWall(String filePath) throws Exception {
        ParametersMap params = new ParametersMap();
        ParametersMap baseparams = new ParametersMap();
        baseparams.put("v", "5.103").put("access_token", "12a183dc275aa84e49f079e6a22381d2007660ed27a3b716151198fd1420d6123d2f7d9b5e924d89c726e");
        Api.setBaseParams(baseparams);

        setNewParameters(GEL_WALL_SERVER);
        sendRequest();
        String serverUrl = ResponseReader.getResponse().getString("upload_url");

        ResponseReader.setJsonResponse(MultipartUtility.sendFile(serverUrl, filePath));
        String serverId = ResponseReader.getField("server").toString();
        String photo = ResponseReader.getField("photo").toString();
        String hash = ResponseReader.getField("hash").toString();

        params.put("photo", photo).put("server", serverId).put("hash", hash);

        setNewParameters(SAVE_WALL_PHOTO, params);
        sendRequest();
        int photoId = ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getInt("id");
        userId = ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getInt("owner_id");

        return "photo" + userId + "_" + photoId;
    }

//    public static String getPostText(int postId) throws IOException {
//        setNewParameters(GET_POST, "posts=" + userId + "_%s&extended=0", Integer.toString(postId));
//        sendRequest();
//        return ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getString("text");
//    }
//
//    public static String getPostImg(int userId, int postId) throws IOException {
//        setNewParameters(GET_POST, "posts=" + userId + "_%s&extended=0", Integer.toString(postId));
//        sendRequest();
//        String imgUrl = ResponseReader.getJson()
//                .getJSONArray("response")
//                .getJSONObject(0)
//                .getJSONArray("attachments")
//                .getJSONObject(0)
//                .getJSONObject("photo")
//                .getJSONArray("sizes")
//                .getJSONObject(6)
//                .getString("url");
//        return imgUrl;
//    }


}
