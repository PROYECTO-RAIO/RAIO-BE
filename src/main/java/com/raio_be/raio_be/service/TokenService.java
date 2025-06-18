package com.raio_be.raio_be.service;

import java.util.Date;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    private final String SECRET_KEY = "raio_super_secret_key"; 

    public String generateToken(String email) {
        long expirationTimeMs = 1000 * 60 * 60 * 24; 

        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMs))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }
}