package Pages;


import org.bases.BaseLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CareerPage extends BaseLibrary {

    private By locationsBlock = By.xpath("//h3[contains(text(),'Our Locations')]");
    private By teamsBlock = By.xpath("//h3[contains(text(),'Find your calling')]");
    private By lifeBlock = By.xpath("//h2[contains(text(),'Life at Insider')]");


    public CareerPage(WebDriver driver) {
        super(driver);
    }


    public boolean isLocationsBlockDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(locationsBlock));
            return block.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isTeamsBlockDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(teamsBlock));
            return block.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLifeBlockDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement block = wait.until(ExpectedConditions.visibilityOfElementLocated(lifeBlock));
            return block.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void clickSeeAllTeams() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement seeAllTeamsBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("a.btn.loadmore")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", seeAllTeamsBtn);

        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", seeAllTeamsBtn);
    }



    public void goToQAPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement qaLink = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[contains(@href,'quality-assurance')]")
        ));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", qaLink);

        try { Thread.sleep(500); } catch (InterruptedException ignored) {}

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", qaLink);
    }

}