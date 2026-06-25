package utilities;

import org.testng.annotations.DataProvider;

public class TestDataProvider {

    @DataProvider(name = "userData")
    public Object[][] getUserData() {

        return new Object[][] {

                {"Mridul", "QA Engineer"},
                {"Rahul", "Automation Engineer"},
                {"Amit", "SDET"}
        };
    }
}