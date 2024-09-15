package com.iesvdc.project.inmojaen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.iesvdc.project.inmojaen.repositories.RepoAnuncio;
import com.iesvdc.project.inmojaen.repositories.RepoUsuario;

// TODO: Revisar métodos faltantes.

/**
 * Controlador general de la aplicación.
 * Contiene las rutas de ayuda, error y otras.
 */
@Controller
public class GeneralController {
    
    @Autowired
    RepoAnuncio repoAnuncio;

    @Autowired
    RepoUsuario repoUsuario;

    /**
     * Muestra la página de ayuda.
     * @return Vista de ayuda.
     */
    @GetMapping("/help")
    public String showHelp() {
        return "help";
    }
    
    /**
     * Muestra la página de sobre nosotros.
     * @return Vista de sobre nosotros.
     */
    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }
    
    /**
     * Muestra la página de error.
     * @return Vista de error.
     */
    @GetMapping("/error")
    public String showError() {
        return "error";
    }

    /**
     * Ruta de login.
     * Añadir método PostMapping("/login") si se personaliza aquí.
     */
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
    
}
