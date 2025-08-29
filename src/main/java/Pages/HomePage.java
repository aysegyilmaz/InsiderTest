package Pages;

import org.bases.BaseLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class HomePage extends BaseLibrary {

    private By companyMenu = By.xpath("//a[contains(text(),'Company')]");
    private By careersLink = By.xpath("//a[contains(text(),'Careers')]");

    public void open() {
        driver.get("https://useinsider.com/");
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.titleContains("Insider"));
    }

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHomePageOpened() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.titleContains("Insider"));
            return driver.getTitle().contains("Insider");
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToCareers() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        actions.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(companyMenu))).perform();
        wait.until(ExpectedConditions.elementToBeClickable(careersLink)).click();
    }
}