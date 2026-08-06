package tests.api.users;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.utils.ApiUtils;
import io.restassured.response.Response;

public class QueryParamAPITest {

    @BeforeClass
    public void setup() {

        ApiUtils.setupBaseURI();
    }

    @Test
    public void verifyQueryParamAPI() {

        Response response =
                UserEndpoints.getUsersByPage(1);

        System.out.println(
                response.getBody()
                        .asPrettyString());
    }
}