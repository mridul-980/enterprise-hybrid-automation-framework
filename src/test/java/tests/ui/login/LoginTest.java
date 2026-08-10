package tests.ui.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import constants.TestData;
import constants.TestMessages;
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
	        description = "Verify login with invalid username",
	        groups = {"regression"}
	)
	public void verifyLoginWithInvalidUsername() {

	    // Arrange
	    LoginPage loginPage = new LoginPage(driver);

	    // Act
	    loginPage.attemptLogin(
	            TestData.INVALID_USERNAME,
	            ConfigReader.getPassword());

	    // Assert
	    Assert.assertTrue(
	            loginPage.isErrorDisplayed(),
	            "Error message should be displayed.");

	    Assert.assertTrue(loginPage.hasErrorMessage());
	}
	
	@Test(
	        description = "Verify login with invalid password",
	        groups = {"regression"}
	)
	public void verifyLoginWithInvalidPassword() {

	    // Arrange
	    LoginPage loginPage = new LoginPage(driver);

	    // Act
	    loginPage.attemptLogin(
	            ConfigReader.getUsername(),
	            TestData.INVALID_PASSWORD);

	    // Assert
	    Assert.assertTrue(
	            loginPage.isErrorDisplayed(),
	            "Error message should be displayed.");

	    Assert.assertTrue(loginPage.hasErrorMessage());
	}
	
	@Test( 
			description = "Verfiy login with invalid username and password",
	        groups = {"regression"}
	)
	public void verifyLoginWithInvalidUsernameAndPassword()
	{
	    // Arrange
	    LoginPage loginPage = new LoginPage(driver);

	    // Act
	    loginPage.attemptLogin(
	            TestData.INVALID_USERNAME,
	            TestData.INVALID_PASSWORD);

	    // Assert
	    Assert.assertTrue(
	            loginPage.isErrorDisplayed(),
	            "Error message should be displayed.");

	    Assert.assertTrue(loginPage.hasErrorMessage());
	}
	
	@Test ( description = "Verify login with empty username",
				        groups = {"regression"}
	)
	public void verifyLoginWithEmptyUsername()
	{
	    // Arrange
	    LoginPage loginPage = new LoginPage(driver);

	    // Act
	    loginPage.attemptLogin(
	            "",
	            ConfigReader.getPassword());

	    // Assert
	    Assert.assertTrue(
	            loginPage.isErrorDisplayed(),
	            "Error message should be displayed.");

	    Assert.assertTrue(loginPage.hasErrorMessage());
	}
	
	@Test ( description = "Verify login with empty password",
				        groups = {"regression"}
	)
	public void verifyLoginWithEmptyPassword()
	{
	    // Arrange
	    LoginPage loginPage = new LoginPage(driver);

	    // Act
	    loginPage.attemptLogin(
	            ConfigReader.getUsername(),
	            "");

	    // Assert
	    Assert.assertTrue(
	            loginPage.isErrorDisplayed(),
	            "Error message should be displayed.");

	    Assert.assertTrue(loginPage.hasErrorMessage());
	}

}