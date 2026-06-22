package utilities;

public class RuntimeConfig {

    public static String getBrowser() {

        String browser =
                System.getProperty("browser");

        if (browser != null &&
                !browser.isEmpty()) {

            return browser;
        }

        return ConfigReader.getProperty("browser");
    }

    public static boolean isHeadless() {

        String headless =
                System.getProperty("headless");

        if (headless != null &&
                !headless.isEmpty()) {

            return Boolean.parseBoolean(headless);
        }

        return Boolean.parseBoolean(
                ConfigReader.getProperty("headless"));
    }
}