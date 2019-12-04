package project.steps;

import framework.resources.PropertiesResourceManager;
import io.qameta.allure.Step;
import org.testng.Assert;
import project.forms.LoginForm;

public class LoginSteps {
    private PropertiesResourceManager properties = new PropertiesResourceManager("api.properties");

    @Step("Enter credentials on login page")
    public void doLogin() {
        LoginForm loginForm = new LoginForm();
        Assert.assertTrue(loginForm.isFormDisplayed(), "Not on login page");

        loginForm.typeLogin(properties.getProperty("login"));
        loginForm.typePassword(properties.getProperty("password"));
        loginForm.clickSubmit();
    }
}
