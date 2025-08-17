import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest extends BaseTest {



    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт'")
    public void testLoginViaMainButton() {
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    public void testLoginViaPersonalAccount() {
        mainPage.clickPersonalAccountButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testLoginViaRegistrationForm() {
        mainPage.clickPersonalAccountButton();
        loginPage.clickRegisterLink();
        registerPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue( mainPage.isOrderButtonVisible(),"Ожидается, что вход через форму регистрации выполнен успешно");
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testLoginViaPasswordRecovery() {
        mainPage.clickPersonalAccountButton();
        loginPage.clickForgotPasswordLink();
        restorePasswordPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());

        assertTrue(mainPage.isOrderButtonVisible(),"Ожидается, что вход через форму восстановления пароля выполнен успешно");
    }


}
