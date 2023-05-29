package Pages;

import org.bouncycastle.util.Store;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AssetsPage {

    WebDriver driver;
    WebDriverWait wait;

    String currDir= System.getProperty("user.dir");
    String filePath = currDir+"\\TestData\\demo1.jpg";


    // Constructor
    public AssetsPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set the implicit wait timeout to 10 seconds
        wait = new WebDriverWait(driver, 30); // Initialize WebDriverWait with a timeout of 10 seconds
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a._s_FileUpload.secondary-navigation-item")
    private WebElement uploadButton;

    @FindBy(css= "span.upload.btn.btn-success.fileinput-button")
    private WebElement uploadFileButton;

    @FindBy(css= "input._s_Value.span12")
    private WebElement titleButton;

    @FindBy(css= "select.category-value-container._s_Value")
    private WebElement dropdown;

    @FindBy(css= "button.btn.btnSuccess._s_UploadSave")
    private WebElement saveButton;

    @FindBy(css= "div._s_BatchUploadFailureMessage.alert-error.alert.upload-message")
    public WebElement errorMessage;

    @FindBy(css= "#fieldValue")
    private WebElement savedValue;

    @FindBy(css= "div._s_CheckBoxTd._s_Icon_Select.select-icon-check.select-icon-check-grid")
    private WebElement checkBox;

    @FindBy(css= "a._s_ShareItems.secondary-navigation-item")
    private WebElement shareButton;

    @FindBy(css= "#s2id_autogen2")
    private WebElement emailTextBox;

    @FindBy(css= "button.btn.btn-success._s_Send")
    private WebElement emailSendButton;

    //page methods
    public void clickUploadButton() {
        wait.until(ExpectedConditions.elementToBeClickable(uploadButton));
        uploadButton.click();
    }


    public void clickUploadFileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(uploadFileButton));
        uploadFileButton.click();
    }

    public void waitForError() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div._s_BatchUploadFailureMessage.alert-error.alert.upload-message")));
    }



    public void uploadFile() throws AWTException, InterruptedException {

        // Create a Robot instance
        Robot robot = new Robot();


        // Pause for a few seconds to allow the file upload dialog to appear
        Thread.sleep(2000);

        // Copy the file path to the clipboard
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        // Simulate pressing Ctrl+V to paste the file path into the dialog
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        Thread.sleep(2000);

        // Simulate pressing Enter to confirm the file selection
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void setTitle() {
        wait.until(ExpectedConditions.elementToBeClickable(titleButton));
        titleButton.click();
        titleButton.sendKeys("QA Automation");
    }

    public void setType() {
        Select select = new Select(dropdown);
        select.selectByValue("611a2ea58986222b540c61b1");
    }

    public void clickSaveButton() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", saveButton);
        saveButton.click();
    }

    public String getFileName() {
        // Wait for the new value to be displayed
        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div.file-name.pull-left"), "demo1.jpg"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String textContent = (String) js.executeScript("return document.querySelectorAll('div.file-name.pull-left')[0].textContent");
        return textContent;
    }

    public WebElement verifyThumbnail() {
        // Wait for the thumbnail image to be visible within 10 seconds
        WebDriverWait wait = new WebDriverWait(driver, 50);
        WebElement thumbnailImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img._s_thumb_MediumImage")));

        return thumbnailImage;
    }

    public String verifyTitle() {
        WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[3]/div[1]"));
        String savedTitle = element.getText();
        return savedTitle;
    }

    public String getType() {
        WebElement element = driver.findElement(By.xpath("//tbody/tr[1]/td[4]/div[1]"));
        String savedType = element.getText();
        return savedType;
    }

    public void clickSavedValue() {
        wait.until(ExpectedConditions.elementToBeClickable(savedValue));
        savedValue.click();
    }

    public void clickCheckBox() {
        wait.until(ExpectedConditions.elementToBeClickable(checkBox));
        checkBox.click();
    }

    public void clickShareButton() {
        wait.until(ExpectedConditions.elementToBeClickable(shareButton));
        shareButton.click();
    }

    public void enterEmail() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(emailTextBox));
        emailTextBox.click();
        emailTextBox.sendKeys("mahmudtest@yahoo.com");
        Thread.sleep(2000);
        emailTextBox.sendKeys(Keys.ENTER);
    }

    public void sendEmail() {
        wait.until(ExpectedConditions.elementToBeClickable(emailSendButton));
        emailSendButton.click();
    }


}
