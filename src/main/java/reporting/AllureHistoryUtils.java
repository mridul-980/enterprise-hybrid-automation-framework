package reporting;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class AllureHistoryUtils {

	private static final Path HISTORY_SOURCE =
	        Path.of("allure-history", "history");

	private static final Path HISTORY_DESTINATION =
	        Path.of("target", "allure-results", "history");

    public static void restoreHistory() {

        if (!Files.exists(HISTORY_SOURCE)) {

            System.out.println(
                    "No previous Allure history found.");

            return;
        }

        try {

            Files.walk(HISTORY_SOURCE)
                    .forEach(source -> {

                        Path destination =
                                HISTORY_DESTINATION.resolve(
                                        HISTORY_SOURCE.relativize(source));

                        try {

                            if (Files.isDirectory(source)) {

                                Files.createDirectories(destination);

                            } else {

                                Files.copy(
                                        source,
                                        destination,
                                        StandardCopyOption.REPLACE_EXISTING);
                            }

                        } catch (IOException e) {

                            e.printStackTrace();
                        }

                    });

            System.out.println(
                    "Allure history restored successfully.");

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
    public static void saveHistory() {

        Path reportHistory =
                Path.of("target",
                        "allure-report",
                        "history");

        if (!Files.exists(reportHistory)) {

            return;
        }

        try {

            Path historyRoot =
                    Path.of("allure-history");

            Files.createDirectories(historyRoot);

            Files.walk(reportHistory)
                    .forEach(source -> {

                        Path destination =
                                HISTORY_SOURCE.resolve(
                                        reportHistory.relativize(source));

                        try {

                            if (Files.isDirectory(source)) {

                                Files.createDirectories(destination);

                            } else {

                                Files.copy(
                                        source,
                                        destination,
                                        StandardCopyOption.REPLACE_EXISTING);

                            }

                        } catch (IOException e) {

                            throw new RuntimeException(e);

                        }

                    });

            System.out.println(
                    "Allure history saved successfully.");

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

    }

}