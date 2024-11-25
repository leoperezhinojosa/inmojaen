package com.iesvdc.project.inmojaen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iesvdc.project.inmojaen.models.Usuario;
import java.util.List;


public interface RepoUsuario extends JpaRepository<Usuario, Long> {

    // En este archivo se pueden añadir métodos adicionales para
    // la gestión de usuarios en la base de datos.
    // Spring se encargará de implementarlos automáticamente.
    List<Usuario> findByUsername(String username);

    List<Usuario> findByEmail(String email);
}
