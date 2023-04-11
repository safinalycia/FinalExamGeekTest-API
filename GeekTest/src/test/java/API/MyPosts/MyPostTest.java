package API.MyPosts;

import API.AbstractTest;
import API.service.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import okhttp3.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MyPostTest extends AbstractTest {

    @Test
    @Tag("Positive")
    @DisplayName("Выдача 0 страницы по возрастанию")
    void getMyPostsAsc() {
        given()
                .spec(getRequestSpecificationMyPosts())
                .queryParam(sort, createdAt)
                .queryParam(order, ASC)
                .queryParam(page, 0)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValid());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Выдача 10 страницы по возрастанию")
    void getMyPostsAscPage10() {
        given()
                .spec(getRequestSpecificationMyPosts())
                .queryParam(sort, createdAt)
                .queryParam(order, ASC)
                .queryParam(page, 10)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValid());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Выдача 1 страницы по убыванию")
    void getMyPostsDescPage1() {
        given()
                .spec(getRequestSpecificationMyPosts())
                .queryParam(sort, createdAt)
                .queryParam(order, DESC)
                .queryParam(page, 1)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValid());

    }

    @Test
    @Tag("Negative")
    @DisplayName("Выдача -1 страницы по возрастанию")
    void getMyPostsAscPageNegative() {
        JsonPath response = given()
                .spec(getRequestSpecificationMyPosts())
                .queryParam(sort, createdAt)
                .queryParam(order, ASC)
                .queryParam(page, -1)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .statusCode(400)
                .extract().body().jsonPath();
        assertThat (response.getString("message"), equalTo("Bad Request"));
    }

    @Test
    @Tag("Negative")
    @DisplayName("Выдача страницы со спцсмвл")
    void getMyPostsAscSpecSumbPage() {
        JsonPath response = given()
                .spec(getRequestSpecificationMyPosts())
                .queryParam(sort, createdAt)
                .queryParam(order, ASC)
                .queryParam(page, "$%5")
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .statusCode(400)
                .extract().body().jsonPath();
        assertThat (response.getString("message"), equalTo("Bad Request "));
    }

    @Test
    @Tag("Negative")
    @DisplayName("Выдача станицы со значением буквы")
    void getMyPostsAscLettersPage() {
        //Response response = given()
        JsonPath response = given()
                .spec(getRequestSpecificationMyPosts())
                .queryParam(sort, createdAt)
                .queryParam(order, ASC)
                .queryParam(page, "Dfr")
                .when()
                .get(getBaseUrl())
                .then()
                .statusCode(400)
                .extract().body().jsonPath();
        assertThat (response.getString("message"), equalTo("Bad Request"));

    }

    @Test
    @Tag("Negative")
    @DisplayName("Выдача страницы без передачи токена в хедер")
    void getMyPostsWithoutToken() {
        JsonPath response = given()
                .queryParam(sort, createdAt)
                .queryParam(order, ASC)
                .queryParam(page, 2)
                .contentType(ContentType.JSON)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .statusCode(401)
                .extract().jsonPath();
        assertThat(response.getString("message"), equalTo("Auth header required X-Auth-Token"));
    }

    @Test
    @Tag("Negative")
    @DisplayName("Выдача страницы с невалидным токен")
    void getMyPostsInvalidToken() {
        JsonPath response = given()
                .header("X-Auth-Token", "4bdd557")
                .queryParam(sort, createdAt)
                .queryParam(order, ASC)
                .queryParam(page, 2)
                .contentType(ContentType.JSON)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .statusCode(401)
                .extract().jsonPath();
        assertThat(response.getString("message"), equalTo("No API token provided or is not valid"));
    }



}
