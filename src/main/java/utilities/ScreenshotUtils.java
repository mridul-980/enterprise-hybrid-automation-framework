package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import factory.DriverFactory;

public class ScreenshotUtils {

    public static String captureScreenshot(String testName) {

        File sourceFile =
                ((TakesScreenshot) DriverFactory.getDriver())
                        .getScreenshotAs(OutputType.FILE);

        String destinationPath =
                System.getProperty("user.dir")
                        + "/screenshots/"
                        + testName
                        + ".png";

        File destinationFile = new File(destinationPath);

        try {

            FileUtils.copyFile(
                    sourceFile,
                    destinationFile);

        } catch (IOException e) {

            e.printStackTrace();
        }

        return destinationPath;
    }
}