package tests.ui.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.TestData;
import constants.TestMessages;
import dataproviders.LoginDataProvider;
import model.LoginTestData;
import pages.InventoryPage;
import pages.LoginPage;
import utilities.ConfigReader;

public class LoginTest extends BaseTest {
	
	@Test(
			description = "Verify user can login with valid credentials",
			groups = {"smoke","regression"}
			)
	
	public void verifyValidLogin() {
		//Arrange
		LoginPage loginPage = new LoginPage(driver);
		
		//Act
		InventoryPage inventoryPage = loginPage.login(
				ConfigReader.getUsername(),
				ConfigReader.getPassword());
		
		//Assert
		Assert.assertTrue(inventoryPage.isInventoryPageDisplayed(), "Inventory page was not displayed after successful login.");
	}
	
	@Test(
	        dataProvider = "invalidLoginData",
	        dataProviderClass = LoginDataProvider.class,
	        description = "Verify invalid login scenarios",
	        groups = {"regression"}
	)
	public void verifyInvalidLogin(final LoginTestData data) {

	    // Arrange
	    LoginPage loginPage = new LoginPage(driver);

	    // Act
	    loginPage.attemptLogin(
	            data.getUsername(),
	            data.getPassword());

	    // Assert
	    Assert.assertTrue(
	            loginPage.hasErrorMessage(),
	            data.getTestCaseId() + " - Error message was not displayed.");

	    Assert.assertEquals(
	            loginPage.getErrorMessage(),
	            data.getExpectedMessage(),
	            data.getTestCaseId() + " failed.");
	}
	
	@Test(
	        description = "Verify locked user cannot login",
	        groups = {"regression"}
	)
	public void verifyLockedUserCannotLogin() {

	    // Arrange
	    LoginPage loginPage = new LoginPage(driver);

	    // Act
	    loginPage.attemptLogin(
	            TestData.LOCKED_USER,
	            ConfigReader.getPassword());

	    // Assert
	    Assert.assertTrue(
	            loginPage.hasErrorMessage(),
	            "Locked user error message was not displayed.");

	    Assert.assertEquals(
	            loginPage.getErrorMessage(),
	            TestMessages.LOCKED_USER,
	            "Incorrect locked user error message.");
	}
	
	@Test(
	        description = "Verify user can logout successfully",
	        groups = {"smoke", "regression"}
	)
	public void verifyLogout() {

	    // Arrange
	    LoginPage loginPage = new LoginPage(driver);

	    // Act
	    InventoryPage inventoryPage =
	            loginPage.login(
	                    ConfigReader.getUsername(),
	                    ConfigReader.getPassword());

	    LoginPage loggedOutPage =
	            inventoryPage.logout();

	    // Assert
	    Assert.assertTrue(
	            loggedOutPage.isLoginButtonDisplayed(),
	            "Login page was not displayed after logout.");
	}
	
	@Test(
	        description = "Verify session is terminated after logout",
	        groups = {"regression"}
	)
	public void verifySessionAfterLogout() {

	    // Arrange
	    LoginPage loginPage = new LoginPage(driver);

	    InventoryPage inventoryPage =
	            loginPage.login(
	                    ConfigReader.getUsername(),
	                    ConfigReader.getPassword());

	    LoginPage loggedOutPage =
	            inventoryPage.logout();

	    // Act
	    loggedOutPage.navigateBack();

	    // Assert
	    Assert.assertTrue(
	            loggedOutPage.isLoginButtonDisplayed(),
	            "User session should not remain active after logout.");
	    Assert.assertFalse(
	    	    inventoryPage.isInventoryPageDisplayed());
	}
}