package Pages;

import org.bases.BaseLibrary;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class JobListPage extends BaseLibrary {

    private By jobListItems = By.cssSelector("div.position-list-item");
    private By positionTitle = By.cssSelector("p.position-title");
    private By departmentName = By.cssSelector("span.position-department");
    private By locationName = By.cssSelector("div.position-location");
    private By viewRoleButton = By.xpath("//a[contains(@href,'lever.co') and contains(text(),'View Role')]");

    public JobListPage(WebDriver driver) {
        super(driver);
    }

    public void filterByLocationAndDepartment(String location, String department) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));


        WebElement locationDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("span#select2-filter-by-location-container")));
        locationDropdown.click();

        WebElement locationInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input.select2-search__field")));
        locationInput.sendKeys(location);

        WebElement locationOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(@class,'select2-results__option') and text()='" + location + "']")));
        locationOption.click();


        WebElement departmentDropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("span#select2-filter-by-department-container")));
        departmentDropdown.click();

        WebElement departmentInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input.select2-search__field")));
        departmentInput.sendKeys(department);

        WebElement departmentOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(@class,'select2-results__option') and text()='" + department + "']")));
        departmentOption.click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(jobListItems));
    }

    public boolean isJobListPresent() {
        return driver.findElements(jobListItems).size() > 0;
    }

    public boolean validateJobDetails(String location, String department) {
        List<WebElement> jobs = driver.findElements(jobListItems);
        boolean atLeastOneValid = false;

        for (WebElement job : jobs) {
            String pos = job.findElement(positionTitle).getText().trim();
            String dep = job.findElement(departmentName).getText().trim();
            String loc = job.findElement(locationName).getText().trim();

            System.out.println("Job bulundu: " + pos + " | " + dep + " | " + loc);

            if (pos.toLowerCase().contains("quality assurance") &&
                    dep.equalsIgnoreCase(department) &&
                    loc.equalsIgnoreCase(location)) {
                atLeastOneValid = true;
            }
        }
        return atLeastOneValid;
    }

    public boolean clickViewRoleAndCheckRedirect() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement viewButton = wait.until(ExpectedConditions.elementToBeClickable(viewRoleButton));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewButton);

        wait.until(d -> driver.getWindowHandles().size() > 1);
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }

        wait.until(ExpectedConditions.urlContains("lever.co"));
        return driver.getCurrentUrl().contains("lever.co");
    }
}
