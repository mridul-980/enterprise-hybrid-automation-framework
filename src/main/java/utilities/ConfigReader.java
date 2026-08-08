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
        System.out.println(filePath);

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
    /**
     * Returns the application URL.
     *
     * @return application base URL
     */
    public static String getBaseUrl() {
        return getProperty("url");
    }

    /**
     * Returns the default username.
     *
     * @return application username
     */
    public static String getUsername() {
        return getProperty("username");
    }

    /**
     * Returns the default password.
     *
     * @return application password
     */
    public static String getPassword() {
        return getProperty("password");
    }

	public static String getBrowser() {
	    return getProperty("browser");
	}

	public static String getExecutionType() {
	    return getProperty("execution.type");
	}

	public static boolean isHeadless() {
	    return Boolean.parseBoolean(getProperty("headless"));
	}

	public static int getExplicitWait() {
	    String value = getProperty("explicit.wait");

	    if (value == null) {
	        throw new IllegalStateException(
	            "Property 'explicit.wait' is missing in the configuration file.");
	    }

	    return Integer.parseInt(value);
	}
	

}