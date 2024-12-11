package com.iesvdc.project.inmojaen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesvdc.project.inmojaen.models.Imagen;

public interface RepoImagen extends JpaRepository<Imagen, Long> {
    
}
