package api.auth;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class AuthSpec {

    public static RequestSpecification getAuthSpec() {

        return new RequestSpecBuilder()

                .setContentType("application/json")

                .addHeader(
                        "Authorization",
                        "Bearer " +
                        TokenManager.getToken())

                .build();
    }
}