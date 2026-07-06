package reporting;

import java.io.IOException;

public class ReportGenerator {

    public static void generateAllureReport() {

        restoreHistory();

        generateReport();

        saveHistory();

        openReport();
    }

    private static void restoreHistory() {

        AllureHistoryUtils.restoreHistory();
    }

    private static void saveHistory() {

        AllureHistoryUtils.saveHistory();
    }

    private static void generateReport() {

        runCommand(
                "allure generate target/allure-results --clean -o target/allure-report");
    }

    private static void openReport() {

        runCommand(
                "allure open target/allure-report");
    }

    private static void runCommand(String command) {

        try {

            ProcessBuilder builder =
                    new ProcessBuilder(
                            "cmd",
                            "/c",
                            command);

            builder.inheritIO();

            Process process = builder.start();

            int exitCode = process.waitFor();

            if (exitCode == 0) {

                System.out.println(
                        "Command executed successfully.");

            } else {

                throw new RuntimeException(
                        "Command failed with exit code: "
                                + exitCode);
            }

        } catch (IOException | InterruptedException e) {

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }

            throw new RuntimeException(
                    "Failed to execute command: "
                            + command,
                    e);
        }
    }
}