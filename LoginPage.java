package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LoginPage {

    private WebElement forgotYourPassLink;
    private WebElement login;
    private WebElement password;
    private WebElement loginButton;
    private WebElement loginError;
    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        login = driver.findElement(By.id("login"));
        password = driver.findElement(By.id("password"));
        loginButton = driver.findElement(By.id("submit_btn"));
        forgotYourPassLink = driver.findElement(By.linkText("Forgot your password?"));
    }

    public AccountPage InputCorrectData(String log, String pass) {
        login.sendKeys(log);
        password.sendKeys(pass);
        loginButton.click();
        return new AccountPage(driver);
    }

    public LoginPage InputIncorrectData(String log, String pass) {
        login.sendKeys(log);
        password.sendKeys(pass);
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("none_err")));
        loginError = driver.findElement(By.id("none_err"));
        return new LoginPage(this.driver);
    }

    public WebElement getLoginError() {
        return loginError;
    }

    public RemindPage remindPassword() {
        forgotYourPassLink.click();
        return new RemindPage(driver);
    }

}
