package API;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class AbstractTest {

    //параметры для проверок:

    static Properties prop = new Properties();
    private static InputStream configFile;
    private static String baseUrl;
    private static String header;
    private static String token;
    private static String validUsername;
    private static String validPassword;
    private static String invalidUsername;
    private static String invalidPassword;

    //параметры для запроса:
    protected static String owner = "owner";
    protected static String notMe = "notMe";
    protected static String sort = "sort";
    protected static String createdAt = "createdAt";
    protected static String order = "order";
    protected static String ASC = "ASC";
    protected static String DESC = "DESC";
    protected static String ALL = "ALL";
    protected static String page = "page";
    protected static String username = "username";
    protected static String password = "password";

    //спецификация для каждого запроса:
    protected static RequestSpecification requestSpecificationValidUsernameAndPassword;
    protected static RequestSpecification requestSpecificationInvalidUsername;
    protected static RequestSpecification requestSpecificationInvalidPassword;
    protected static RequestSpecification requestSpecificationMyPosts;
    protected static RequestSpecification requestSpecificationNotMyPosts;
    protected static ResponseSpecification responseSpecificationAuthValid;
    protected static ResponseSpecification responseSpecificationPostsValid;
    protected static ResponseSpecification responseSpecificationInvalidUsernameOrPassword;

//запускать перед всеми тестами, использовать my.properties, спецификации к запросам и ответам
    @BeforeAll
    static void initTest() throws IOException {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        configFile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configFile);
        baseUrl = prop.getProperty("baseUrl");
        validUsername = prop.getProperty("validUsername");
        validPassword = prop.getProperty("validPassword");
        invalidUsername = prop.getProperty("invalidUsername");
        invalidPassword = prop.getProperty("invalidPassword");
        header = prop.getProperty("header");
        token = prop.getProperty("token");

        // в запросах и ответах ожидать:

        requestSpecificationValidUsernameAndPassword = new RequestSpecBuilder()
                .addFormParam(username, validUsername)
                .addFormParam(password, validPassword)
                .log(LogDetail.URI)
                .build();

        requestSpecificationInvalidUsername = new RequestSpecBuilder()
                .addFormParam(username, invalidUsername)
                .addFormParam(password, validPassword)
                .log(LogDetail.URI)
                .build();

        requestSpecificationInvalidPassword = new RequestSpecBuilder()
                .addFormParam(username, validUsername)
                .addFormParam(password, invalidPassword)
                .log(LogDetail.URI)
                .build();

        responseSpecificationAuthValid = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.TEXT)
                .expectResponseTime(Matchers.lessThan(5000L))
                .log(LogDetail.STATUS)
                .build();

        responseSpecificationInvalidUsernameOrPassword = new ResponseSpecBuilder()
                .expectStatusCode(401)
                .expectStatusLine("HTTP/1.1 401 Unauthorized")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .log(LogDetail.STATUS)
                .build();

        requestSpecificationMyPosts = new RequestSpecBuilder()
                .addHeader(header, token)
                .addQueryParam(sort, createdAt)
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .build();

        requestSpecificationNotMyPosts = new RequestSpecBuilder()
                .addHeader(header, token)
                .addQueryParam(sort, createdAt)
                .addQueryParam(owner, notMe)
                .setContentType(ContentType.JSON)
                .log(LogDetail.URI)
                .build();

        responseSpecificationPostsValid = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .log(LogDetail.STATUS)
                .build();
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    //геттеры спецификаций:

    public static RequestSpecification getRequestSpecificationValidUsernameAndPassword() {
        return requestSpecificationValidUsernameAndPassword;
    }

    public static RequestSpecification getRequestSpecificationInvalidUsername() {
        return requestSpecificationInvalidUsername;
    }

    public static RequestSpecification getRequestSpecificationInvalidPassword() {
        return requestSpecificationInvalidPassword;
    }

    public static ResponseSpecification getResponseSpecificationAuthValid() {
        return responseSpecificationAuthValid;
    }

    public static ResponseSpecification getResponseSpecificationInvalidUsernameOrPassword() {
        return responseSpecificationInvalidUsernameOrPassword;
    }

    public static RequestSpecification getRequestSpecificationMyPosts() {
        return requestSpecificationMyPosts;
    }

    public static RequestSpecification getRequestSpecificationNotMyPosts() {
        return requestSpecificationNotMyPosts;
    }

    public static ResponseSpecification getResponseSpecificationPostsValid() {
        return responseSpecificationPostsValid;
    }

}
