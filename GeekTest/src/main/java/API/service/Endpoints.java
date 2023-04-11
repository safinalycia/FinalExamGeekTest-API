package API.service;

public final class Endpoints {

    //конечная точка для получения токена и тестов валдиные/невалидные логин и пароль сиспользованием валид/невалид токена:
    public static final String postAuth = "gateway/login";

    //конечная точка выдача постов. Нужны токен, юзернейм, пароль, queryParams:
    public static final String getPosts = "api/posts";

}
