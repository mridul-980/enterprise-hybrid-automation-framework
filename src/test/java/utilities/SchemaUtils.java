package utilities;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.response.Response;

public class SchemaUtils {

    public static void validateSchema(
            Response response,
            String schemaName) {

        response.then()
                .assertThat()
                .body(
                        matchesJsonSchemaInClasspath(
                                "schemas/" + schemaName));
    }
}