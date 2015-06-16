package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountPage {

    WebDriver driver;
    private String actialTitle;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getActualTitle() {
        WebDriverWait wait = new WebDriverWait(this.driver, 10);
        wait.until(ExpectedConditions.titleIs("Account Manager"));
        actialTitle = driver.getTitle();
        return actialTitle;
    }
}
