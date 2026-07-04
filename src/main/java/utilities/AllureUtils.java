package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.qameta.allure.Allure;

public final class AllureUtils {

    private AllureUtils() {
    }

    public static void attachScreenshot(String screenshotPath) {

        Path path = Paths.get(screenshotPath);

        if (!Files.exists(path)) {
            return;
        }

        try (InputStream is = Files.newInputStream(path)) {

            Allure.addAttachment(
                    path.getFileName().toString(),
                    "image/png",
                    is,
                    ".png");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void attachExecutionLog() {

        Path logPath = Paths.get("logs", "automation.log");

        if (!Files.exists(logPath)) {
            return;
        }

        try (InputStream is = Files.newInputStream(logPath)) {

            Allure.addAttachment(
                    "Execution Log",
                    "text/plain",
                    is,
                    ".log");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}