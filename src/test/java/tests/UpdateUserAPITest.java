package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.models.UserPayload;
import api.utils.ApiUtils;
import io.restassured.response.Response;

public class UpdateUserAPITest {

	@BeforeClass

	public void setup() {

		ApiUtils.setupBaseURI();
	}

	@Test

	public void verifyUpdateUserAPI() {

		UserPayload payload = new UserPayload();

		payload.setName("Mridul Tripathi");

		payload.setJob("Senior QA Engineer");

		Response response = UserEndpoints.updateUser(1, payload);

		System.out.println(response.getBody().asPrettyString());

//        Assert.assertEquals( response.getStatusCode(), 200);             // Check the status code of the response  

	/*
		response.then() // Use the successResponse() method from ResponseSpecs to validate the response
				.spec(ResponseSpecs.successResponse());

		SchemaUtils.validateSchema(response, "update-user-schema.json"); // Validate the response against the
																			// update-user-schema.json schema
*/
		ApiValidator.validateSuccessResponse(
		        response,
		        "update-user-schema.json");  // Validate the response using the ApiValidator utility class
//		   response.then()
//           .body(
//                   "name",
//                   equalTo("Mridul Tripathi"))
//           .body(
//                   "job",
//                   equalTo("Senior QA Engineer"));
		Assert.assertEquals(response.jsonPath().getString("name"), "Mridul Tripathi");
	}
}