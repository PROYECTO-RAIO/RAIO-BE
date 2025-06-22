package com.raio_be.raio_be.security.manager;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.raio_be.raio_be.DTO.AdminDTO;
import com.raio_be.raio_be.service.AdminService;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private AdminService adminService;
    private PasswordEncoder passwordEncoder;

    public CustomAuthenticationManager(AdminService adminService, PasswordEncoder passwordEncoder){
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
}
@Override
public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String email = authentication.getName();
    String rawPassword = authentication.getCredentials().toString();

    AdminDTO admin = adminService.getAdminByEmail(email);
    if (!passwordEncoder.matches(rawPassword, admin.getContraseña())) {
        throw new BadCredentialsException("Contraseña incorrecta");
    }

    return new UsernamePasswordAuthenticationToken(
        email,
        admin.getContraseña(),
        Collections.singletonList(new SimpleGrantedAuthority("USER"))
    );
}
}