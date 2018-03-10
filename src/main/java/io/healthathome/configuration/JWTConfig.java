package io.healthathome.configuration;

public class JWTConfig {

    public static final String SECRET = "HealthAtHomeSecret";
    public static final long EXPIRATION_TIME = 864_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
