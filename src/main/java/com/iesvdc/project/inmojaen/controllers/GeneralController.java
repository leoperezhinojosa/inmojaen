package com.iesvdc.project.inmojaen.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.iesvdc.project.inmojaen.models.Usuario;
import com.iesvdc.project.inmojaen.repositories.RepoAnuncio;
import com.iesvdc.project.inmojaen.repositories.RepoUsuario;

import lombok.NonNull;

import org.springframework.web.bind.annotation.RequestParam;


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


    /**
     * Endpoint: /register (GET)
     * Ruta de registro.
     * 
     * @return Vista del formulario de Registro.
     */
    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }
    
    /**
     * Endpoint: /register (POST)
     * Ruta de registro.
     * 
     * @param modelo     Modelo de la vista.
     * @param username  Nombre de usuario del usuario (único).
     * @param password  Contraseña.
     * @param nombre    Nombre del usuario.
     * @param apellidos Apellidos del usuario.
     * @param email     Correo electrónico del usuario (único).
     * @return Vista de error o mensaje de alta del usuario a registrar.
     */
    @PostMapping("/register")
    public String register(
        Model modelo,
        @NonNull @RequestParam String username,
        @NonNull @RequestParam String password,
        @NonNull @RequestParam String nombre,
        @NonNull @RequestParam String apellidos,
        @NonNull @RequestParam String email,
        @NonNull @RequestParam String telefono,
        @NonNull @RequestParam Boolean premium
    ) {
        Usuario usuario = new Usuario();
        BCryptPasswordEncoder encryptPassword = new BCryptPasswordEncoder();

        try {
            // Asignación de valores del nuevo usuario:
            usuario.setUsername(username);
            usuario.setPassword(encryptPassword.encode(password));
            usuario.setNombre(nombre);
            usuario.setApellidos(apellidos);
            usuario.setEmail(email);
            usuario.setTelefono(telefono);
            usuario.setPremium(premium);
            if (usuario.getPremium()) {
                usuario.getRol().setId((long) 2);
                usuario.getRol().setRol("USER");
            }
            else {
                usuario.getRol().setId((long) 3);
                usuario.getRol().setRol("BUYER");
            }
            usuario.setInalterable(false);
            usuario.setEnabled(true);
            // Guardar el nuevo usuario:
            repoUsuario.save(usuario);

            modelo.addAttribute("titulo", 
                    " - Alta nuevo usuario - ");
            modelo.addAttribute("mensaje", 
                    "El usuario ha sido registrado en el sistema. " + 
                    "Ya puede iniciar sesión");

                    return "redirect:/";
                    
        } catch (Exception e) {
            modelo.addAttribute("titulo",
                    " - ERROR de registro - ");
            modelo.addAttribute("mensaje",
                    "Fallo al registrar usuario. Comprobar los datos.");
            return "error";
        }

    }
    
}
