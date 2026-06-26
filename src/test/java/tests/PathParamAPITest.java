package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.utils.ApiUtils;
import io.restassured.response.Response;
import utilities.ApiValidator;

public class PathParamAPITest {

    @BeforeClass
    public void setup() {
        ApiUtils.setupBaseURI();
    }

    @Test
    public void verifyPathParamUserAPI() {

        Response response =
                UserEndpoints.getUser(1);

        System.out.println(
                response.getBody()
                        .asPrettyString());

        ApiValidator.validateSuccessResponse(
                response,
                "user-schema.json");

        Assert.assertEquals(
                response.jsonPath()
                        .getInt("id"),
                1);
    }
}