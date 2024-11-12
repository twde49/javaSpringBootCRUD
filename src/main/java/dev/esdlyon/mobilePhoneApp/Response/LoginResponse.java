package dev.esdlyon.mobilePhoneApp.Response;

public class LoginResponse {
    private String token;

    private long expiresIn;

    public String getToken() {
        return token;
    }

    public Object setToken(String jwtToken) {
        token = jwtToken;
        return token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expirationTime) {
        expiresIn = expirationTime;
    }
}