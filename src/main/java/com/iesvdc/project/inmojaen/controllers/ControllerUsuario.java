package com.iesvdc.project.inmojaen.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iesvdc.project.inmojaen.models.Usuario;
import com.iesvdc.project.inmojaen.repositories.RepoAnuncio;
import com.iesvdc.project.inmojaen.repositories.RepoUsuario;
import org.springframework.web.bind.annotation.GetMapping;

// TODO: Revisar métodos faltantes. ¿Cambiar nombre a "/perfil"?

/**
 * Controlador del usuario.
 * Rutas utilizadas por cada usuario (comprador y vendedor)
 * para gestionar su propia cuenta.
 * Los usuarios pueden crear y editar su cuenta, pero no eliminarla.
 * Si desean eliminarla, sólo se desactivará.
 * El administrador será el encargado de eliminar cuentas totalmente.
 */
@Controller
@RequestMapping("/usuario")
public class ControllerUsuario {
    
    @Autowired
    private RepoUsuario repoUsuario;

    @Autowired
    private RepoAnuncio repoAnuncio;

    /**
     * Método para obtener el usuario que ha iniciado sesión
     * desde el contexto de la aplicación. Devuelve el usuario
     * correspondiente con el usuario que está logueado.
     * 
     * @param modelo Modelo de la vista.
     * @return Usuario logueado.
     */
    private Usuario getLoggedUser() {
        // Obtención del usuario logueado del contexto de la aplicación.
        Authentication authentication = SecurityContextHolder
            .getContext().getAuthentication();
        String username = authentication.getName();
        // Obtención del usuario del repositorio por su username:
        Usuario usuario = repoUsuario.findByUsername(username).get(0);

        return usuario;
    }

    /**
     * Endpoint: /usuario/ (GET)
     * Método que obtiene el usuario que ha entrado del contexto
     * de la aplicación. Este método es un espejo del método 
     * getLoggedUser(), pero con un parámetro.
     * 
     * @param modelo Modelo de la vista.
     * @return Usuario logueado.
     */
    @GetMapping("/")
    public String getUser(Model modelo) {
        modelo.addAttribute("usuario", getLoggedUser());
        return "usuario/usuario";
    }

    /**
     * Endpoint: /usuario/{id} (GET)
     * Método para obtener el usuario que ha iniciado sesión 
     * desde el contexto de la aplicación. Éste es un espejo
     * del método getLoggedUser(), pero con un parámetro adicional.
     * 
     * @param modelo Modelo de la vista.
     * @return Usuario logueado.
     */
    @GetMapping("")
    public String getUserForm(Model modelo) {
        return getUser(modelo);
    }


    

}
