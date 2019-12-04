package project.api.multipart;

import framework.utils.ExceptionHandler;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.*;

public class MultipartUtility {
    public static String sendFile(String uploadUrl, String imagePath) {
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = builder.build();

        HttpPost httpPost = new HttpPost(uploadUrl);

        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.addPart("file", new FileBody(new File(imagePath)));
        final HttpEntity entity = entityBuilder.build();
        httpPost.setEntity(entity);
        HttpResponse response = null;

        try {
            response = httpClient.execute(httpPost);
        } catch (IOException e) {
            ExceptionHandler.throwException("Could not send file", e);
        }

        InputStreamReader reader = null;

        try {
            reader = new InputStreamReader(response.getEntity().getContent());
        } catch (IOException e) {
            ExceptionHandler.throwException("Could not read response", e);
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
            ExceptionHandler.throwException("Could not read second response", e);
        }

        String output = response2.toString();
        return output;
    }
}