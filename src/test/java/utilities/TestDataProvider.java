package utilities;

import java.util.List;

import org.testng.annotations.DataProvider;

import testdata.UserTestData;

public class TestDataProvider {

    @DataProvider(name = "userData")            // Define a data provider named "userData" that provides test data for the test method
    
    public Object[][] getUserData() {
        List<UserTestData> users =
                JsonDataReader.readUsers("users.json");   // Read the test data from the users.json file using the JsonDataReader utility class

        Object[][] data =
                new Object[users.size()][2];

        for (int i = 0; i < users.size(); i++) {          // Loop through the list of users and populate the 2D array with the name and job of each user

            data[i][0] = users.get(i).getName();          // Set the name of the user in the first column of the 2D array
            data[i][1] = users.get(i).getJob();			  // Set the job of the user in the second column of the 2D array
        }

        return data;

   /*     return new Object[][] {					// Return a 2D array of test data for the test method

                {"Mridul", "QA Engineer"},
                {"Rahul", "Automation Engineer"}, // Provide test data for the test method
                {"Amit", "SDET"}   */
        };
    }
