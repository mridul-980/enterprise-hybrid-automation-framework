package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.models.UserPayload;
import api.specs.ResponseSpecs;
import api.utils.ApiUtils;
import io.restassured.response.Response;

public class CreateUserAPITest {

	@BeforeClass

	public void setup() {

		ApiUtils.setupBaseURI();
	}

	@Test

	public void verifyCreateUserAPI() {

		UserPayload payload = new UserPayload();

		payload.setName("Mridul");

		payload.setJob("QA Engineer");

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
		Assert.assertEquals(response.jsonPath().getString("name"), "Mridul");
	}
}