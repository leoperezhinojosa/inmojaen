package com.iesvdc.project.inmojaen.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesvdc.project.inmojaen.models.Anuncio;

public interface RepoAnuncio extends JpaRepository<Anuncio, Long> {

    List<Anuncio> findAllActivos();

    List<Anuncio> findAllActivo();

}
