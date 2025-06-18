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

    public SecurityConfig(CustomAuthenticationManager customAuthenticationManager){
        this.customAuthenticationManager = customAuthenticationManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //TRIAL - Remove all atuth
        return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
        .build();

        // JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(customAuthenticationManager);
        // authenticationFilter.setFilterProcessesUrl("/api/v1/admins/login");

        // http
        //     .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
        //     .csrf(csrf -> csrf.disable())
            // .authorizeHttpRequests(request -> request
            //     //Temporarily allow post new admin - ELIMINAR MÁS TARDE
            //      .requestMatchers(HttpMethod.POST, "/api/v1/admins").permitAll()
            //      .requestMatchers(HttpMethod.POST, "/api/v1/admins/**").permitAll()

            //     // H2 Console
            //     .requestMatchers("/h2/**").permitAll()

            //     // Public GET endpoints
            //     .requestMatchers(HttpMethod.GET, "/api/v1/categorias/**").permitAll()

            //     // Public login endpoint
            //     .requestMatchers(HttpMethod.POST, "/api/v1/admins/login").permitAll()

            //     // Any other request (including POST/PUT/DELETE) requires authentication
            //     .anyRequest().authenticated()
    //         )
    //         .addFilter(authenticationFilter)
    //         .addFilterAfter(new JWTAuthorizationFilter(), JWTAuthenticationFilter.class)
    //         .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    // return http.build();    
    // }
}
}