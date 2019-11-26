package framework.api;

import project.enums.HttpMethod;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class VkApi {
    private static String apiUrl = "https://api.vk.com/method/";
    private static String postMethod = "wall.post";
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
    }

    public static void sendWallPost(String message) throws IOException {
        clearParameters();
        parameters = String.format("message=%s&v=%s&access_token=%s", message, apiVer, token);
        apiUrl = apiUrl + postMethod;
        setCon(apiUrl, HttpMethod.GET);
        sendRequest();
        System.out.println(ResponseReader.read(con, "post_id"));
    }
    public static void addParameter(String parameter, String value) {
        String format = String.format("%s=%s", parameter, value);

        if (parameters.equals("")) {
            parameters = format;

        } else {
            parameters = parameters + "&" + format;
        }
    }

    public static void clearParameters() {
        parameters = "";
    }

}
