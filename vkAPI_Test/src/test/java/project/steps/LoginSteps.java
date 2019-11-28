package project.steps;

import framework.configuration.Configuration;
import io.qameta.allure.Step;
import org.testng.Assert;
import project.forms.LoginForm;

public class LoginSteps {

    @Step("Enter credentials on login page")
    public void doLogin() {
        LoginForm loginForm = new LoginForm();
        Assert.assertTrue(loginForm.isFormDisplayed(), "Not on login page");

        loginForm.typeLogin(Configuration.getCurrentEnvironment().getLogin());
        loginForm.typePassword(Configuration.getCurrentEnvironment().getPassword());
        loginForm.clickSubmit();
    }
}
