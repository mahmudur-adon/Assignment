package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class DamPage {

     WebDriver driver;
     WebDriverWait wait;

    // Constructor
    public DamPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // Set the implicit wait timeout to 10 seconds
        wait = new WebDriverWait(driver, 30); // Initialize WebDriverWait with a timeout of 10 seconds
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "a.dropdown-toggle")
    private WebElement DAMButton;

    @FindBy(linkText = "Assets")
    private WebElement assetButton;



    public void clickDAMButton() {
        wait.until(ExpectedConditions.elementToBeClickable(DAMButton));
        DAMButton.click();
    }


    public void clickAssetButton() {
        wait.until(ExpectedConditions.elementToBeClickable(assetButton));
        assetButton.click();
    }

}
