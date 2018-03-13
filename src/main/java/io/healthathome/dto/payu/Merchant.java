package io.healthathome.dto.payu;

public class Merchant {
    private String apiKey;
    private String apiLogin;

    public Merchant(String apiKey, String apiLogin) {
        this.apiKey = apiKey;
        this.apiLogin = apiLogin;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiLogin() {
        return apiLogin;
    }
}
