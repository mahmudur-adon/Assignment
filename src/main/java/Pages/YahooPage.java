package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class YahooPage {
    WebDriver driver;
    WebDriverWait wait;

    public YahooPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set the implicit wait timeout to 10 seconds
        wait = new WebDriverWait(driver, 30); // Initialize WebDriverWait with a timeout of 10 seconds
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a.fuji-button-link.fuji-button-inverted.signin")
    private WebElement signinButton;

    @FindBy(css = "#login-username")
    private WebElement yahooUserName;

    @FindBy(css = "#login-signin")
    private WebElement nextButtonYahoo;

    @FindBy(css = "#login-passwd")
    private WebElement yahooPassword;

    @FindBy(css = "span[title='Ehasanul Haque ROny has shared files with you']")
    private WebElement yahooEmailTitle;

    @FindBy(css = "span[data-test-id='message-group-subject-text']")
    private WebElement yahooEmailBodyTitle;

    //page methods
    public void clickSigninButton() {
        wait.until(ExpectedConditions.elementToBeClickable(signinButton));
        signinButton.click();
    }

    public void enterYahooEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(yahooUserName));
        yahooUserName.click();
        yahooUserName.sendKeys("mahmudtest");
    }
    public void clicknextYahooButton() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButtonYahoo));
        nextButtonYahoo.click();
    }

    public void enterYahooPassword() {
        wait.until(ExpectedConditions.elementToBeClickable(yahooPassword));
        yahooPassword.click();
        yahooPassword.sendKeys("Pop12up!@#");
    }

    public void clickYahooEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(yahooEmailTitle));
        yahooEmailTitle.click();
    }

    public String getYahooEmailTitle() {
        String emailTitle = yahooEmailBodyTitle.getText();
        return emailTitle;
    }






}
