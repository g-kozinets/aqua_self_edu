package project.api;

import org.testng.Assert;
import project.api.data.ParametersMap;
import project.enums.ApiMethod;
import project.enums.HttpMethod;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Api {
    private static ParametersMap baseParams = new ParametersMap();
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

    public static void addParameter(String parameter, Object value) {
        String format = String.format("%s=%s", parameter, value);
        if (parameters.equals("")) {
            parameters = format;
        } else {
            parameters = parameters + "&" + format;
        }
    }

    private static void readParamsMap(ParametersMap params) {
        for (String key : params.keySet()) {
            addParameter(key, params.get(key));
        }
    }

    private static void clearParameters() {
        parameters = "";
    }

    protected static void setNewParameters(ApiMethod apiMethod, ParametersMap params) throws IOException {
        readParamsMap(params);
        readParamsMap(baseParams);
        setCon(apiUrl + apiMethod.getMethod(), HttpMethod.POST);
    }

    protected static void setNewParameters(ApiMethod apiMethod) throws IOException {
        readParamsMap(baseParams);
        setCon(apiUrl + apiMethod.getMethod(), HttpMethod.POST);
    }

    protected static void setBaseParams(ParametersMap baseParams) {
        Api.baseParams = baseParams;
    }

    private static void responseCodeHandler() throws IOException {
        int code = con.getResponseCode();
        if (code > 299) {
            Assert.fail("Bad response code: " + code);
        }
    }
}
