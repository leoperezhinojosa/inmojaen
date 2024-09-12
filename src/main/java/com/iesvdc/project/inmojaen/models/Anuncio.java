package com.iesvdc.project.inmojaen.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Anuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private Double precio;
    private Double superficie;
    private Integer habitaciones;
    private Integer banos;
    private String direccion;
    private LocalDate fechaPublicacion;
    private LocalDate fechaReserva;
    private LocalDate fechaVenta;
    private Boolean visto;
    private Boolean reservado;
    private Boolean vendido;
    private Usuario usuario;
    private List<String> fotos;
    private List<String> mensajes;
}
