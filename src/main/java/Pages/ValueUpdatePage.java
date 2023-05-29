package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ValueUpdatePage {
    WebDriver driver;
    WebDriverWait wait;
    public ValueUpdatePage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // Set the implicit wait timeout to 30 seconds
        wait = new WebDriverWait(driver, 60); // Initialize WebDriverWait with a timeout of 60 seconds
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "button.chakra-button._s_Edit.css-rce95w")
    private WebElement editButton;

    @FindBy(css = "input.chakra-input._s_singleLineTextField.css-1f285ba")
    private WebElement editTitleBox;

    @FindBy(css = "button.chakra-button._s_SaveItem.css-rce95w")
    private WebElement saveButton;

    @FindBy(css = "button.chakra-button._s_Close.css-rce95w")
    private WebElement closeButton;

    //page methods
    public void clickUploadFileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(editButton));
        editButton.click();
    }

    public void changeTitle() {
        wait.until(ExpectedConditions.elementToBeClickable(editTitleBox));
        editTitleBox.click();
        editTitleBox.clear();
        editTitleBox.sendKeys("QA Automation Engineer");
    }

    public void clickSaveButton() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }

    public void clickCloseButton() {
        wait.until(ExpectedConditions.elementToBeClickable(closeButton));
        closeButton.click();
    }

    public void waitForUpdatedTitle() {
        WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[3]/div[1]"));
        wait.until(ExpectedConditions.textToBe(By.xpath("//tbody/tr[1]/td[3]/div[1]"), "QA Automation Engineer"));
        //wait.until(ExpectedConditions.textToBePresentInElement(element, "QA Automation Engineer"));

    }

    public String getUpdatedTitle() {
        WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[3]/div[1]"));

        String savedTitle = element.getText();
        return savedTitle;
    }
}
