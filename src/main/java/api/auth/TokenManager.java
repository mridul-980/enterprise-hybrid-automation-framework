package api.auth;

public class TokenManager {

    private static String token;

    private TokenManager() {
    }

    public static void setToken(String authToken) {

        token = authToken;
    }

    public static String getToken() {

        return token;
    }

    public static boolean hasToken() {

        return token != null
                && !token.isEmpty();
    }

    public static void clearToken() {

        token = null;
    }
}