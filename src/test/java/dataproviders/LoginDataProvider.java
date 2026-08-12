package dataproviders;

import org.testng.annotations.DataProvider;

import constants.TestData;
import constants.TestMessages;
import model.LoginTestData;
import utilities.ConfigReader;

public class LoginDataProvider {

	@DataProvider(name = "invalidLoginData")
	public Object[][] invalidLoginData() {

	    return new Object[][] {

	        {
	            new LoginTestData(
	                    "TC_002",
	                    TestData.INVALID_USERNAME,
	                    ConfigReader.getPassword(),
	                    TestMessages.INVALID_CREDENTIALS)
	        },

	        {
	            new LoginTestData(
	                    "TC_003",
	                    ConfigReader.getUsername(),
	                    TestData.INVALID_PASSWORD,
	                    TestMessages.INVALID_CREDENTIALS)
	        },

	        {
	            new LoginTestData(
	                    "TC_004",
	                    TestData.INVALID_USERNAME,
	                    TestData.INVALID_PASSWORD,
	                    TestMessages.INVALID_CREDENTIALS)
	        },

	        {
	            new LoginTestData(
	                    "TC_005",
	                    "",
	                    ConfigReader.getPassword(),
	                    TestMessages.USERNAME_REQUIRED)
	        },

	        {
	            new LoginTestData(
	                    "TC_006",
	                    ConfigReader.getUsername(),
	                    "",
	                    TestMessages.PASSWORD_REQUIRED)
	        },

	        {
	            new LoginTestData(
	                    "TC_007",
	                    "",
	                    "",
	                    TestMessages.USERNAME_REQUIRED)
	        }
	    };
	}
}