package api.endpoints;

import static io.restassured.RestAssured.given;

import api.specs.BaseSpec;
import io.restassured.response.Response;

public class AuthEndpoints {

    public static Response login(String username,
                                 String password) {

        String payload = "{"
                + "\"username\":\"" + username + "\","
                + "\"password\":\"" + password + "\""
                + "}";

        return

                given()

                .spec(BaseSpec.getRequestSpec())

                .body(payload)

                .when()

                .post("/auth/login");
    }
}