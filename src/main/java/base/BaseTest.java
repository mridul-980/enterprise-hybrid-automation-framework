package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import drivers.BrowserManager;
import factory.DriverFactory;
import utilities.ConfigReader;
import utilities.Log;
public class BaseTest {

	@BeforeMethod
	@Parameters("browser")
	public void setup(
	        @Optional("chrome") String browser) {

	    Log.info("Browser setup started");

	    BrowserManager.initializeBrowser(browser);

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