package framework.api;

import framework.api.multipart.MultipartUtility;
import io.qameta.allure.Step;
import org.json.JSONArray;
import org.testng.Assert;
import project.enums.HttpMethod;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class VkApi {
    private static String apiUrl = "https://api.vk.com/method/";
    private static String postMethod = "wall.post";
    private static String deletePostMethod = "wall.delete";
    private static String editPostMethod = "wall.edit";
    private static String getPostMethod = "wall.getById";
    private static String addLikeMethod = "likes.add";
    private static String getLikeMethod = "likes.getList";
    private static String saveWallPhoto = "photos.saveWallPhoto";
    private static String getWallUploadServer = "photos.getWallUploadServer";
    private static String sendCommentMethod = "wall.createComment";
    private static String token = "12a183dc275aa84e49f079e6a22381d2007660ed27a3b716151198fd1420d6123d2f7d9b5e924d89c726e";
    private static String apiVer = "5.103";
    private static int userId;
    private static HttpURLConnection con;
    private static String parameters = "";

    public static void setCon(String apiUrl, HttpMethod method) throws IOException {
        URL url = new URL(apiUrl);
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(method.getMethod());
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
    }

    public static void sendRequest() throws IOException {
        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(parameters);
        out.flush();
        out.close();

        System.out.println("Parameters: " + parameters);
        System.out.println("========================== " + con.getResponseCode());
        ResponseReader.read(con);
        responseCodeHandler();
    }

    public static int sendWallPost(String message) throws IOException {
        setNewParameters(postMethod, "message=%s", message);
        sendRequest();
        return ResponseReader.getResponse().getInt("post_id");
    }

    public static void deleteWallPost(int postId) throws IOException {
        setNewParameters(deletePostMethod, "post_id=%s", Integer.toString(postId));
        sendRequest();
    }

    public static int sendCommentToPost(int postId, String text) throws IOException {
        setNewParameters(sendCommentMethod, "post_id=%s&message=%s", Integer.toString(postId), text);
        sendRequest();
        return ResponseReader.getResponse().getInt("comment_id");
    }

    public static void addLikeToPost(int postId) throws IOException {
        setNewParameters(addLikeMethod, "type=post&item_id=%s", Integer.toString(postId));
        sendRequest();
    }

    public static ArrayList getPostLikes(int postId) throws IOException {
        setNewParameters(getLikeMethod, "type=post&item_id=%s&filter=likes", Integer.toString(postId));
        sendRequest();
        JSONArray jsonArray = ResponseReader.getResponse().getJSONArray("items");
        return ResponseReader.convertJsonArrToArray(jsonArray);
    }

    public static int editPostPhoto(int postId, String filePath) throws Exception {
        String attachment = uploadPhotoToWall(filePath);
        String postText = getPostText(postId);
        setNewParameters(editPostMethod, "attachment=%s&post_id=%s&message=%s", attachment, Integer.toString(postId), postText);
        sendRequest();

        return ResponseReader.getResponse().getInt("post_id");
    }

    public static String uploadPhotoToWall(String filePath) throws Exception {
        setNewParameters(getWallUploadServer, "");
        sendRequest();
        String serverUrl = ResponseReader.getResponse().getString("upload_url");

        ResponseReader.setJsonResponse(MultipartUtility.sendFile(serverUrl, filePath));
        String serverId = ResponseReader.getField("server").toString();
        String photo = ResponseReader.getField("photo").toString();
        String hash = ResponseReader.getField("hash").toString();

        setNewParameters(saveWallPhoto, "photo=%s&server=%s&hash=%s", photo, serverId, hash);
        sendRequest();
        int photoId = ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getInt("id");
        userId = ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getInt("owner_id");

        return "photo" + userId + "_" + photoId;
    }

    public static String getPostText(int postId) throws IOException {
        setNewParameters(getPostMethod, "posts=" + userId + "_%s&extended=0", Integer.toString(postId));
        sendRequest();
        return ResponseReader.getJson().getJSONArray("response").getJSONObject(0).getString("text");
    }

    public static void addParameter(String parameter, String value) {
        String format = String.format("%s=%s", parameter, value);

        if (parameters.equals("")) {
            parameters = format;

        } else {
            parameters = parameters + "&" + format;
        }
    }

    private static void clearParameters() {
        parameters = "";
    }

    private static void setNewParameters(String apiMethod, String paramFormat, String...values) throws IOException {
        parameters = String.format(paramFormat, values);
        addParameter("v", apiVer);
        addParameter("access_token", token);
        setCon(apiUrl + apiMethod, HttpMethod.POST);
    }

    private static void responseCodeHandler() throws IOException {
        int code = con.getResponseCode();
        if (code > 299) {
            Assert.fail("Bad response code: " + code);
        }
    }
}
