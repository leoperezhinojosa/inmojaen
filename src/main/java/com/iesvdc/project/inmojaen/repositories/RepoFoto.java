package com.iesvdc.project.inmojaen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesvdc.project.inmojaen.models.Foto;

public interface RepoFoto extends JpaRepository<Foto, Long> {
    
}
