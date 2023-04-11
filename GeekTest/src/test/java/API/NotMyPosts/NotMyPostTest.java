package API.NotMyPosts;

import API.AbstractTest;
import API.service.Endpoints;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class NotMyPostTest extends AbstractTest {
    @Test
    @Tag("Positive")
    @DisplayName("Выдача 5 страницы чужих постов по возрастанию")
    void getNotMyPostsAsc() {
        given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam(owner,notMe)
                .queryParam(sort, createdAt)
                .queryParam(order, ASC)
                .queryParam(page, 5)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValid());
    }
    @Test
    @Tag("Positive")
    @DisplayName("Выдача 15999 страницы чужих постов по возрастанию")
    void getNotMyPostsAscPage15999() {
        given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam(owner,notMe)
                .queryParam(sort, createdAt)
                .queryParam(order, ASC)
                .queryParam(page, 15999)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValid());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Выдача чужих постов страница 1 по убыванию")
    void getNotMyPostsDescPage1() {
        given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam(owner,notMe)
                .queryParam(sort, createdAt)
                .queryParam(order, DESC)
                .queryParam(page, 1)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValid());
    }
    @Test
    @Tag("Positive")
    @DisplayName("Выдача All чужих постов страница 1")
    void getNotMyPostsAllPage1() {
        given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam(owner,notMe)
                .queryParam(sort, createdAt)
                .queryParam(order, ALL)
                .queryParam(page, 1)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValid());
    }

    @Test
    @Tag("Positive")
    @DisplayName("Выдача All чужих постов страница 0")
    void getNotMyPostsAllPage0() {
        given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam(owner,notMe)
                .queryParam(sort, createdAt)
                .queryParam(order, ALL)
                .queryParam(page, 0)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .spec(getResponseSpecificationPostsValid());
    }

    @Test
    @Tag("Negative")
    @DisplayName("Выдача чужих постов по возрастанию страница -1")
    void getNotMyPostsPageIsNegative() {
        JsonPath response = given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam(owner,notMe)
                .queryParam(sort, createdAt)
                .queryParam(order, ASC)
                .queryParam(page, -1)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .statusCode(400)
                .extract().body().jsonPath();
        assertThat (response.getString("message"), equalTo("Bad request"));
    }

    @Test
    @Tag("Negative")
    @DisplayName("Выдача чужих постов по возрастанию пустой страницы")
    void getNotMyPostsAscEmptyPage() {
        JsonPath response = given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam(owner,notMe)
                .queryParam(sort, createdAt)
                .queryParam(order, ASC)
                .queryParam(page)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .statusCode(400)
                .extract().body().jsonPath();
        assertThat (response.getString("message"), equalTo("Bad request"));
    }

    @Test
    @Tag("Negative")
    @DisplayName("Выдача чужих постов пустой ордер, страница буквами")
    void getNotMyPostsEmptyOrderWithPageLetters() {
        JsonPath response = given()
                .spec(getRequestSpecificationNotMyPosts())
                .queryParam(owner,notMe)
                .queryParam(sort, createdAt)
                .queryParam(order)
                .queryParam(page, "Lorem")
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .statusCode(400)
                .extract().body().jsonPath();
        assertThat (response.getString("message"), equalTo("Bad request"));
    }

    @Test
    @Tag("Negative")
@DisplayName("Выдача чужих постов с пустым значением токена")
    void getNotMyPostsEmptyToken() {
        JsonPath response = given()
                .header("X-Auth-Token", "")
                .queryParam(owner, notMe)
                .queryParam(sort, createdAt)
                .queryParam(order, DESC)
                .queryParam(page, 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .statusCode(401)
                .extract().jsonPath();
        assertThat(response.getString("message"), equalTo("No API token provided or is not valid"));
    }

    @Test
    @Tag("Negative")
    @DisplayName("Выдача чужих постов с невалидным токеном")
    void getNotMyPostsInvalidToken() {
        JsonPath response = given()
                .header("X-Auth-Token", "4bdd557")
                .queryParam(owner, notMe)
                .queryParam(sort, createdAt)
                .queryParam(order, ASC)
                .queryParam(page, 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getBaseUrl() + Endpoints.getPosts)
                .then()
                .statusCode(401)
                .extract().jsonPath();
        assertThat(response.getString("message"), equalTo("No API token provided or is not valid"));
    }


    @Test
    @Tag("Negative")
    @DisplayName("Выдача чужих постов без указания токена")
    void getNotMyPostsWithoutToken (){
        JsonPath response = given()
                .queryParam(owner, notMe)
                .queryParam(sort, createdAt)
                .queryParam(order, DESC)
                .queryParam(page, 2)
                .contentType(ContentType.JSON)
                .when()
                .get(getBaseUrl()+ Endpoints.getPosts)
                .then()
                .statusCode(401)
                .extract().jsonPath();
        assertThat(response.getString("message"), equalTo("Auth header required X-Auth-Token"));


    }

}
