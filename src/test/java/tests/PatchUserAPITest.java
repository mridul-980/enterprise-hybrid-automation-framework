package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.utils.ApiUtils;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class PatchUserAPITest {

    @BeforeClass
    public void setup() {

        ApiUtils.setupBaseURI();
    }

    @Test
    public void verifyPatchUserAPI() {

        Map<String, Object> payload =
                new HashMap<>();

        payload.put(
                "job",
                "Lead QA Engineer");

        Response response =
                UserEndpoints.patchUser(
                        1,
                        payload);

        System.out.println(
                response.getBody()
                        .asPrettyString());

        // Response Validation
//        response.then()
//                .spec(ResponseSpecs.successResponse());
//
//        // Schema Validation
//        SchemaUtils.validateSchema(
//                response,
//                "patch-user-schema.json");
        
        ApiValidator.validateSuccessResponse(
                response,
                "patch-user-schema.json");  // Validate the response using the ApiValidator utility class

        // Business Validation
        response.then()
        .body(
                "id",
                equalTo(1))
        .body(
                "job",
                equalTo("Lead QA Engineer"));
    }
}