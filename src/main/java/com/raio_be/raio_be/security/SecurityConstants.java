package com.raio_be.raio_be.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConstants {
    public static final long TOKEN_EXPIRATION = 864_000_000;

    private String secret;

    public SecurityConstants(@Value("${JWT_SECRET_KEY}") String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }
}