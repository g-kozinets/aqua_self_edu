package project.api;

import aquality.selenium.logger.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;

public class ResponseReader {
    public static void setJsonResponse(String jsonResponse) {
        ResponseReader.jsonResponse = jsonResponse;
    }

    private static String jsonResponse;

    public static String read(URLConnection con) {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            jsonResponse = content.toString();
        } catch (IOException e) {
            Logger.getInstance().error("Could not read JSON response: " + e.getMessage());
            throw new RuntimeException();
        }
        return jsonResponse;
    }

    public static ArrayList convertJsonArrToArray(JSONArray jsonArray) {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (Object item : jsonArray) {
            arrayList.add(item);
        }
        return arrayList;
    }

    public static JSONObject getResponse() {
        return new JSONObject(jsonResponse).getJSONObject("response");
    }

    public static JSONObject getJson() {
        return new JSONObject(jsonResponse);
    }

    public static Object getField(String name) {
        return new JSONObject(jsonResponse).get(name);
    }
}

