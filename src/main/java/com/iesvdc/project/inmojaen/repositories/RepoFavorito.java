package com.iesvdc.project.inmojaen.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesvdc.project.inmojaen.models.Anuncio;
import com.iesvdc.project.inmojaen.models.Favorito;
import com.iesvdc.project.inmojaen.models.Usuario;

public interface RepoFavorito extends JpaRepository<Favorito, Long> {
    
    // Encontrar favorito por usuario y anuncio:
    Optional<Favorito> findByUsuarioAndAnuncio(Usuario usuario, Anuncio anuncio);

}
