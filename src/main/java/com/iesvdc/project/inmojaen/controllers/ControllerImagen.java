package com.iesvdc.project.inmojaen.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.iesvdc.project.inmojaen.models.Imagen;
import com.iesvdc.project.inmojaen.repositories.RepoAnuncio;
// import com.iesvdc.project.inmojaen.repositories.RepoImagen;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador de imagenes.
 * Contiene las rutas para gestionar las imagenes de los anuncios.
 */
@Controller
@RequestMapping("/imagenes")
public class ControllerImagen {
    
    // @Autowired
    // private RepoImagen repoImagen;
    
    @Autowired
    private RepoAnuncio repoAnuncio;


    /**
     * Endpoint: /imagenes (GET)
     * Metodo para obtener las imagenes de un anuncio.
     * 
     * @param id Id del anuncio.
     */
    @GetMapping("/anuncio")
    public String getImagenes(Model modelo, @RequestParam("id") Long id){ 
        repoAnuncio.findById(id);
        List<Imagen> imagenes = repoAnuncio.findById(id).get().getImagenes();
        modelo.addAttribute("imagenes", imagenes);
        return "imagenes/anuncio";
    }

    /**
     * Endpoint: /principal (GET)
     * Metodo para obtener la imagen principal de un anuncio.
     * 
     * @param id Id del anuncio.
     * @return Vista de la imagen principal.
     */
    @GetMapping("/principal")
    public String getPrincipal(Model modelo, @RequestParam("id") Long id){ 
        repoAnuncio.findById(id);
        List<Imagen> imagenes = repoAnuncio.findById(id).get().getImagenes();
        Imagen imagen = null;
        for (Imagen i : imagenes) {
            if (i.getPrincipal()) {
                imagen = i;
            }
        }
        if (imagen == null) {
            imagen = imagenes.get(0);
        }
        modelo.addAttribute("imagen", imagen);
        return "imagenes/principal";
    }


}
