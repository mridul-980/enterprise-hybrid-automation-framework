package tests.ui.login;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
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

}