package com.raio_be.raio_be.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.raio_be.raio_be.security.filter.JWTAuthenticationFilter;
import com.raio_be.raio_be.security.filter.JWTAuthorizationFilter;
import com.raio_be.raio_be.security.manager.CustomAuthenticationManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;
    private final SecurityConstants securityConstants;

    public SecurityConfig(CustomAuthenticationManager customAuthenticationManager, SecurityConstants securityConstants){
        this.customAuthenticationManager = customAuthenticationManager;
        this.securityConstants = securityConstants;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(customAuthenticationManager, securityConstants);
        authenticationFilter.setFilterProcessesUrl("/api/v1/admin/login");

        http
            .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
            .cors(cors -> {})
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(request -> request

                .requestMatchers("/h2/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/v1/categorias/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/admins/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/v1/admins").permitAll() 

                .requestMatchers("/api/v1/mensajes-originales/**").permitAll()
                .requestMatchers("/api/v1/mensajes-reverberados/**").permitAll()

                .requestMatchers(HttpMethod.POST, "/api/v1/categorias").authenticated()
                .requestMatchers(HttpMethod.PUT, "/api/v1/categorias/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/categorias/**").authenticated()

                .anyRequest().permitAll()
            )
            .addFilter(authenticationFilter)
            .addFilterAfter(new JWTAuthorizationFilter(securityConstants), JWTAuthenticationFilter.class)
            .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}