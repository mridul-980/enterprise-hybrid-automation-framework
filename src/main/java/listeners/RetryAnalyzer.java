package listeners;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import utilities.ConfigReader;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;

    private final int maxRetryCount =
            Integer.parseInt(
                    ConfigReader.getProperty("retry.count"));

    @Override
    public boolean retry(ITestResult result) {

        // No retries left
        if (retryCount >= maxRetryCount) {
            return false;
        }

        boolean retryAssertions =
                Boolean.parseBoolean(
                        ConfigReader.getProperty("retry.assertion.failures"));

        Throwable throwable = result.getThrowable();

        // Retry assertion failures only if enabled
        if (throwable instanceof AssertionError) {

            if (!retryAssertions) {
                return false;
            }

        } 
        // Retry flaky Selenium exceptions
        else if (!(throwable instanceof TimeoutException
                || throwable instanceof StaleElementReferenceException
                || throwable instanceof NoSuchElementException
                || throwable instanceof ElementClickInterceptedException
                || throwable instanceof WebDriverException)) {

            return false;
        }

        retryCount++;

        System.out.println(
                "Retrying test: "
                        + result.getMethod().getMethodName()
                        + " ("
                        + retryCount
                        + "/"
                        + maxRetryCount
                        + ")");

        return true;
    }
}