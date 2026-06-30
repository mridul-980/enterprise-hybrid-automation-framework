package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import factory.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserManager {

    public static void initializeBrowser(
    		String browser,
    		boolean headless) {
    	
    	WebDriver driver = null;
    	
    	switch (browser.toLowerCase()) {
    	case "chrome":

    	    ChromeOptions chromeOptions = new ChromeOptions();

    	    if (headless) {
    	        chromeOptions.addArguments("--headless=new");
    	    }

    	    String execution = ConfigReader.getProperty("execution");

    	    String remoteUrl = ConfigReader.getProperty("remoteUrl");

    	    if ("remote".equalsIgnoreCase(execution)) {

    	        try {

    	            driver = new RemoteWebDriver(
    	                    new URL(remoteUrl),
    	                    chromeOptions);

    	        } catch (MalformedURLException e) {

    	            throw new RuntimeException(e);
    	        }

    	    } else {

    	        WebDriverManager.chromedriver().setup();

    	        driver = new ChromeDriver(chromeOptions);
    	    }

    	    break;
    		
    	case "firefox":

            WebDriverManager.firefoxdriver().setup();

            driver = new FirefoxDriver();

            break;
    		
    	case "edge":
    		
    		WebDriverManager.edgedriver().setup();
    		
    		driver = new EdgeDriver();
    		
    		break;
    		
    		default:
    			
    			throw new IllegalArgumentException(
    					"Invalid Browser: "+ browser);
    			
    	}
    	
    	driver.manage().window().maximize();
    	
    	DriverFactory.setDriver(driver);

      
    }
}