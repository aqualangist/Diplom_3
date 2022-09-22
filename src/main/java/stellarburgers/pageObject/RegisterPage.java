package stellarburgers.pageObject;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;

public class RegisterPage {

    final static public String URL = "https://stellarburgers.nomoreparties.site/register";

    @FindBy(how = How.XPATH, using = ".//fieldset[1]/.//input[@type='text']")
    private SelenideElement nameField;

    @FindBy(how = How.XPATH, using = ".//fieldset[2]/.//input[@type='text']")
    private SelenideElement emailField;

    @FindBy(how = How.XPATH, using = ".//input[@type='password']")
    private SelenideElement passwordField;

    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    private SelenideElement errorIncorrectPasswordMessage;

    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerButton;

    @FindBy(how = How.XPATH, using = ".//p[text()='Такой пользователь уже существует']")
    private SelenideElement errorExistingUserMessage;

    @FindBy(how = How.XPATH, using = ".//a[@class='Auth_link__1fOlj']")
    private SelenideElement loginLink;

    @Step("Set name field")
    public void setNameField(String name) {
        nameField.shouldBe(visible);
        nameField.setValue(name);
    }

    @Step("Set email field")
    public void setEmailField(String email) {
        emailField.shouldBe(visible);
        emailField.setValue(email);
    }

    @Step("Set password field")
    public void setPasswordField(String password) {
        passwordField.shouldBe(visible);
        passwordField.setValue(password);
    }

    @Step("Submit the registration form")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Check the error message of incorrect password appears when password's length is less than six digits")
    public void checkIncorrectPasswordErrorMessage() {
        errorIncorrectPasswordMessage.shouldBe(visible).shouldHave(text("Некорректный пароль"));
    }

    @Step("Check the error message of incorrect password does not appear when password's length is six and more digits")
    public void checkIncorrectPasswordMessageDoesNotAppear() {
        errorIncorrectPasswordMessage.shouldNotBe(visible);
    }

    @Step("Check the error message of existing user does not appear when click on register button")
    public void checkExistingUserMessageDoesNotAppear() {
        errorExistingUserMessage.shouldNotBe(visible);
    }

    @Step("Click the login link on the registration page")
    public void clickLoginLink() {
        loginLink.shouldHave(href("/login"));
        loginLink.click();
    }

    @Step("Set the registration form")
    public void setRegistrationForm(String name, String email, String password) {
        setNameField(name);
        setEmailField(email);
        setPasswordField(password);
    }
}
