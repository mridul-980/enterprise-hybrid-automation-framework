package api.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/*
 * This class defines the base request specification for API tests.
 * It sets the content type to "application/json" for all requests.
 * This promotes reusability and maintainability by centralizing common request configurations.
 IMPORTANT CONCEPT
RequestSpecification

Stores:
✅ headers
✅ content-type
✅ auth
✅ request config

centrally.
 */
// Now framework automatically logs:
//✅ request headers
//✅ request URI
//✅ payloads
//✅ responses
//Excellent for debugging.

public class BaseSpec {

	public static RequestSpecification getRequestSpec() {

	    return new RequestSpecBuilder()

	            .setContentType("application/json")

	            .log(io.restassured.filter.log.LogDetail.ALL)

	            .build();
	}
}