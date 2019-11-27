package framework.api;

import org.json.JSONObject;
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
    private static String addLikeMethod = "likes.add";
    private static String getLikeMethod = "likes.getList";
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
        responseCodeHandler();
    }

    public static int sendWallPost(String message) throws IOException {
        setNewParameters(postMethod, "message=%s", message);
        sendRequest();
        return (int) ResponseReader.getJsonField("post_id");
    }

    public static void addLikeToPost(int postId) throws IOException {
        setNewParameters(addLikeMethod, "type=post&item_id=49030", Integer.toString(postId));
        sendRequest();
    }

    public static ArrayList getPostLikes(int postId) throws IOException {
        setNewParameters(getLikeMethod, "type=post&item_id=%s&filter=likes", Integer.toString(postId));
        sendRequest();
        JSONObject jsonObject = (JSONObject) ResponseReader.getJsonField("response");
        return ResponseReader.convertJsonArrToArray(jsonObject.getJSONArray("items"));
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
        apiUrl = apiUrl + apiMethod;
        setCon(apiUrl, HttpMethod.POST);
    }

    private static void responseCodeHandler() throws IOException {
        int code = con.getResponseCode();
        if (code > 299) {
            Assert.fail("Bad response code: " + code);
        }
    }

}
