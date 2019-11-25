package project.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LoginForm extends Form {
    ITextBox loginTxb = getElementFactory().getTextBox(By.id("index_email"), "Login textbox");
    ITextBox passwordTxb = getElementFactory().getTextBox(By.id("index_pass"), "Password textbox");
    IButton submitBtn = getElementFactory().getButton(By.id("index_login_button"), "Submit button");

    public LoginForm() {
        super(By.id("index_login"), "Login");
    }

    public void typeLogin(String login) {
        loginTxb.type(login);
    }

    public void typePassword(String pass) {
        passwordTxb.type(pass);
    }

    public void clickSubmit() {
        submitBtn.click();
    }
}
