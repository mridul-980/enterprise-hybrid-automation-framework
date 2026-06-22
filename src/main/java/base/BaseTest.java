package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import drivers.BrowserManager;
import factory.DriverFactory;
import utilities.ConfigReader;
import utilities.Log;
import utilities.RuntimeConfig;
public class BaseTest {

	@BeforeMethod
	public void setup() {

	    Log.info("Browser setup started");

	    BrowserManager.initializeBrowser(
	            RuntimeConfig.getBrowser(),
	            RuntimeConfig.isHeadless());

	    DriverFactory.getDriver()
	            .get(ConfigReader.getProperty("url"));

	    Log.info("Application launched successfully");
	}
	
    @AfterMethod

    public void tearDown() {

        if (DriverFactory.getDriver() != null) {
        	
        	Log.info("Closing browser");

            DriverFactory.getDriver().quit();

            DriverFactory.unload();
            
            Log.info("Browser closed successfully");
        }
    }
}