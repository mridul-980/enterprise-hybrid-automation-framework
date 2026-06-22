package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.models.UserPayload;
import api.utils.ApiUtils;
import io.restassured.response.Response;

public class CreateUserAPITest {

    @BeforeClass

    public void setup() {

        ApiUtils.setupBaseURI();
    }

    @Test

    public void verifyCreateUserAPI() {

        UserPayload payload =
                new UserPayload();

        payload.setName("Mridul");

        payload.setJob("QA Engineer");

        Response response =
                UserEndpoints.createUser(payload);

        System.out.println(
                response.getBody().asPrettyString());

        Assert.assertEquals(
                response.getStatusCode(),
                201);

        Assert.assertEquals(
                response.jsonPath()
                        .getString("name"),
                "Mridul");
    }
}