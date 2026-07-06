package reporting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class AllureReportMetadata {

    private static final String SOURCE =
            "src/test/resources/allure/environment.properties";

    private static final String DESTINATION =
            "target/allure-results/environment.properties";

    public static void copyEnvironmentFile() {

        try {

            Files.copy(
                    Path.of(SOURCE),
                    Path.of(DESTINATION),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    public static void copyExecutorFile() {

        try {

            Files.copy(
                Path.of("src/test/resources/allure/executor.json"),
                Path.of("target/allure-results/executor.json"),
                StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
    public static void copyCategoriesFile() {

        try {

            Files.copy(
                Path.of("src/test/resources/allure/categories.json"),
                Path.of("target/allure-results/categories.json"),
                StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}