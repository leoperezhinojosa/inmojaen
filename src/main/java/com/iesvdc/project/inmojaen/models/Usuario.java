package com.iesvdc.project.inmojaen.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String nombre;
    private String apellidos;
    private String telefono;
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private Rol rol;
    // @OneToMany(mappedBy = "usuario") // Si anuncios tienen referencia a Usuario
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Anuncio> anunciosEnVenta;
    // @OneToMany(mappedBy = "usuario") // Si anuncios tienen referencia a Favorito
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Favorito> favoritos;
    // @OneToMany
    @OneToMany(mappedBy = "emisor", fetch = FetchType.LAZY) // Vincula con 'emisor' en Mensaje
    private List<Mensaje> mensajesByEmisor;
    // @OneToMany
    @OneToMany(mappedBy = "receptor", fetch = FetchType.LAZY) // Vincula con 'receptor' en Mensaje
    private List<Mensaje> mensajesByReceptor;
    private Boolean premium;
    private Boolean inalterable;
    private Boolean enabled;
}