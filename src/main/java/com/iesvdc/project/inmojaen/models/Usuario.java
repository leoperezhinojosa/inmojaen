package com.iesvdc.project.inmojaen.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor    
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Column(unique = true)
    private String email;
    private String password;
    private String telefono;
    private Rol rol;
    @Column(unique = true)
    private String username;
    private List<Anuncio> anunciosEnVenta;
    private List<Anuncio> anunciosFavoritos;
    private Boolean inalterable;
    private Boolean enabled;
}