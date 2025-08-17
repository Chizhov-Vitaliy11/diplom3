package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationPage extends BasePage {
    private static final By PROFILE_LINK = By.xpath(".//a[contains(@class, 'Account_link_') and text()='Профиль']");
    private static final By LOGOUT_BUTTON = By.xpath(".//button[text()='Выход']");

    public NavigationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageLoaded() {
        return false;
    }
    @Step("Проверить, что страница личного кабинета отображается")
    public boolean isCabinetPageDisplayed() {
        return waitForElementVisible(PROFILE_LINK).isDisplayed() ||
                waitForElementVisible(LOGOUT_BUTTON).isDisplayed();
    }

    @Step("Нажать кнопку 'Выход'")
    public NavigationPage clickLogoutButton() {
        waitForElementClickable(LOGOUT_BUTTON).click();
        return this;

    }

}
