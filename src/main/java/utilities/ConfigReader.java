package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {

        properties = new Properties();
// it determines the environment (e.g., qa, staging, production) by calling EnvironmentManager.getEnvironment(), constructs the file path to the corresponding properties file, and loads the properties from that file into the Properties object. This allows for environment-specific configurations to be easily managed and accessed throughout the framework.
        String environment =
                EnvironmentManager
                        .getEnvironment();

        String filePath =

                "src/test/resources/config/"
                        + environment
                        + ".properties";

        try {

            FileInputStream fis =
                    new FileInputStream(filePath);

            properties.load(fis);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {

        String systemValue = System.getProperty(key);

        if (systemValue != null) {
            return systemValue;
        }

        return properties.getProperty(key);
    }
}