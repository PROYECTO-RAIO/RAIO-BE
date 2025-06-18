package com.raio_be.raio_be.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.raio_be.raio_be.DTO.AuthResponse;
import com.raio_be.raio_be.model.Admin;
import com.raio_be.raio_be.repository.AdminRepository;
import com.raio_be.raio_be.security.JwtUtil;
import com.raio_be.raio_be.service.AdminService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("api/v1/admins")
@CrossOrigin(origins = "*")

public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRepository adminRepository;

    @PostMapping
    public AdminDTO createAdmin(@Valid @RequestBody AdminDTO adminDTO) {
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

//OLD CODE - does not include autho with JWT
    // @PostMapping("/login")
    // public String login(@RequestBody Admin loginData) {
    //     Optional<Admin> optionalAdmin = adminRepository.findByEmail(loginData.getEmail());

    //     if (optionalAdmin.isPresent()) {
    //         Admin admin = optionalAdmin.get();
    //         if (admin.getContraseña().equals(loginData.getContraseña())) {
    //             return "Login exitoso";
    //         } else {
    //             return "Contraseña incorrecta";
    //         }
    //     } else {
    //         return "No existe el usuario con ese email";
    //     }
    // }
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin loginData) {
        Optional<Admin> optionalAdmin = adminRepository.findByEmail(loginData.getEmail());

        if (optionalAdmin.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el usuario con ese email");
        }

        Admin admin = optionalAdmin.get();

        if (!admin.getContraseña().equals(loginData.getContraseña())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
        }

        String token = jwtUtil.generateToken(admin.getEmail());

        return ResponseEntity.ok(new AuthResponse("Login exitoso", token));
    }
}

