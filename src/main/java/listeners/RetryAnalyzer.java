package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import utilities.Log;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int currentRetry = 0;

    private static final int maxRetry = 2;

    @Override
    public boolean retry(ITestResult result) {

        if (currentRetry < maxRetry) {

            currentRetry++;
            
            Log.warn(
                    "Retrying test: "
                            + result.getMethod().getMethodName()
                            + " | Retry count: "
                            + currentRetry);

            return true;
        }

        return false;
    }
}