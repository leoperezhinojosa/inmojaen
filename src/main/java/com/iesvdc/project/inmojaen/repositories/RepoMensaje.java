package com.iesvdc.project.inmojaen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesvdc.project.inmojaen.models.Mensaje;

public interface RepoMensaje extends JpaRepository<Mensaje, Long> {
    
}
