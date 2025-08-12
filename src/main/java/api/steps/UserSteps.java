package api.steps;
import api.models.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;



import static io.restassured.RestAssured.given;

public class UserSteps {
    private static final String BASE_URI = "https://stellarburgers.nomoreparties.site";
    private final String REGISTER_USER = "/api/auth/register";
    private final String DELETE_USER = "/api/auth/user";
    private final String LOGIN_USER = "/api/auth/login";

    @Step("Метод создания пользователя")
    public Response createUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(REGISTER_USER)
                ;
    }

    @Step("Удаление пользователя")
    public void deleteUser(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .when()
                .delete(DELETE_USER);
    }

    @Step("Логин пользователя")
    public Response login(User user) {
        return given()
                .header("Content-type", "application/json")
                .baseUri(BASE_URI)
                .body(user)
                .when()
                .post(LOGIN_USER)
                ;
    }

    public String getAccessToken(Response response) {
        return response.then().extract().path("accessToken");
    }
}
