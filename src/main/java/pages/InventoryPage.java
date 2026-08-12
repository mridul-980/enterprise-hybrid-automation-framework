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
    
    private static final By MENU_BUTTON =
            By.id("react-burger-menu-btn");

    private static final By LOGOUT_LINK =
            By.id("logout_sidebar_link");
    
    public LoginPage logout() {

        click(MENU_BUTTON);

        waitForVisibility(LOGOUT_LINK);

        click(LOGOUT_LINK);

//        System.out.println("Current URL: " + driver.getCurrentUrl());
//        System.out.println("Page Title: " + driver.getTitle());

        return new LoginPage(driver);
    }

}