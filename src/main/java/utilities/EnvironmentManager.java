package utilities;

public class EnvironmentManager {

    public static String getEnvironment() {

        String env =
                System.getProperty("env");

        if (env == null) {

            env = "qa";
        }

        return env;
    }
}