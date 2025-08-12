package api.steps;
import api.models.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


import static io.restassured.RestAssured.given;

public class UserSteps {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    private final String REGISTER_USER = "/api/auth/register";
    private final String DELETE_USER = "/api/auth/user";
    private final String LOGIN_USER = "/api/auth/login";

    @Step("Метод создания пользователя")
    public Response createUser(User user) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(REGISTER_USER)
                .then()
                .log().all()  // Логировать все детали ответа
                .extract()
                .response()
                ;
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        given()
                .log().all()
                .baseUri(BASE_URI)
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_USER).then()
                .log().all()  // Логировать все детали ответа
                .extract()
                .response()
        ;
    }

    @Step("Логин пользователя")
    public Response login(User user) {
        return given()
                .log().all()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(LOGIN_USER)
                .then()
                .log().all()  // Логировать все детали ответа
                .extract()
                .response()
                ;
    }

    public String getAccessToken(Response response) {
        return response.then().extract().path("accessToken");
    }
}
