package stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.pageObject.*;
import stellarburgers.user.User;
import stellarburgers.user.UserClient;
import stellarburgers.user.UserCredentials;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class LoginTest {

    TestData testData = new TestData();
    String name;
    String email;
    String password;

    @Before
    public void setUp() {
        User user = testData.getRandomUserTestData();
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();

        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);
        registerPage.setRegistrationForm(name, email, password);
        registerPage.clickRegisterButton();
    }

    @After
    public void tearDown() {
        UserClient userClient = new UserClient();
        UserCredentials credentials = UserCredentials.builder().email(email).password(password).build();

        ValidatableResponse loginResponse = userClient.loginWithValidCredentials(credentials);
        int responseStatusCode = loginResponse.extract().statusCode();

        if (responseStatusCode == 200) {
            String token = loginResponse.extract().path("accessToken").toString().substring(7);
            userClient.delete(token);
        }
    }

    @Test
    @DisplayName("Login with valid data by clicking button on main page")
    @Description("Before test user register in the system. User redirects after clicking on login button" +
            " from main page to login page. After successful login user is going to be deleted.")
    public void loginFromMainPageTest() {
        ConstructorPage constructorPage = open(ConstructorPage.URL, ConstructorPage.class);
        constructorPage.clickLoginButton();
        webdriver().shouldHave(url(LoginPage.URL));

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setLoginForm(email, password);
        loginPage.clickSubmitButton();

        webdriver().shouldHave(url(ConstructorPage.URL));
    }

    @Test
    @DisplayName("Login with valid data by clicking personal account button")
    @Description("Before test user register in the system. User redirects after clicking on personal account button" +
            " from main page to login page. After successful login user is going to be deleted.")
    public void loginFromPersonalAccountButtonTest() {
        MainPage mainPage = open(ConstructorPage.URL, MainPage.class);

        mainPage.clickPersonalAccountButton();
        webdriver().shouldHave(url(LoginPage.URL));

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setLoginForm(email, password);
        loginPage.clickSubmitButton();

        webdriver().shouldHave(url(ConstructorPage.URL));
    }

    @Test
    @DisplayName("Login with valid data by clicking login link on registration page")
    @Description("Before test user register in the system. User redirects after clicking login link" +
            " from register to login page. After successful login user is going to be deleted.")
    public void loginFromRegistrationPageTest() {
        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);

        registerPage.clickLoginLink();
        webdriver().shouldHave(url(LoginPage.URL));

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setLoginForm(email, password);
        loginPage.clickSubmitButton();

        webdriver().shouldHave(url(ConstructorPage.URL));
    }

    @Test
    @DisplayName("Login with valid data by clicking login link on forgot password page")
    @Description("Before test user register in the system. User redirects after clicking login link" +
            " from forgot password to login page. After successful login user is going to be deleted.")
    public void loginFromForgotPasswordPageTest() {
        PasswordRecoveryPage forgotPasswordPage = open(PasswordRecoveryPage.URL, PasswordRecoveryPage.class);

        forgotPasswordPage.clickLoginLink();
        webdriver().shouldHave(url(LoginPage.URL));

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setLoginForm(email, password);
        loginPage.clickSubmitButton();

        webdriver().shouldHave(url(ConstructorPage.URL));
    }
}
