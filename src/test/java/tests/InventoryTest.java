package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import factory.DriverFactory;

public class InventoryTest extends BaseTest {

    @Test

    public void verifyInventoryPageTitle() {

        String title =
                DriverFactory.getDriver().getTitle();

        Assert.assertEquals(
                title,
                "Swag Labs");
    }
}