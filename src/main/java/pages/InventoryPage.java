package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage extends BasePage {
 
	private static final By PRODUCTS_TITLE =
	        By.cssSelector(".title");
	public boolean isInventoryPageDisplayed() {
	    return isDisplayed(PRODUCTS_TITLE);
	}
	
    public InventoryPage(final WebDriver driver) {
        super(driver);
    }

}