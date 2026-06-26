package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.models.UserPayload;
import api.utils.ApiUtils;
import io.restassured.response.Response;
import utilities.ApiValidator;
import utilities.TestDataProvider;

public class CreateUserAPITest {

	@BeforeClass

	public void setup() {

		ApiUtils.setupBaseURI();
	}

	@Test(
		    dataProvider = "userData",
		    dataProviderClass = TestDataProvider.class    // Use the TestDataProvider class to provide test data for the test method
		)
		public void verifyCreateUserAPI(
		        String name,
		        String job) {

		UserPayload payload = new UserPayload();
/*
		payload.setName("Mridul");       // Set the name of the user in the payload
		payload.setJob("QA Engineer");   // Set the job of the user in the payload
*/
		
		payload.setName(name);          // Set the name of the user in the payload using the parameter from the data provider
		payload.setJob(job);			// Set the job of the user in the payload using the parameter from the data provider
		
		Response response = UserEndpoints.createUser(payload);// Call the createUser() method from UserEndpoints to send a POST request to create a new user with the specified payload
		System.out.println(response.getBody().asPrettyString());
	/*	
		response.then().spec(ResponseSpecs.createResponse());  // Use the createResponse() method from ResponseSpecs to validate the response

		SchemaUtils.validateSchema(response, "create-user-schema.json"); // Validate the response against the create-user-schema.json schema
   */
		ApiValidator.validateCreateResponse(
		        response,
		        "create-user-schema.json");   // Validate the response using the ApiValidator utility class

//        Assert.assertEquals(
//                response.getStatusCode(), // Check the status code of the response
//                201);
//		Assert.assertEquals(response.jsonPath().getString("name"), "Mridul");    // Check if the name in the response matches the expected value
		
		Assert.assertEquals(
		        response.jsonPath().getString("name"),           // Check if the name in the response matches the expected value
		        name);

		Assert.assertEquals(
		        response.jsonPath().getString("job"),           // Check if the job in the response matches the expected value
		        job);
	}
}