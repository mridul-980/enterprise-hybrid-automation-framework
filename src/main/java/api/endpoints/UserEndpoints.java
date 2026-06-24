package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.Map;

import api.models.UserPayload;
import api.specs.BaseSpec;
import io.restassured.response.Response;

// This class defines the API endpoints related to user operations.
// Request now uses centralized specification from BaseSpec, which promotes reusability and maintainability.

public class UserEndpoints {

    public static Response getUser(int userId) {    // Method to get a user by ID using GET request

        return

                given()

                .spec(BaseSpec.getRequestSpec())

                .when()

                .get("/users/" + userId);
    }
    
    public static Response createUser(      // Method to create a new user using POST request
            UserPayload payload) {

        return

                given()

                .spec(BaseSpec.getRequestSpec())

                .body(payload)  // Payload is now passed as an object, and RestAssured will handle serialization.

                .when()

                .post("/users");
    }
    
    public static Response getUserWithAuth(     // Method to get a user with authentication using GET request
            int userId) {

        return

                given()

                .spec(
                    api.auth.AuthSpec
                        .getAuthSpec())

                .when()

                .get("/users/" + userId);
    }
    
    public static Response updateUser(          // Method to update a user using PUT request
            int userId,
            UserPayload payload) {

        return

                given()

                .spec(BaseSpec.getRequestSpec())

                .body(payload)

                .when()

                .put("/users/" + userId);
    }
    public static Response deleteUser(        // Method to delete a user using DELETE request
            int userId) {

        return

                given()

                .spec(BaseSpec.getRequestSpec())

                .when()

                .delete("/users/" + userId);
    }
    public static Response patchUser(        // Method to partially update a user using PATCH request
            int userId,
            Map<String, Object> payload) {

        return given()
                .spec(BaseSpec.getRequestSpec())
                .body(payload)
                .when()
                .patch("/users/" + userId);
    }
}