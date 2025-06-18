package com.raio_be.raio_be.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

@Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain)
        throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    System.out.println("JWT Filter: Checking Authorization header...");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
        final String jwt = authHeader.substring(7);
        System.out.println("JWT Filter: Found Bearer token");

        if (jwtUtil.validateToken(jwt)) {
            System.out.println("JWT Filter: Token is valid");

            String email = jwtUtil.extractEmail(jwt);
            System.out.println("JWT Filter: Extracted email: " + email);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("JWT Filter: Authentication set for user: " + email);
        } else {
            System.out.println("JWT Filter: Invalid token");
        }
    } else {
        System.out.println("JWT Filter: No Authorization header or wrong format");
    }

    filterChain.doFilter(request, response); 
}
}

