package stellarburgers.user;

import io.restassured.specification.RequestSpecification;
import io.restassured.builder.RequestSpecBuilder;
import io.qameta.allure.restassured.AllureRestAssured;

import static io.restassured.http.ContentType.JSON;

public class RestClient {

    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    protected RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .addFilter(new AllureRestAssured())
                .setContentType(JSON)
                .setBaseUri(BASE_URL)
                .build();
    }
}
