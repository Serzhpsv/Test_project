package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RemindPage {

    private WebElement loginError;
    private WebElement captchaError;
    private WebElement loginButton;
    private WebElement loginField;
    WebDriver driver;

    public RemindPage(WebDriver driver) {
        this.driver = driver;
        loginField = driver.findElement(By.id("id_email"));
        loginButton = driver.findElement(By.id("submit_btn"));
    }

    public RemindPage sendRequest(String log) {
        loginField.sendKeys(log);
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("captcha_1_err")));
        captchaError = driver.findElement(By.id("captcha_1_err"));
        loginError = driver.findElement(By.id("email_err"));
        return new RemindPage(driver);
    }

    public WebElement getCaptchaError() {
        return captchaError;
    }

    public WebElement getLoginError() {
        return loginError;
    }
}
