package com.iesvdc.project.inmojaen.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDate fecha;
    private LocalDateTime hora;
    private Boolean leido;
    private Boolean enviado;
    private Boolean recibido;
    private Boolean borrado;
    @OneToOne
    private Usuario emisor;
    @OneToOne
    private Usuario receptor;
    private Boolean bloqueado;
}
