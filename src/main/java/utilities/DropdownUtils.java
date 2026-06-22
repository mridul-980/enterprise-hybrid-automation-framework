package utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownUtils {

    public static void selectByVisibleText(
            WebElement element,
            String text) {

        Select select = new Select(element);

        select.selectByVisibleText(text);
    }

    public static void selectByValue(
            WebElement element,
            String value) {

        Select select = new Select(element);

        select.selectByValue(value);
    }
}