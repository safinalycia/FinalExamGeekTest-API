package API.Auth;


import API.AbstractTest;
import API.service.Endpoints;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class AuthTest extends AbstractTest {


    @Test
    @Tag("Positive")
    @DisplayName("Авторизация с валидными логин и пароль")
    void postAuthValidTest() {
        given()
                .spec(getRequestSpecificationValidUsernameAndPassword())
                .when()
                .post(getBaseUrl() + Endpoints.postAuth)
                .then()
                .spec(getResponseSpecificationAuthValid());
    }

    @Test
    @Tag("Negative")
    @DisplayName("Авторизация с невалидным логин/валидный пароль")
    void postAuthInvalidUsernameTest() {
        given()
                .spec(getRequestSpecificationInvalidUsername())
                .when()
                .post(getBaseUrl() + Endpoints.postAuth)
                .then()
                .spec(getResponseSpecificationInvalidUsernameOrPassword());
    }

    @Test
    @Tag("Negative")
    @DisplayName("Авторизация с невалидным пароль/валидный логин")
    void postAuthInvalidPasswordTest() {
        given()
                .spec(getRequestSpecificationInvalidPassword())
                .when()
                .post(getBaseUrl() + Endpoints.postAuth)
                .then()
                .spec(getResponseSpecificationInvalidUsernameOrPassword());
    }

}
