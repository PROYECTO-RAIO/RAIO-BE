package com.raio_be.raio_be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordEncoderConfig {

@Bean
PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
}