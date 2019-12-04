package project.api.multipart;

import aquality.selenium.logger.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.*;

public class MultipartUtility {
    public static String sendFile(String uploadUrl, String imagepath) {
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = builder.build();

        HttpPost httpPost = new HttpPost(uploadUrl);

        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.addPart("file", new FileBody(new File(imagepath)));
        final HttpEntity entity = entityBuilder.build();
        httpPost.setEntity(entity);
        HttpResponse response = null;

        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            getLogger().error("Could not send file: " + e.getMessage());
            throw new RuntimeException();
        }

        InputStreamReader reader = null;

        try {
            reader = new InputStreamReader(response.getEntity().getContent());
        } catch (IOException e) {
            getLogger().error("Could not read response: " + e.getMessage());
            throw new RuntimeException();
        }

        BufferedReader in = new BufferedReader(reader);


        String inputLine;
        StringBuffer response2 = new StringBuffer();

        try {
            while ((inputLine = in.readLine()) != null) {
                response2.append(inputLine);
            }
            reader.close();
        } catch (IOException e) {
            getLogger().error("Could not read second response: " + e.getMessage());
            throw new RuntimeException();
        }

        String output = response2.toString();
        return output;
    }

    public static Logger getLogger() {
        return Logger.getInstance();
    }

}