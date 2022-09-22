package stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import stellarburgers.pageObject.LoginPage;
import stellarburgers.pageObject.RegisterPage;
import stellarburgers.user.User;
import stellarburgers.user.UserClient;
import stellarburgers.user.UserCredentials;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class RegistrationTest {

    TestData testData = new TestData();
    String name;
    String email;
    String password;
    final String invalidPassword = "12345";
    final String validPassword = "123456";

    @Before
    public void setUp() {
        User user = testData.getRandomUserTestData();
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
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
    @DisplayName("User can register in the system with valid data")
    @Description("Register new user. Check that error messages do not appeared and after successful registration" +
            " there is redirect to Login page. After test user is going to be deleted.")
    public void userCanRegisterWithValidDataTest() {
        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);

        registerPage.setRegistrationForm(name, email, password);
        registerPage.clickRegisterButton();
        registerPage.checkIncorrectPasswordMessageDoesNotAppear();
        registerPage.checkExistingUserMessageDoesNotAppear();
        webdriver().shouldHave(url(LoginPage.URL));
    }

    @Test
    @DisplayName("Error message of incorrect password appears, when a user is trying to register " +
            "with 5 numbers in a password")
    @Description("Check that error message appears after clicking on submit button. After test user is going to be deleted.")
    public void errorMessageAppearWhenPassword5NumbersTest() {
        password = invalidPassword;
        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);

        registerPage.setRegistrationForm(name, email, password);
        registerPage.clickRegisterButton();
        registerPage.checkIncorrectPasswordErrorMessage();
    }

    @Test
    @DisplayName("Error message of incorrect password does not appear, when a user is trying to register " +
            "with 6 numbers in a password")
    @Description("Check that error message does not appear after clicking on submit button. " +
            "After test user is going to be deleted.")
    public void errorMessageDoesNotAppearWhenPassword6NumbersTest() {
        password = validPassword;
        RegisterPage registerPage = open(RegisterPage.URL, RegisterPage.class);

        registerPage.setRegistrationForm(name, email, password);
        registerPage.clickRegisterButton();
        registerPage.checkIncorrectPasswordMessageDoesNotAppear();
    }


}
