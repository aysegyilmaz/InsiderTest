package org.bases;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class BaseLibrary extends Data {
    protected static WebDriver driver;

    public BaseLibrary(WebDriver driver) {
        BaseLibrary.driver = driver;
    }

    @Step("Url bilgisi alınır")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Step("Metin eşitliği doğrulanır: {expected}")
    public void assertEqualsText(String actual, String expected) {
        Assert.assertEquals(actual, expected, "Metin eşit değil!");
        screenshot();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] screenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
