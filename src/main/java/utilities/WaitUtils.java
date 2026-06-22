package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import factory.DriverFactory;

public class WaitUtils {

    private static WebDriverWait wait =
            new WebDriverWait(
                    DriverFactory.getDriver(),
                    Duration.ofSeconds(
                            Integer.parseInt(
                                    ConfigReader.getProperty("explicitWait"))));

    public static WebElement waitForElementVisible(By locator) {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForElementClickable(By locator) {

        return wait.until(
                ExpectedConditions.elementToBeClickable(locator));
    }
}