package com.raio_be.raio_be.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.raio_be.raio_be.DTO.AdminDTO;
import com.raio_be.raio_be.model.Admin;
import com.raio_be.raio_be.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService {

  @Autowired
  private AdminRepository adminRepository;
  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  public static AdminDTO toDto(Admin admin) {
    return AdminDTO.builder()
        .id(admin.getId())
        .nombreUsuarie(admin.getNombreUsuarie())
        .contraseña(admin.getContraseña())
        .email(admin.getEmail())
        .build();
  }

  public static Admin toEntity(AdminDTO dto) {
    return Admin.builder()
        .id(dto.getId())
        .nombreUsuarie(dto.getNombreUsuarie())
        .contraseña(dto.getContraseña())
        .email(dto.getEmail())
        .build();
  }

  @Override
  public AdminDTO createAdmin(AdminDTO adminDTO) {
    Admin admin = toEntity(adminDTO);
    admin.setContraseña(bCryptPasswordEncoder.encode(admin.getContraseña()));
    Admin savedAdmin = adminRepository.save(admin);
    return toDto(savedAdmin);
  }

  @Override
  public List<AdminDTO> getAllAdmins() {
    return adminRepository.findAll()
        .stream()
        .map(AdminServiceImpl::toDto)
        .collect(Collectors.toList());
  }

  @Override
  public AdminDTO getAdminById(Integer id) {
    Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontró el admin"));
    return toDto(admin);

  }

  @Override
  public AdminDTO updateAdmin(Integer id, AdminDTO adminDTO) {
    Admin existingAdmin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin no encontrado"));

    existingAdmin.setNombreUsuarie(adminDTO.getNombreUsuarie());
    existingAdmin.setContraseña(bCryptPasswordEncoder.encode(adminDTO.getContraseña()));
    existingAdmin.setEmail(adminDTO.getEmail());

    Admin updatedAdmin = adminRepository.save(existingAdmin);
    return toDto(updatedAdmin);
  }

  @Override
  public void deleteAdmin(Integer id) {
    if (!adminRepository.existsById(id)) {
      throw new RuntimeException("Admin no encontrado");
    }
    adminRepository.deleteById(id);
  }

  @Override
  public AdminDTO getAdminByEmail(String email) {
      Admin admin = adminRepository.findByEmail(email)
        .orElseThrow(() -> new RuntimeException("Admin no encontrado con ese email"));
      return toDto(admin);
  } 
}
