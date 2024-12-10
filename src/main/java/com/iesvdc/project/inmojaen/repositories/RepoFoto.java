package com.iesvdc.project.inmojaen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesvdc.project.inmojaen.models.Imagen;

public interface RepoFoto extends JpaRepository<Imagen, Long> {
    
}
