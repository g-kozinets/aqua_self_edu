package framework.api;

import framework.api.multipart.MultipartUtility;
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
    private static String editPostMethod = "wall.edit";
    private static String addLikeMethod = "likes.add";
    private static String getLikeMethod = "likes.getList";
    private static String saveWallPhoto = "saveWallPhoto";
    private static String getWallUploadServer = "photos.getWallUploadServer";
    private static String token = "12a183dc275aa84e49f079e6a22381d2007660ed27a3b716151198fd1420d6123d2f7d9b5e924d89c726e";
    private static String apiVer = "5.103";
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

    public static void addLikeToPost(int postId) throws IOException {
        setNewParameters(addLikeMethod, "type=post&item_id=49030", Integer.toString(postId));
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
        setNewParameters(editPostMethod, "attachment=%s&post_id=%s", attachment, Integer.toString(postId));
        sendRequest();

        return ResponseReader.getResponse().getInt("post_id");
    }

    public static String uploadPhotoToWall(String filePath) throws Exception {
        setNewParameters(getWallUploadServer, "");
        sendRequest();
        String serverUrl = ResponseReader.getResponse().getString("upload_url");

        ResponseReader.setJsonResponse(MultipartUtility.sendFile(serverUrl, ".TestPhoto/GitHub-Mark.png"));
        String serverId = ResponseReader.getField("server").toString();
        String photo = ResponseReader.getField("photo").toString();
        String hash = ResponseReader.getField("hash").toString();

        setNewParameters(saveWallPhoto, "photo=%s&server=%s&hash=%s", photo, serverId, hash);
        sendRequest();
        String photoId = ResponseReader.getResponse().getString("id");
        int ownerId = ResponseReader.getResponse().getInt("owner_id");

        return "photo" + ownerId + "_" + photoId;
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
        String apiUrl = apiUrl + apiMethod;
        //добавляется метод, поэтому неверный запрос для получения аплод сервера
        setCon(apiUrl, HttpMethod.POST);
    }

    private static void responseCodeHandler() throws IOException {
        int code = con.getResponseCode();
        if (code > 299) {
            Assert.fail("Bad response code: " + code);
        }
    }

}
