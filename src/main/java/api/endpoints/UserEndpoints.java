package api.endpoints;

import static io.restassured.RestAssured.given;

import api.models.UserPayload;
import api.specs.BaseSpec;
import io.restassured.response.Response;

// This class defines the API endpoints related to user operations.
// Request now uses centralized specification from BaseSpec, which promotes reusability and maintainability.

public class UserEndpoints {

    public static Response getUser(int userId) {

        return

                given()

                .spec(BaseSpec.getRequestSpec())

                .when()

                .get("/users/" + userId);
    }
    
    public static Response createUser(
            UserPayload payload) {

        return

                given()

                .spec(BaseSpec.getRequestSpec())

                .body(payload)  // Payload is now passed as an object, and RestAssured will handle serialization.

                .when()

                .post("/users");
    }
    
    public static Response getUserWithAuth(
            int userId) {

        return

                given()

                .spec(
                    api.auth.AuthSpec
                        .getAuthSpec())

                .when()

                .get("/users/" + userId);
    }
}