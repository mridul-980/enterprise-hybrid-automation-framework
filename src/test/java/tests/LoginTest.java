package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.InventoryPage;
import pages.LoginPage;
import utilities.ConfigReader;

public class LoginTest extends BaseTest {

    @Test 
    public void verifySuccessfulLogin() {

        LoginPage loginPage =
                new LoginPage();

        InventoryPage inventoryPage =
                new InventoryPage();

        loginPage.loginToApplication(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password"));

        String actualText =
                inventoryPage.getProductsPageTitle();

        Assert.assertEquals(
                actualText,
                "Products");
    }
}