package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import factory.DriverFactory;

public class JavaScriptUtils {

    public static void clickElement(WebElement element) {

        JavascriptExecutor js =
                (JavascriptExecutor) DriverFactory.getDriver();

        js.executeScript(
                "arguments[0].click();",
                element);
    }

    public static void scrollIntoView(WebElement element) {

        JavascriptExecutor js =
                (JavascriptExecutor) DriverFactory.getDriver();

        js.executeScript(
                "arguments[0].scrollIntoView(true);",
                element);
    }
}