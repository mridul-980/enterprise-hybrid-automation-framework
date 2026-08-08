package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import drivers.BrowserManager;
import factory.DriverFactory;
import utilities.ConfigReader;
import utilities.Log;
public class BaseTest {

	protected WebDriver driver;
	@BeforeMethod
	@Parameters("browser")
	public void setUp(
	        @Optional("chrome") String browser) {

	    Log.info("Browser setup started");

	    BrowserManager.initializeBrowser(browser);

	    driver = DriverFactory.getDriver();
	    driver.get(ConfigReader.getBaseUrl());

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