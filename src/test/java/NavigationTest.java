import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationTest extends BaseTest {

    @Test
    @DisplayName("Переход в личный кабинет переход по нажатию на кнопку 'Личный кабинет'")
    public void testPersonalCabinet() {
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAccountButton();

        assertTrue(navigationPage.isCabinetPageDisplayed(), "Главная страница не отобразилась");
    }


    @Test
    @DisplayName("Переход из личного кабинета на главную страницу переход по нажатию на кнопку  'Конструктор' ")
    public void testGoToConstructorFromCabinetByConstructorButton() {
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAccountButton();
        assertTrue(navigationPage.isCabinetPageDisplayed(), "Страница личного кабинета не отобразилась");

        mainPage.clickConstructorButton();
        assertTrue(mainPage.isOrderButtonVisible(), "Кнопка 'Оформить заказ' не отобразилась, переход в конструктор не удался");
    }

    @Test
    @DisplayName("Выход из аккаунта по кнопке 'Выйти' ")
    public void testLogout(){
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.clickPersonalAccountButton();
        assertTrue(navigationPage.isCabinetPageDisplayed(), "Страница личного кабинета не отобразилась");

        navigationPage.clickLogoutButton();
        assertTrue(loginPage.isLoginButtonDisplayed(), "Не отображения кнопка входа");

    }
}
