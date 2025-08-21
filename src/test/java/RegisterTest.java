import api.models.User;
import api.steps.UserSteps;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import utils.RandomGenerationData;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTest extends BaseTest {



    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void testSuccessfulRegistration() {



        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        String name = RandomGenerationData.generateRandomName();
        String email = RandomGenerationData.generateRandomEmail();
        String password = RandomGenerationData.generateRandomPassword(8);
        registerPage.register(
                name,
                email,
                password
        );
        loginUser(name,email,password);
        assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Test
    @DisplayName("Проверка регистрации пользователя с коротким паролем")
    public void testRegistrationWithShortPassword() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registrationPage = new RegisterPage(driver);
        registrationPage.register(
                RandomGenerationData.generateRandomName(),
                RandomGenerationData.generateRandomEmail(),
                RandomGenerationData.generateRandomPassword(5)
        );

        assertTrue(registrationPage.isPasswordErrorDisplayed());
    }
}
