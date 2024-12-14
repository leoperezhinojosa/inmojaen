package com.iesvdc.project.inmojaen.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iesvdc.project.inmojaen.models.Anuncio;
import com.iesvdc.project.inmojaen.models.Usuario;
import com.iesvdc.project.inmojaen.repositories.RepoAnuncio;
import com.iesvdc.project.inmojaen.repositories.RepoUsuario;

import lombok.NonNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Controlador de anuncios activos.
 * Rutas utilizadas por el usuario registrado para los anuncios activos.
 * El usuario puede ver los anuncios activos y ver los detalles de los mismos.
 * Puede marcar y guardar uno o varios anuncios como favoritos.
 * Tambien puede filtrar los anuncios por precio y por superficie.
 */
@Controller
@RequestMapping("/activos")
public class ControllerActivos {

    @Autowired
    private RepoAnuncio repoAnuncio;

    @Autowired
    private RepoUsuario repoUsuario;


    /**
     * Método que obtiene el usuario que ha entrado del contexto
     * de la aplicación. Devuelve un objeto de tipo Usuario que corresponde
     * con el usuario que está logueado.
     * 
     * @param modelo Modelo de la vista
     * @return Usuario que está logueado
     */
    Usuario getLoggedUser() {
        // Obtener el usuario logueado del contexto de la aplicación:
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        String username = authentication.getName();
        // Se obtiene el usuario del repositorio por su nombre de usuario:
        Usuario usuario = repoUsuario.findByUsername(username).get(0);

        return usuario;
    }
    
    /**
     * Endpoint: /activos (GET)
     * Metodo para obtener los anuncios activos.
     * 
     * @param modelo Modelo de la vista.
     * @return Vista de los anuncios activos.
     */
    @GetMapping(path = "/")
    public String findAllActive(Model modelo) {
        Usuario usuario = getLoggedUser();
        List<Anuncio> anuncios = repoAnuncio.findAll();
        for (Anuncio anuncio : anuncios) {
            if (!anuncio.getActivo()) anuncios.remove(anuncio);
        }
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("anuncios", anuncios);
        return "activos/activos";
    }

    /**
     * Endpoint: /activos (GET)
     * Endpoint alternativo para la lista de anuncios activos.
     * 
     * @param modelo Modelo de la vista.
     * @return Vista de los anuncios activos.
     */
    @GetMapping(path = "")
    public String findAllActiveAdvertisements(Model modelo) {
        return findAllActive(modelo);
    }

    /**
     * Endpoint: /activos/info/{id} (GET)
     * Metodo para obtener la informacion de un anuncio activo.
     * 
     * @param modelo Modelo de la vista.
     * @param id ID del anuncio.
     * @return Vista de la informacion completa del anuncio.
     */
    @GetMapping("/info/{id}")
    public String getUserInfo(Model modelo, @PathVariable("id") @NonNull Long id) {
        Optional<Anuncio> anuncio = repoAnuncio.findById(id);
        if (!anuncio.isPresent() || !anuncio.get().getActivo()) {
            modelo.addAttribute("titulo", " - Error al mostrar anuncio - ");
            modelo.addAttribute("mensaje", "El anuncio con el id " + id + " no existe.");
            return "error";
        } else {
            modelo.addAttribute("anuncio", anuncio.get());
            return "activos/info";
        }
    }

    /**
     * Endpoint: /activos/add (GET)
     * Metodo para agregar un anuncio activo a la base de datos.
     * 
     * @param id ID del usuario logueado.
     * @param modelo Modelo de la vista.
     * @return Vista de los anuncios del usuario.
     */
    @GetMapping("/add")
    public String add(@PathVariable("id") Long id, Model modelo, Anuncio anuncio) {
        return "activos/add";
    }
    























    /**
     * Todo -> Ya veremos si acabo usando esto...
     * 
     * Endpoint: /activos/buscar (GET)
     * Metodo para buscar anuncios activos por precio y superficie.
     * 
     * @param modelo Modelo de la vista.
     * @param precioMin Precio mínimo.
     * @param precioMax Precio máximo.
     * @param superficieMin Superficie mínima.
     * @param superficieMax Superficie máxima.
     * @return Vista de los anuncios activos filtrados.
     */
    // @GetMapping("/buscar")
    // public String findByFilters(Model modelo, @RequestParam(required = false) Double precioMin, @RequestParam(required = false) Double precioMax,
    //         @RequestParam(required = false) Double superficieMin, @RequestParam(required = false) Double superficieMax) {
    //     List<Anuncio> anuncios = repoAnuncio.findByFilters(precioMin, precioMax, superficieMin, superficieMax);
    //     for (Anuncio anuncio : anuncios) {
    //         if (!anuncio.getActivo()) anuncios.remove(anuncio);
    //     }
    //     modelo.addAttribute("anuncios", anuncios);
    //     return "activos/activos";
    // }
    
}
