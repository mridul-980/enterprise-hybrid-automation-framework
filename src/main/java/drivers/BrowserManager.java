package drivers;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import constants.ExecutionType;
import factory.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;
import utilities.RuntimeConfig;

public class BrowserManager {

    public static void initializeBrowser(String browser) {

        WebDriver driver = null;

        boolean maximize =
                Boolean.parseBoolean(
                        ConfigReader.getProperty("maximize"));

        ExecutionType execution =
                RuntimeConfig.getExecutionType();

        String remoteUrl =
                RuntimeConfig.getRemoteUrl();

        switch (browser.toLowerCase()) {

        case "chrome":
            driver = createChromeDriver(execution, remoteUrl);
            break;

        case "firefox":
            driver = createFirefoxDriver(execution, remoteUrl);
            break;

        case "edge":
            driver = createEdgeDriver(execution, remoteUrl);
            break;

        default:
            throw new IllegalArgumentException(
                    "Invalid Browser: " + browser);
        }

        if (maximize) {
            driver.manage().window().maximize();
        }

        DriverFactory.setDriver(driver);
    }

    private static WebDriver createChromeDriver(
            ExecutionType execution,
            String remoteUrl) {

        ChromeOptions options = new ChromeOptions();

        boolean headless =
                Boolean.parseBoolean(
                        ConfigReader.getProperty("headless"));

        boolean incognito =
                Boolean.parseBoolean(
                        ConfigReader.getProperty("incognito"));

        boolean disableNotifications =
                Boolean.parseBoolean(
                        ConfigReader.getProperty("disable.notifications"));

        boolean acceptInsecureCerts =
                Boolean.parseBoolean(
                        ConfigReader.getProperty("accept.insecure.certs"));

        String pageLoadStrategy =
                ConfigReader.getProperty("page.load.strategy");

        if (headless) {
            options.addArguments("--headless=new");
        }

        if (incognito) {
            options.addArguments("--incognito");
        }

        if (disableNotifications) {
            options.addArguments("--disable-notifications");
        }

        if (acceptInsecureCerts) {
            options.setAcceptInsecureCerts(true);
        }

        switch (pageLoadStrategy.toLowerCase()) {

        case "eager":
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            break;

        case "none":
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            break;

        default:
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            break;
        }

        if (execution == ExecutionType.REMOTE) {
            return createRemoteDriver(remoteUrl, options);
        }

        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver(
            ExecutionType execution,
            String remoteUrl) {

        FirefoxOptions options = new FirefoxOptions();

        boolean headless =
                Boolean.parseBoolean(
                        ConfigReader.getProperty("headless"));

        if (headless) {
            options.addArguments("--headless");
        }

        if (execution == ExecutionType.REMOTE) {
            return createRemoteDriver(remoteUrl, options);
        }

        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver(
            ExecutionType execution,
            String remoteUrl) {

        EdgeOptions options = new EdgeOptions();

        boolean headless =
                Boolean.parseBoolean(
                        ConfigReader.getProperty("headless"));

        if (headless) {
            options.addArguments("--headless=new");
        }

        if (execution == ExecutionType.REMOTE) {
            return createRemoteDriver(remoteUrl, options);
        }

        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(options);
    }

    private static WebDriver createRemoteDriver(
            String remoteUrl,
            MutableCapabilities capabilities) {

        try {

            return new RemoteWebDriver(
                    new URL(remoteUrl),
                    capabilities);

        } catch (MalformedURLException e) {

            throw new RuntimeException(
                    "Invalid Remote URL: " + remoteUrl,
                    e);
        }
    }
}