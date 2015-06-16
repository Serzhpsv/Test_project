package Tests;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AccountPage;
import pages.LoginPage;
import pages.RemindPage;
import java.util.concurrent.TimeUnit;


public class GoAntiFraudTestPage {

    private FirefoxDriver driver;
    public static final String VALID_LOGIN = "tt";
    public static final String INVALID_LOGIN = "someFakeLogin";
    public static final String VALID_PASSWORD = "123456";

    @BeforeMethod
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://goantifraud.com/manager");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    //Проверка корректного входа
    @Test(priority = 0)
    public void loginWithValidDaTa() {
        LoginPage loginForm = new LoginPage(driver);
        AccountPage account = loginForm.InputCorrectData(VALID_LOGIN, VALID_PASSWORD);
        Assert.assertEquals(account.getActualTitle(), "Account Manager");
    }

    //Проверка входа с некорректным логином
    @Test(priority = 1)
    public void loginWithInvalidLoginName() {
        LoginPage loginForm = new LoginPage(driver);
        loginForm.InputIncorrectData(INVALID_LOGIN, VALID_PASSWORD);
        Assert.assertEquals(loginForm.getLoginError().getText(), "Login or password is incorrect");
    }

    //Проверка восстановления пароля
    @Test(priority = 2)
    public void retrievePasswordWithValidData() {
        LoginPage loginForm = new LoginPage(driver);
        RemindPage remind = loginForm.remindPassword();
        remind.sendRequest(VALID_LOGIN);
        Assert.assertEquals(remind.getCaptchaError().getText(), "Please, enter the captcha here");
    }

    @Test(priority = 3)
    public void retrievePasswordWithInvalidLoginName() {
        LoginPage loginForm = new LoginPage(driver);
        RemindPage remind = loginForm.remindPassword();
        remind.sendRequest(INVALID_LOGIN);
        Assert.assertEquals(remind.getLoginError().getText(), "Username is not exist");
    }

}
