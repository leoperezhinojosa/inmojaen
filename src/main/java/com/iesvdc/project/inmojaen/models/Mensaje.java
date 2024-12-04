package com.iesvdc.project.inmojaen.models;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenido;
    private LocalDate fecha;
    private LocalTime hora;
    private Boolean leido;
    private Boolean enviado;
    private Boolean recibido;
    private Boolean borrado;
    @ManyToOne
    @JoinColumn(name = "emisor_id")
    private Usuario emisor;
    @ManyToOne
    @JoinColumn(name = "receptor_id")
    private Usuario receptor;
    private Boolean bloqueado;
}
