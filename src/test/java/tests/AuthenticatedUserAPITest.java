package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.auth.AuthUtils;
import api.endpoints.UserEndpoints;
import api.utils.ApiUtils;
import io.restassured.response.Response;

public class AuthenticatedUserAPITest {

    @BeforeClass

    public void setup() {

        ApiUtils.setupBaseURI();

        AuthUtils.generateToken();
    }

    @Test

    public void verifyAuthenticatedUserAPI() {

        Response response =
                UserEndpoints
                    .getUserWithAuth(1);

        Assert.assertEquals(
                response.getStatusCode(),
                200);
    }
}