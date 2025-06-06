package com.raio_be.raio_be.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Admin {
  @Id
  private int id;

  @Column(nullable = false)
  private String nombreUsuarie;

  @Column(nullable = false)
  private String contraseña;

  @Column(nullable = false)
  private String email;

  public Admin() {

  }


  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombreUsuarie() {
    return this.nombreUsuarie;
  }

  public void setNombreUsuarie(String nombreUsuarie) {
    this.nombreUsuarie = nombreUsuarie;
  }

  public String getContraseña() {
    return this.contraseña;
  }

  public void setContraseña(String contraseña) {
    this.contraseña = contraseña;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
