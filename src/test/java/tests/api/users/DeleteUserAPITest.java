package tests.api.users;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.specs.ResponseSpecs;
import api.utils.ApiUtils;
import io.restassured.response.Response;

public class DeleteUserAPITest {

    @BeforeClass

    public void setup() {

        ApiUtils.setupBaseURI();
    }

    @Test

    public void verifyDeleteUserAPI() {

        Response response =
                UserEndpoints.deleteUser(1);

        System.out.println(
                response.getStatusCode());

//        Assert.assertEquals(                  // Check the status code of the response
//                response.getStatusCode(), 
//                200);
        
        response.then()
        .spec(ResponseSpecs.deleteResponse());
    }
}