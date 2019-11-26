package framework.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;

public class ResponseReader {

    public static String read(URLConnection con, String fieldName) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return String.valueOf(content).split("(?:\"" + fieldName + "\":)(.*?)(?:[,}])", 1)[0];
    }
}
