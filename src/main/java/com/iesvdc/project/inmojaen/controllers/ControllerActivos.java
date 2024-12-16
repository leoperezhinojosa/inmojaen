package com.iesvdc.project.inmojaen.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iesvdc.project.inmojaen.models.Anuncio;
import com.iesvdc.project.inmojaen.models.Favorito;
import com.iesvdc.project.inmojaen.models.Imagen;
import com.iesvdc.project.inmojaen.models.Usuario;
import com.iesvdc.project.inmojaen.repositories.RepoAnuncio;
import com.iesvdc.project.inmojaen.repositories.RepoFavorito;
import com.iesvdc.project.inmojaen.repositories.RepoUsuario;

import lombok.NonNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @Autowired
    private RepoFavorito repoFavorito;

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
            if (!anuncio.getActivo())
                anuncios.remove(anuncio);
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
     * También lleva las imágenes del mismo.
     * 
     * @param modelo Modelo de la vista.
     * @param id     ID del anuncio.
     * @return Vista de la informacion completa del anuncio.
     */
    @GetMapping("/info/{id}")
    public String getAnuncioInfo(Model modelo, @PathVariable("id") @NonNull Long id) {
        Optional<Anuncio> anuncio = repoAnuncio.findById(id);

        if (!anuncio.isPresent() || !anuncio.get().getActivo()) {
            modelo.addAttribute("titulo", " - Error al mostrar anuncio - ");
            modelo.addAttribute("mensaje", "El anuncio con el id " + id + " no existe.");
            return "error";
        } else {
            Anuncio anuncioObtenido = anuncio.get();

            // Obtener las imágenes asociadas al anuncio:
            List<Imagen> imagenes = anuncioObtenido.getImagenes();

            // Añadir datos al modelo:
            modelo.addAttribute("anuncio", anuncioObtenido);
            modelo.addAttribute("imagenes", imagenes);

            return "activos/info";
        }
    }

    /**
     * Endpoint: /activos/add (GET)
     * Metodo para agregar un anuncio activo a la base de datos.
     * 
     * @param id     ID del usuario logueado.
     * @param modelo Modelo de la vista.
     * @return Vista de los anuncios del usuario.
     */
    @GetMapping("/add")
    public String add(@PathVariable("id") Long id, Model modelo, Anuncio anuncio) {
        return "activos/add";
    }

    /**
     * Endpoint: /favoritos (GET)
     * Vista de la gestion de favoritos.
     * 
     * @param modelo Modelo de la vista.
     * @return Vista de la gestion de favoritos.
     */
    @GetMapping("/favoritos")
    public String getFavoritos(Model modelo) {
        Usuario usuario = getLoggedUser();
        if (usuario.getId() == null) {
            modelo.addAttribute("titulo", " - Error al gestionar favorito - ");
            modelo.addAttribute("mensaje", "El usuario indicado no existe");
            return "error";
        }

        if (usuario != null) {
            List<Favorito> favoritos = usuario.getFavoritos();
            List<Anuncio> anuncios = new ArrayList<>();
            // Recorrer la lista de favoritos y obtener los anuncios correspondientes:
            for (Favorito favorito : favoritos) {
                anuncios.add(favorito.getAnuncio());
            }
            modelo.addAttribute("usuario", usuario);
            modelo.addAttribute("anuncios", anuncios);
        }

        return "activos/favoritos";
    }

    @PostMapping("/favoritos")
    public String toggleFavorito(@RequestParam("anuncioId") Long anuncioId,
            @RequestParam("usuarioId") Long usuarioId,
            RedirectAttributes redirectAttributes) {
        // Obtener usuario y anuncio
        Usuario usuario = repoUsuario.findById(usuarioId)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Anuncio anuncio = repoAnuncio.findById(anuncioId)
                .orElseThrow(() -> new IllegalArgumentException("Anuncio no encontrado"));

        // Alternar favorito
        Optional<Favorito> favoritoExistente = repoFavorito.findByUsuarioAndAnuncio(usuario, anuncio);
        if (favoritoExistente.isPresent()) {
            repoFavorito.delete(favoritoExistente.get());
        } else {
            Favorito nuevoFavorito = new Favorito();
            nuevoFavorito.setUsuario(usuario);
            nuevoFavorito.setAnuncio(anuncio);
            repoFavorito.save(nuevoFavorito);
        }

        return "redirect:/activos";
    }

}
