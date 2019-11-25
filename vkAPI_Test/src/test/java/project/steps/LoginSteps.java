package project.steps;

import framework.configuration.Configuration;
import io.qameta.allure.Step;
import project.forms.LoginForm;

public class LoginSteps {

    @Step("Enter credentials on login page")
    public void doLogin() {
        LoginForm loginForm = new LoginForm();
        loginForm.typeLogin(Configuration.getCurrentEnvironment().getLogin());
        loginForm.typePassword(Configuration.getCurrentEnvironment().getPassword());
        loginForm.clickSubmit();
    }
}
