package com.raio_be.raio_be.security;

import org.springframework.stereotype.Component;

import io.github.cdimascio.dotenv.Dotenv;

@Component
public class SecurityConstants {
    public static final long TOKEN_EXPIRATION = 864_000_000;
    private String secret;

    public SecurityConstants() {
        Dotenv dotenv = Dotenv.configure().load();
        this.secret = dotenv.get("JWT_SECRET_KEY");
    }

    public String getSecret() {
        return secret;
    }
}