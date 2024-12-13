package com.iesvdc.project.inmojaen.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.iesvdc.project.inmojaen.models.Rol;
import com.iesvdc.project.inmojaen.models.Usuario;
import com.iesvdc.project.inmojaen.repositories.RepoUsuario;

/**
 * Servicio para limpiar la base de datos de usuarios temporales.
 * Se ejecuta cada hora.
 */
 @Service
public class CleanServices {

    @Autowired
    private RepoUsuario repoUsuario;

    /**
     * Limpiar la base de datos de usuarios temporales.
     * Puede ajustarse la frecuencia de limpieza.
     */
    // @Scheduled(cron = "0 0 * * * ?") // Cada hora
    @Scheduled(cron = "0 0 0 * * ?") // Cada dia a las 00:00
    public void eliminarTemporales() {
        Rol rol = new Rol();
        rol.setId((Long) 2L);
        rol.setRol("TEMPORAL");
        List<Usuario> temporales = repoUsuario.findByRol(rol);
        repoUsuario.deleteAll(temporales);
    }
}