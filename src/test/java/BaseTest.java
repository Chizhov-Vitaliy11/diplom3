import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import api.models.User;
import api.steps.UserSteps;
import pages.*;
import utils.Browser;
import utils.RandomGenerationData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected String browserName;
    protected NavigationPage navigationPage;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected RestorePasswordPage restorePasswordPage;
    protected boolean createUser = true;
    protected UserSteps userSteps;
    protected User user;
    protected String accessToken;

    @BeforeEach
    public void setUp() {
        browserName = System.getProperty("browser");

        if (browserName == null || browserName.isEmpty()) {
            browserName = "chrome";
        }

        System.out.println("Starting test in browser: " + browserName);

        driver = Browser.getBrowser(browserName);
        driver.manage().window().maximize();

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        restorePasswordPage = new RestorePasswordPage(driver);
        navigationPage = new NavigationPage(driver);


//
//            userSteps = new UserSteps();
//            user = new User();
//            user.setEmail(RandomGenerationData.generateRandomEmail());
//            user.setPassword(RandomGenerationData.generateRandomPassword(8));
//            user.setName(RandomGenerationData.generateRandomName());
//            Response response = userSteps.createUser(user);
//            accessToken = userSteps.getAccessToken(response);

        driver.get("https://stellarburgers.nomoreparties.site");
    }

    private String loadBrowserFromProperties() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            props.load(fis);
            return props.getProperty("browser");
        } catch (IOException e) {
            throw new RuntimeException("Error loading properties file", e);
        }
    }

    protected void createUserData(String email, String password, String name) {
        userSteps = new UserSteps();
        user = new User();
        //RandomGenerationData.generateRandomEmail()
        user.setEmail(email);
//    RandomGenerationData.generateRandomPassword(8)
        user.setPassword(password);
        /// RandomGenerationData.generateRandomName()
        user.setName(name);
        Response response = userSteps.createUser(user);
        accessToken = userSteps.getAccessToken(response);

    }

    protected void loginUser(String name, String email, String password) {
        userSteps = new UserSteps();
        user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        Response response = userSteps.login(user);
        accessToken = userSteps.getAccessToken(response);

    }


    @AfterEach
    public void tearDown() {
        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Test finished in browser: " + browserName);
    }
}