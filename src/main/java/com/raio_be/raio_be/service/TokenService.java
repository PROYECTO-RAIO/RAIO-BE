package com.raio_be.raio_be.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private static final String JWT_SECRET_KEY = "raio_secret_key"; 
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; 

    public String generateToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JWT_SECRET_KEY));
    }

    public String getEmailFromToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC512(JWT_SECRET_KEY)).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        return decodedJWT.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(JWT_SECRET_KEY)).build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
}