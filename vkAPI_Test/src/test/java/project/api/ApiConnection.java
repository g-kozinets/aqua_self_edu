package project.api;

import aquality.selenium.logger.Logger;
import framework.resources.PropertiesResourceManager;
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

public class ApiConnection {
    private String apiUrl;
    private ParametersMap baseParams = new ParametersMap();
    private HttpURLConnection con;
    private String parameters = "";

    public ApiConnection(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void setCon(String apiUrl, HttpMethod method) {

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

        int timeout = Integer.parseInt(new PropertiesResourceManager("api.properties").getProperty("connectionTimeout"));
        con.setConnectTimeout(timeout);
        con.setReadTimeout(timeout);
    }

    private void sendRequest() {
        con.setDoOutput(true);

        try(DataOutputStream out = new DataOutputStream(con.getOutputStream())) {
            Logger.getInstance().info("Parameters: " + parameters);
            out.writeBytes(parameters);
            out.flush();
            out.close();

            ResponseReader.read(con);
            responseCodeHandler();
        } catch (IOException e) {
            ExceptionHandler.throwException("Could not send request", e);
        }

    }

    public void addParameter(String parameter, Object value) {
        String format = String.format("%s=%s", parameter, value);
        if (parameters.equals("")) {
            parameters = format;
        } else {
            parameters = parameters + "&" + format;
        }
    }

    private void readParamsMap(ParametersMap params) {
        for (String key : params.keySet()) {
            addParameter(key, params.get(key));
        }
    }

    private void clearParameters() {
        parameters = "";
    }

    protected void sendNewParameters(HttpMethod httpMethod, ApiMethod apiMethod, ParametersMap params) {
        clearParameters();
        readParamsMap(params);
        readParamsMap(baseParams);
        setCon(apiUrl + apiMethod.getMethod(), httpMethod);
        sendRequest();
    }

    protected void sendNewParameters(HttpMethod httpMethod, ApiMethod apiMethod) {
        clearParameters();
        readParamsMap(baseParams);
        setCon(apiUrl + apiMethod.getMethod(), httpMethod);
        sendRequest();
    }

    protected void setBaseParams(ParametersMap baseParams) {
        this.baseParams = baseParams;
    }

    private void responseCodeHandler() {
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
