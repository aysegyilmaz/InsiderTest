package org.bases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.DriverFactory;

public class BaseTest extends Data{
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = DriverFactory.initializeDriver();
        driver.get(BASE_URL);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
