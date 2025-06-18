package com.raio_be.raio_be.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raio_be.raio_be.DTO.AdminDTO;
import com.raio_be.raio_be.model.Admin;
import com.raio_be.raio_be.repository.AdminRepository;
import com.raio_be.raio_be.service.AdminService;
import com.raio_be.raio_be.service.TokenService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("api/v1/admins")
@CrossOrigin(origins = "*")

public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public AdminDTO createAdmin(@Valid @RequestBody AdminDTO adminDTO) {
        String rawPassword = adminDTO.getContraseña();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        adminDTO.setContraseña(encodedPassword);
        return adminService.createAdmin(adminDTO);
    }

    @GetMapping
    public List<AdminDTO> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public AdminDTO getAdminById(@PathVariable Integer id) {
        return adminService.getAdminById(id);
    }

    @PutMapping("/{id}")
    public AdminDTO updateAdmin(@PathVariable Integer id, @RequestBody AdminDTO adminDTO) {
        return adminService.updateAdmin(id, adminDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Integer id) {
        adminService.deleteAdmin(id);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Admin loginData) {
        Optional<Admin> optionalAdmin = adminRepository.findByEmail(loginData.getEmail());

        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            
            if (passwordEncoder.matches(loginData.getContraseña(), admin.getContraseña())
            || admin.getContraseña().equals(loginData.getContraseña())) {
            
                String token = tokenService.generateToken(admin.getEmail());
            return Map.of(
                "message", "Login exitoso",
                "token", token
            );
        } else {
            return Map.of("error", "Contraseña incorrecta");
        }
    } else {
        return Map.of("error", "No existe el usuario con ese email");
    }
    }
    
    private final PasswordEncoder passwordEncoder;

    public AdminController(AdminService adminService, PasswordEncoder passwordEncoder) {
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
}
}
