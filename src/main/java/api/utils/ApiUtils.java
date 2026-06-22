package api.utils;

import io.restassured.RestAssured;
import utilities.ConfigReader;

public class ApiUtils {

    public static void setupBaseURI() {
// It's a good practice to externalize the base URI in a configuration file or environment variable for better maintainability and flexibility.
//        RestAssured.baseURI =
//                "https://jsonplaceholder.typicode.com";
    	
    	
    	// Read the base URI from a configuration file using a utility class like ConfigReader
    	RestAssured.baseURI =

    	        ConfigReader
    	                .getProperty(
    	                        "api.base.url");
    }
}