package project.api;

import framework.utils.ExceptionHandler;
import org.testng.Assert;
import project.api.data.ParametersMap;
import project.enums.ApiMethod;
import project.enums.HttpMethod;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Api {
    protected static String apiUrl;
    private static ParametersMap baseParams = new ParametersMap();
    private static HttpURLConnection con;
    private static String parameters = "";

    public static void setCon(String apiUrl, HttpMethod method) {
        URL url = null;
        try {
            url = new URL(apiUrl);
        } catch (MalformedURLException e) {
            ExceptionHandler.throwException("Wrong URL", e);
        }

        try {
            con = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            ExceptionHandler.throwException("Could not open URL connection", e);
        }


        try {
            con.setRequestMethod(method.getMethod());
        } catch (ProtocolException e) {
            ExceptionHandler.throwException("Could not set HTTP method", e);
        }
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
    }

    public static void sendRequest() {
        con.setDoOutput(true);

        try(DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
            out.writeBytes(parameters);
            out.flush();
            out.close();

            ResponseReader.read(con);
            responseCodeHandler();
        } catch (IOException e) {
            ExceptionHandler.throwException("Could not send request", e);
        }

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

    protected static void sendNewParameters(ApiMethod apiMethod, ParametersMap params) {
        clearParameters();
        readParamsMap(params);
        readParamsMap(baseParams);
        setCon(apiUrl + apiMethod.getMethod(), HttpMethod.POST);
    }

    protected static void sendNewParameters(ApiMethod apiMethod) {
        clearParameters();
        readParamsMap(baseParams);
        setCon(apiUrl + apiMethod.getMethod(), HttpMethod.POST);
    }

    protected static void setBaseParams(ParametersMap baseParams) {
        Api.baseParams = baseParams;
    }

    private static void responseCodeHandler() {
        int code = 0;
        try {
            code = con.getResponseCode();
        } catch (IOException e) {
            ExceptionHandler.throwException("Could not get response code", e);
        }
        if (code > 299) {
            Assert.fail("Bad response code: " + code);
        }
    }
}
