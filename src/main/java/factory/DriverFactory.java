package factory;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver() {

        return driver.get();
    }

    public static void setDriver(WebDriver webdriver) {

        driver.set(webdriver);
    }

    public static void unload() {

        driver.remove();
    }
}	