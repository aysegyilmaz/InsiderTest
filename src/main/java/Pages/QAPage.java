package Pages;

import org.bases.BaseLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class QAPage extends BaseLibrary {
    private WebDriver driver;

    // Locators
    private By seeAllQAJobsButton = By.xpath("//a[contains(text(),'See all QA jobs')]");

    public QAPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // QA sayfası doğru açıldı mı
    public boolean isQAPageOpened() {
        return driver.getCurrentUrl().toLowerCase().contains("quality-assurance");
    }

    // "See all QA jobs" butonuna tıkla
    public void clickSeeAllQAJobs() {
        driver.findElement(seeAllQAJobsButton).click();
    }
}
