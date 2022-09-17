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

public class PersonalAccountTest {

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
        webdriver().shouldHave(url(LoginPage.URL));

        LoginPage loginPage = page(LoginPage.class);
        loginPage.setLoginForm(email, password);
        loginPage.clickSubmitButton();
        webdriver().shouldHave(url(ConstructorPage.URL));

        MainPage mainPage = page(MainPage.class);
        mainPage.clickPersonalAccountButton();
        webdriver().shouldHave(url(PersonalAccount.URL));
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
    @DisplayName("Logout from the system on personal account page")
    @Description("Logged user should have an ability to log out from the system")
    public void logoutFromPersonalAccountButtonTest() {
        PersonalAccount personalAccount = page(PersonalAccount.class);
        personalAccount.clickLogoutButton();
        personalAccount.checkURLIsLoginPage();
    }

    @Test
    @DisplayName("By click construction button user redirects from personal account to main page")
    @Description("Create new user and log in. Go to Personal Account. Then click on Constructor button in Header. " +
            "It will redirect you to main page.")
    public void constructorButtonRedirectsFromPersonalAccountToMainPageTest() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickConstructorButton();
        mainPage.checkURlIsMainPage();
    }

    @Test
    @DisplayName("By click logo user redirects from personal account to main page")
    @Description("Create new user and log in. Go to Personal Account. Then click on logo in Header. " +
            "It will redirect you to main page.")
    public void logoRedirectsFromPersonalAccountToMainPage() {
        MainPage mainPage = page(MainPage.class);
        mainPage.clickLogo();
        mainPage.checkURlIsMainPage();
    }

}
