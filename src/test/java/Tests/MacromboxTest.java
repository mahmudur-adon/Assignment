package Tests;

import Pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.lang.reflect.Method;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MacromboxTest {
    WebDriver driver;
    LoginPage loginPage;
    DamPage damPage;
    AssetsPage assetsPage;
    ValueUpdatePage valueUpdatePage;
    YahooPage yahooPage;

    @BeforeClass
    public void setUp() throws InterruptedException, AWTException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qatest.marcombox.com");

        loginPage = new LoginPage(driver);
        damPage = new DamPage(driver);
        assetsPage = new AssetsPage(driver);
        valueUpdatePage=new ValueUpdatePage(driver);
        yahooPage=new YahooPage(driver);

        // Login once and upload the image
        login();
        uploadFile();
    }

    private void login() throws AWTException, InterruptedException {
        Properties properties = new Properties();
        try (FileInputStream file = new FileInputStream("src/main/java/pages/credentials.properties")) {
            properties.load(file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        loginPage.enterUserName(username);
        loginPage.clickNextButton();
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();

        damPage.clickDAMButton();
        damPage.clickAssetButton();
    }

    private void uploadFile() throws InterruptedException, AWTException {
        assetsPage.clickUploadButton();
        assetsPage.clickUploadFileButton();
        assetsPage.uploadFile();
        assetsPage.setTitle();
        assetsPage.setType();
        assetsPage.clickSaveButton();
    }

    @Test(priority = 1, description = "This test verifies the file name exists under the File column.")
    public void fileNameTest() throws InterruptedException {
        // Verify the same name file error message is not present
        assetsPage.waitForError();

        // Wait until the number of elements increases by 3 as there are 3 values for each row
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(elementsCountIncreasedBy(By.cssSelector("#fieldValue"), 3));

        // Verify the file name exists under the File column
        String savedValue = assetsPage.getFileName();
        Assert.assertEquals(savedValue, "demo1.jpg");

    }

    @Test(priority = 2,dependsOnMethods = "fileNameTest", description = "This test verifies the thumbnail of the uploaded image has generated.")
    public void thumbnailTest() throws InterruptedException {
        // Verify that the thumbnail image is loaded
        WebElement thumbnailImage = assetsPage.verifyThumbnail();
        Assert.assertTrue(thumbnailImage.isDisplayed(), "Thumbnail image is not loaded.");

    }

    @Test(priority = 3,dependsOnMethods = "thumbnailTest", description = "This test verifies the title & type fields value has saved.")
    public void titleTypeTest() throws InterruptedException {

        // Verify the saved title and type
        String savedTitle = assetsPage.verifyTitle();
        Assert.assertEquals(savedTitle, "QA Automation");

        String savedType = assetsPage.getType();
        Assert.assertEquals(savedType, "Image");
    }

    @Test(priority = 4,dependsOnMethods = "titleTypeTest", description = "This test verifies the Updated Title value is showing up in the list.")
    public void updatedTitleTest() throws InterruptedException {
        assetsPage.clickSavedValue();
        valueUpdatePage.clickUploadFileButton();
        valueUpdatePage.changeTitle();
        valueUpdatePage.clickSaveButton();
        valueUpdatePage.clickCloseButton();

        // Verify the saved title
        valueUpdatePage.waitForUpdatedTitle();
        String savedTitle = valueUpdatePage.getUpdatedTitle();
        Assert.assertEquals(savedTitle, "QA Automation Engineer");

    }

    @Test(priority = 5, description = "This test verifies the share functionality")
    public void shareTest() throws InterruptedException {
    assetsPage.clickCheckBox();
    assetsPage.clickShareButton();
    assetsPage.enterEmail();
    assetsPage.sendEmail();

    driver.get("https://mail.yahoo.com");
    yahooPage.clickSigninButton();
    yahooPage.enterYahooEmail();
    yahooPage.clicknextYahooButton();
    yahooPage.enterYahooPassword();
    yahooPage.clicknextYahooButton();
    yahooPage.clickYahooEmail();
    String emailTitle= yahooPage.getYahooEmailTitle();
    Assert.assertEquals(emailTitle, "Ehasanul Haque ROny has shared files with you");

    }

    // Custom condition for elements count increase
    public static ExpectedCondition<Boolean> elementsCountIncreasedBy(By locator, int count) {
        return new ExpectedCondition<Boolean>() {
            private int previousCount = -1;

            @Override
            public Boolean apply(WebDriver driver) {
                int currentCount = driver.findElements(locator).size();
                System.out.println(currentCount);
                if (currentCount - previousCount >= count) {
                    return true;
                }
                previousCount = currentCount;
                return false;
            }

            @Override
            public String toString() {
                return String.format("Number of elements to increase by %d. Current count: %d", count, previousCount);
            }
        };
    }

    @AfterClass
    public void tearDown() {
        // Quit the WebDriver instance
        driver.quit();
    }
}
