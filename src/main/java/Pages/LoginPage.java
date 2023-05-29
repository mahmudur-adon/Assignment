package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set the implicit wait timeout to 10 seconds
        wait = new WebDriverWait(driver, 30); // Initialize WebDriverWait with a timeout of 10 seconds
        PageFactory.initElements(driver, this);
    }

    // Define page elements using @FindBy annotation
    @FindBy(css = "input._s_UserName")
    private WebElement userNameInput;

    @FindBy(css = "button.sign-in-box-btn._s_LoginOption")
    private WebElement nextButtonLoginPage;

    @FindBy(css = "input.sign-in-box-input._s_Password")
    private WebElement passwordInput;

    @FindBy(css = ".sign-in-box-btn")
    private WebElement loginButton;


    // Page methods
    public void enterUserName(String userName) {
        userNameInput.click();
        userNameInput.sendKeys(userName);
    }

    public void clickNextButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButtonLoginPage));
        nextButtonLoginPage.click();
    }


    public void enterPassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        passwordInput.click();
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

}
