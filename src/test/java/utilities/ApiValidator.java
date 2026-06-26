package utilities;

import api.specs.ResponseSpecs;
import io.restassured.response.Response;

public class ApiValidator {

    public static void validateSuccessResponse(
            Response response,
            String schemaName) {

        response.then()
                .spec(ResponseSpecs.successResponse());

        SchemaUtils.validateSchema(
                response,
                schemaName);
    }

    public static void validateCreateResponse(
            Response response,
            String schemaName) {

        response.then()
                .spec(ResponseSpecs.createResponse());

        SchemaUtils.validateSchema(
                response,
                schemaName);
    }
}