package com.iesvdc.project.inmojaen.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor    
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    private String nombre;
    private String apellidos;
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
    @OneToMany
    private List<Anuncio> anunciosEnVenta;
    @OneToMany
    private List<Anuncio> anunciosFavoritos;
    private Boolean premium;
    private Boolean inalterable;
    private Boolean enabled;
}