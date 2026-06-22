package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import factory.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserManager {

    public static void initializeBrowser(
    		String browser,
    		boolean headless) {
    	
    	WebDriver driver = null;
    	
    	switch (browser.toLowerCase()) {
    	case "chrome":
    		
    		WebDriverManager.chromedriver().setup();
    		
    		ChromeOptions chromeOptions = new ChromeOptions();
    		
    		if (headless) {
    			chromeOptions.addArguments("--headless=new");
    		}
    		driver = new ChromeDriver(chromeOptions);
    		
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