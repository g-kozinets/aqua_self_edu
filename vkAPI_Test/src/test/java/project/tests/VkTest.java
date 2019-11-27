package project.tests;


import framework.api.ResponseReader;
import framework.api.VkApi;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import project.enums.HttpMethod;
import project.forms.MainFeedForm;
import project.steps.LoginSteps;
import java.io.IOException;
import java.util.ArrayList;


import static project.enums.SideMenuId.MY_PROFILE;

public class VkTest extends BaseTest{
    private LoginSteps loginSteps = new LoginSteps();
    private MainFeedForm mainFeedForm = new MainFeedForm();

    @Test
    public void loginTest() throws IOException {
        loginSteps.doLogin();

        mainFeedForm.selectOnSideMenu(MY_PROFILE);

        VkApi.sendWallPost("agbniul");
    }

    @Test
    public void gson() {
        String jsonString = "{\"response\":{\"count\":2,\"items\":[561155508,381108928]}}";
        JSONObject request = new JSONObject(jsonString);
        ArrayList zipCode = ResponseReader.convertJsonArrToArray(request.getJSONObject("response").getJSONArray("items"));
        Assert.assertTrue(zipCode.contains(561155508));
    }
}
