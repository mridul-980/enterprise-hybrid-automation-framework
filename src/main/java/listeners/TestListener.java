package listeners;



import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import reporting.AllureReportMetadata;
import utilities.AllureUtils;
import utilities.ExtentManager;
import utilities.ScreenshotUtils;

public class TestListener implements ITestListener {

    private static ExtentReports extent =
            ExtentManager.getInstance();

    private static ThreadLocal<ExtentTest> test =
            new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {

        System.out.println(
                "===== Test Suite Started =====");
    }

    @Override
    public void onFinish(ITestContext context) {
        AllureReportMetadata.copyEnvironmentFile();
        AllureReportMetadata.copyExecutorFile();
        AllureReportMetadata.copyCategoriesFile();
        extent.flush();

        System.out.println(
                "===== Test Suite Finished =====");
    }

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest extentTest =
                extent.createTest(
                        result.getMethod().getMethodName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        test.get().fail(result.getThrowable());

        String screenshotPath =
                ScreenshotUtils.captureScreenshot(
                        result.getMethod().getMethodName());

        // Attach to Allure
        AllureUtils.attachScreenshot(screenshotPath);
        AllureUtils.attachExecutionLog();

        // Attach to Extent
        try {

            test.get()
                    .fail("Screenshot attached")
                    .addScreenCaptureFromPath(screenshotPath);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {

        test.get().skip("Test Skipped");
    }
}