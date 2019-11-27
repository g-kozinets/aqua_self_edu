package framework.api;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;

public class ResponseReader {
    private static String jsonResponse;

    public static String read(URLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        jsonResponse = content.toString();
        in.close();

        return jsonResponse;
    }

    public static ArrayList convertJsonArrToArray(JSONArray jsonArray) {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (Object item : jsonArray) {
            arrayList.add(item);
        }
        return arrayList;
    }

    public static Object getJsonField(String fieldName) throws IOException {
        return new JSONObject(jsonResponse).get("fieldName");
    }
}
