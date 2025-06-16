package com.raio_be.raio_be.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raio_be.raio_be.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String email);
}
