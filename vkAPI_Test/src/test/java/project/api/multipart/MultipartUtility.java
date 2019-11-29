package project.api.multipart;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.*;

public class MultipartUtility {
    public static String sendFile(String uploadUrl, String imagepath) throws Exception {
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = builder.build();

        HttpPost httpPost = new HttpPost(uploadUrl);

        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.addPart("file", new FileBody(new File(imagepath)));
        final HttpEntity entity = entityBuilder.build();
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);
        InputStreamReader reader = new InputStreamReader(response.getEntity().getContent());
        BufferedReader in = new BufferedReader(reader);


        String inputLine;
        StringBuffer response2 = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response2.append(inputLine);
        }
        reader.close();

// print result
        String output = response2.toString();
        System.out.println(output);
        return output;
    }

}