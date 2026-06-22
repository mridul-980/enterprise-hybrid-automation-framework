package api.auth;

import utilities.Log;

public class AuthUtils {

    public static void generateToken() {

        String fakeToken =
                "sample_token_123456";

        TokenManager.setToken(fakeToken);

        Log.info(
            "Token generated successfully");
    }
}