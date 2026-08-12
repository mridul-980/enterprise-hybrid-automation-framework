package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
	
	private static final By USERNAME_TEXTBOX = By.id("user-name");
	
	private static final By PASSWORD_TEXTBOX = By.id("password");
	
	private static final By LOGIN_BUTTON = By.id("login-button");
	
	private static final By ERROR_MESSAGE = By.cssSelector("h3[data-test='error']");
	
	
	public LoginPage(final WebDriver driver) {
		super(driver);
	}
	
	public void enterUsername(final String username) {
		clearAndType(USERNAME_TEXTBOX, username);
	}
	
	public void enterPassword(final String password) {
		clearAndType(PASSWORD_TEXTBOX, password);
	}
	
	public void clickLogin() {
		click(LOGIN_BUTTON);
	}
	
	/**
	 * Performs login using valid user credentials.
	 *
	 * @param username application username
	 * @param password application password
	 * @return InventoryPage after successful login
	 */
	
	public InventoryPage login(
	        final String username,
	        final String password) {

	    performLogin(username, password);

	    return new InventoryPage(driver);
	}
	/**
	 * Performs the login action.
	 *
	 * @param username application username
	 * @param password application password
	 */
	private void performLogin(
	        final String username,
	        final String password) {

	    enterUsername(username);
	    enterPassword(password);
	    clickLogin();
	}
	
	/**
	 * Attempts to log in using the supplied credentials.
	 * This method is intended for negative login scenarios
	 * where the user remains on the Login page.
	 *
	 * @param username application username
	 * @param password application password
	 * @return current LoginPage instance
	 */
	
	public LoginPage attemptLogin(
	        final String username,
	        final String password) {

	    performLogin(username, password);

	    return this;
	}
	
	public boolean hasErrorMessage() {
	    return isDisplayed(ERROR_MESSAGE);
	}
	
	public String getErrorMessage() {
	    return getText(ERROR_MESSAGE);
	}
	
	public boolean isLoginButtonDisplayed() {

	    return isDisplayed(LOGIN_BUTTON);
	}
	
	
	 // Locators

    // Constructor

    // Actions

    // Business Methods

    // Validation Methods
}
