package pages;

import org.openqa.selenium.By;

import factory.DriverFactory;
import utilities.Log;

public class LoginPage {

    // Locators

    private By usernameField =
            By.id("user-name");

    private By passwordField =
            By.id("password");

    private By loginButton =
            By.id("login-button");

    // Actions

    public void enterUsername(String username) {

        DriverFactory.getDriver()
                .findElement(usernameField)
                .sendKeys(username);
    }

    public void enterPassword(String password) {

        DriverFactory.getDriver()
                .findElement(passwordField)
                .sendKeys(password);
    }

    public void clickLoginButton() {

        DriverFactory.getDriver()
                .findElement(loginButton)
                .click();
    }

    public void loginToApplication(
            String username,
            String password) {
    	
    	Log.info("Entering username");

        enterUsername(username);
        
        Log.info("Entering password");

        enterPassword(password);

        clickLoginButton();
        
        Log.info("Login operation completed");
    }
}