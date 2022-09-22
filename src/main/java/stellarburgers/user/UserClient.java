package stellarburgers.user;

import io.restassured.response.ValidatableResponse;
import io.restassured.RestAssured;
import io.qameta.allure.Step;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.Matchers.equalTo;

public class UserClient extends RestClient{

    private final String USER_AUTH_USER = "/auth/user";
    private final String USER_AUTH_LOGIN_PATH = "/auth/login";

    @Step("Login with valid credentials, status code = 200")
    public ValidatableResponse loginWithValidCredentials(UserCredentials credentials) {
        return RestAssured.given()
                .spec(getBaseSpec())
                .body(credentials)
                .when()
                .post(USER_AUTH_LOGIN_PATH)
                .then().log().ifValidationFails();
    }

    @Step("Delete user")
    public void delete(String token) {
        RestAssured.given()
                .spec(getBaseSpec())
                .auth().oauth2(token)
                .when()
                .delete(USER_AUTH_USER)
                .then().log().ifError()
                .assertThat()
                .statusCode(SC_ACCEPTED)
                .body("success", equalTo(true))
                .body("message", equalTo("User successfully removed"));
    }
}
