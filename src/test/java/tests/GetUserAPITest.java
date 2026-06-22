package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.models.UserResponse;
import api.utils.ApiUtils;
import io.restassured.response.Response;

public class GetUserAPITest {

    @BeforeClass

    public void setup() {

        ApiUtils.setupBaseURI();
    }

    @Test

    public void verifyGetUserAPI() {

//        Response response =
//                UserEndpoints.getUser(1);
    	Response response =
    	        UserEndpoints.getUser(1);

    	UserResponse user =
    	        response.as(UserResponse.class); // Deserialize the JSON response into a UserResponse object
    	
    	// Assert top-level fields
    	Assert.assertEquals(user.getId(), 1, "User ID mismatch");
    	Assert.assertEquals(user.getUsername(), "Bret", "Username mismatch");
    	Assert.assertEquals(user.getEmail(), "Sincere@april.biz", "Email mismatch");

    	// Assert nested Address fields
    	Assert.assertEquals(user.getAddress().getCity(), "Gwenborough", "City mismatch");
    	Assert.assertEquals(user.getAddress().getStreet(), "Kulas Light", "Street mismatch");

    	// Assert nested Company fields
    	Assert.assertEquals(user.getCompany().getName(), "Romaguera-Crona", "Company name mismatch");
    	Assert.assertEquals(user.getCompany().getCatchPhrase(), "Multi-layered client-server neural-net", "CatchPhrase mismatch");
    	
    	// Print the user details to verify the deserialization
    	System.out.println("ID = " + user.getId());

    	System.out.println("Name = " + user.getName());

    	System.out.println("Username = " + user.getUsername());

    	System.out.println("Email = " + user.getEmail());

        System.out.println(
                response.getBody().asPrettyString());
        
        // Access nested properties
        System.out.println(
                user.getAddress().getCity());

        System.out.println(
                user.getAddress().getStreet());

        System.out.println(
                user.getCompany().getName());

        // Assertions to validate the response
        Assert.assertEquals(
                user.getId(),
                1);

        Assert.assertEquals(
                user.getUsername(),
                "Bret");
    }
}
//Endpoint Layer = API logic
//Test Layer = validations