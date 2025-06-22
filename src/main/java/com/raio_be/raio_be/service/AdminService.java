package com.raio_be.raio_be.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.raio_be.raio_be.DTO.AdminDTO;

@Service
public interface AdminService {

    AdminDTO createAdmin(AdminDTO adminDTO);

   List<AdminDTO> getAllAdmins();

     AdminDTO getAdminById(Integer id);

    AdminDTO updateAdmin(Integer id, AdminDTO adminDTO);

    void deleteAdmin(Integer id);

    AdminDTO getAdminByEmail(String email);
} 
