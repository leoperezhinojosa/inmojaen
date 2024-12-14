package com.iesvdc.project.inmojaen.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iesvdc.project.inmojaen.models.Anuncio;
import com.iesvdc.project.inmojaen.models.Favorito;
import com.iesvdc.project.inmojaen.models.Usuario;
import com.iesvdc.project.inmojaen.repositories.RepoAnuncio;
import com.iesvdc.project.inmojaen.repositories.RepoFavorito;
import com.iesvdc.project.inmojaen.repositories.RepoUsuario;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/favoritos")
public class ControllerFavoritos {

    @Autowired
    private RepoFavorito repoFavorito;
    
    @Autowired
    private RepoAnuncio repoAnuncio;

    @Autowired
    private RepoUsuario repoUsuario;

    /**
     * Endpoint: /favoritos (POST)
     * Agrega o elimina un anuncio de favoritos de un usuario.
     * 
     * @param modelo Modelo de la vista.
     * @param usuarioId ID del usuario anunciante.
     * @param anuncioId ID del anuncio de inmueble.
     * @return Vista de la gestion de favoritos.
     */
    @PostMapping("/favoritos")
    public String gestionarFavorito(Model modelo, @RequestParam Long anuncioId, @RequestParam Long usuarioId) {
        if (anuncioId == null || usuarioId == null) {
            modelo.addAttribute("titulo", "Error al gestionar favorito");
            modelo.addAttribute("mensaje", "El anuncio o el usuario indicado no existen");
            return "error";
        }
        
        Usuario usuario = repoUsuario.findById(usuarioId).get();
        Anuncio anuncio = repoAnuncio.findById(anuncioId).get();
        
        if (usuario == null || anuncio == null) {
            modelo.addAttribute("titulo", "Error al gestionar favorito");
            modelo.addAttribute("mensaje", "El usuario o el anuncio indicado no existen");
            return "error";
        } else {
            modelo.addAttribute("anuncio", anuncio);
            modelo.addAttribute("usuario", usuario);
            Optional<Favorito> favorito = repoFavorito.findByUsuarioAndAnuncio(usuario, anuncio);
            
            if (favorito.isPresent()) {
                // Si ya es favorito, eliminar
                repoFavorito.delete(favorito.get());
            } else {
                // Si no es favorito, agregar
                Favorito nuevoFavorito = new Favorito();
                nuevoFavorito.setUsuario(usuario);
                nuevoFavorito.setAnuncio(anuncio);
                repoFavorito.save(nuevoFavorito);
            }
        }

        return "redirect:/activos";
    }
    


}
