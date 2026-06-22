package pages;

import org.openqa.selenium.By;

import factory.DriverFactory;

public class InventoryPage {

    private By productsTitle =
            By.className("title");

    public String getProductsPageTitle() {

        return DriverFactory.getDriver()
                .findElement(productsTitle)
                .getText();
    }
}