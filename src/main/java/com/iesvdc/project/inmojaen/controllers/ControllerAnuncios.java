package com.iesvdc.project.inmojaen.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import com.iesvdc.project.inmojaen.models.Anuncio;
import com.iesvdc.project.inmojaen.models.Usuario;
import com.iesvdc.project.inmojaen.repositories.RepoAnuncio;

import lombok.NonNull;

// TODO: Revisar métodos faltantes.

/**
 * Controlador de anuncios.
 * Rutas utilizadas por el administrador para gestión de anuncios.
 * El administrador puede añadir, editar y eliminar anuncios por petición,
 * y pueden desactivarse por infracciones de ley o políticas legales.
 */
@Controller
@RequestMapping("/anuncios")
public class ControllerAnuncios {

    @Autowired
    private RepoAnuncio repoAnuncio;

    /**
     * Endpoint: /anuncios/ (GET)
     * Muestra todos los anuncios registrados.
     * 
     * @param modelo Modelo de la vista.
     * @return Vista de la lista de anuncios.
     */
    @GetMapping(path = "/")
    public String findAll(Model modelo) {
        List<Anuncio> anuncios = repoAnuncio.findAll();
        modelo.addAttribute("anuncios", anuncios);
        return "anuncios/anuncios";
    }

    /**
     * Endpoint: /anuncios (GET)
     * Endpoint alternativo para la lista de anuncios.
     * 
     * @param modelo Modelo de la vista.
     * @return Vista de la lista de anuncios.
     */
    @GetMapping(path = "")
    public String findAllAdvertisements(Model modelo) {
        return findAll(modelo);
    }

    /**
     * Endpoint: /anuncios/info/{id} (GET)
     * Muestra la información de un anuncio.
     * 
     * @param modelo
     * @return Vista de la información completa del anuncio.
     */
    @GetMapping("/info/{id}")
    public String getUserInfo(Model modelo, @PathVariable("id") @NonNull Long id) {
        Optional<Anuncio> anuncio = repoAnuncio.findById(id);
        if (!anuncio.isPresent()) {
            modelo.addAttribute("titulo", "Error al mostrar anuncio");
            modelo.addAttribute("mensaje", "El anuncio con el id " + id + " no existe");
            return "error";
        } else {
            modelo.addAttribute("anuncio", anuncio.get());
            return "anuncios/info";
        }
    }

    /**
     * Endpoint: /anuncios/add (GET)
     * Muestra el formulario para añadir un anuncio nuevo.
     * 
     * @param modelo Modelo de la vista.
     * @return Vista del formulario de añadir anuncio.
     */
    @GetMapping("/add")
    public String addAdvertisementForm(Model modelo) {
        modelo.addAttribute("anuncio", new Anuncio());
        return "anuncios/add";
    }

    /**
     * Endpoint: /anuncios/add (POST)
     * Añade un anuncio nuevo a la base de datos.
     * 
     * @param anuncio Anuncio a añadir.
     * @return Redirección a la lista total de anuncios.
     */
    @PostMapping("/add")
    public String addAdvertisement(
            @ModelAttribute("anuncio") @NonNull Anuncio anuncio) {
        // Actualizar la lista de anuncios con el nuevo anuncio.
        repoAnuncio.save(anuncio);
        return "redirect:/anuncios";
    }

    /**
     * Endpoint: /anuncios/edit/{id} (GET)
     * Muestra el formulario para editar un anuncio.
     * 
     * @param modelo Modelo de la vista.
     * @param id     ID del anuncio a editar.
     * @return Vista de edición del anuncio.
     */
    @GetMapping("/edit/{id}")
    public String editAdvertisementForm(
            Model modelo, @PathVariable("id") Long id) {
        Optional<Anuncio> anuncioAEditar = repoAnuncio.findById(id);
        if (!anuncioAEditar.isPresent()) {
            modelo.addAttribute("titulo",
                    " - Error al editar anuncio - ");
            modelo.addAttribute("mensaje",
                    " - Atención: El anuncio con ID " + id + " no existe - ");
            return "error";
        } else {
            modelo.addAttribute("anuncio", anuncioAEditar.get());
        }

        return "anuncios/edit";
    }

    /**
     * Endpoint: /anuncios/edit (POST)
     * Edita un anuncio existente en la base de datos.
     * 
     * @param anuncio Anuncio a editar.
     * @return Redirección a la lista total de anuncios.
     */
    @PostMapping("/edit")
    public String editAdvertisement(
            @ModelAttribute("anuncio") @NonNull Anuncio anuncio) {
        // Actualizar la lista de anuncios con el anuncio editado.
        repoAnuncio.save(anuncio);
        return "redirect:/anuncios";
    }

    /**
     * Endpoint: /anuncios/delete/{id} (GET)
     * Muestra el formulario para eliminar un anuncio de la base de datos.
     * 
     * @param id ID del anuncio a eliminar.
     * @return Metodo POST para eliminar el anuncio.
     */
    @GetMapping("/delete/{id}")
    public String deleteAdvertisementForm(
            Model modelo, @PathVariable("id") @NonNull Long id) {
        Optional<Anuncio> anuncioAEliminar = repoAnuncio.findById(id);
        if (!anuncioAEliminar.isPresent()) {
            modelo.addAttribute("titulo",
                    " - Error al eliminar anuncio - ");
            modelo.addAttribute("mensaje",
                    " - Atención: El anuncio con ID " + id + " no existe - ");
            return "error";
        } else {
            // Los anuncios reservados o comprados no pueden eliminarse.
            if (anuncioAEliminar.get().getReservado() || anuncioAEliminar.get().getVendido()) {
                modelo.addAttribute("titulo",
                        " - Error al eliminar anuncio - ");
                modelo.addAttribute("mensaje",
                        " - Atención: El anuncio con ID " + id + " no puede eliminarse - ");
                return "error";
            } else {
                modelo.addAttribute("anuncio", anuncioAEliminar.get());
            }
        }
        return "anuncios/delete";
    }

    /**
     * Endpoint: /anuncios/delete (POST)
     * Elimina un anuncio de la base de datos.
     * 
     * @param id ID del anuncio a eliminar.
     * @return Redirección a la lista total de anuncios.
     */
    @PostMapping("/delete")
    public String deleteAdvertisement(
            Model modelo, @RequestParam("id") @NonNull Long id) {
        Optional<Anuncio> anuncioAEliminar = repoAnuncio.findById(id);
        if (!anuncioAEliminar.isPresent()) {
            modelo.addAttribute("titulo",
                    " - Error al eliminar anuncio - ");
            modelo.addAttribute("mensaje",
                    " - Atención: El anuncio con ID " + id + " no existe - ");
            return "error";
        } else {
            // Los anuncios reservados o comprados no pueden eliminarse.
            if (anuncioAEliminar.get().getReservado() || anuncioAEliminar.get().getVendido()) {
                modelo.addAttribute("titulo",
                        " - Error al eliminar anuncio - ");
                modelo.addAttribute("mensaje",
                        " - Atención: El anuncio con ID " + id + " no puede eliminarse - ");
                return "error";
            } else {
                // Eliminar el anuncio de la base de datos.
                modelo.addAttribute("anuncio", anuncioAEliminar.get());
                repoAnuncio.delete(anuncioAEliminar.get());
            }
        }
        return "redirect:/anuncios";
    }

    /**
     * Endpoint: /activate (POST)
     * Activa o desactiva un anuncio.
     * 
     * @param id Identificador del anuncio a activar o desactivar.
     * @return Redirigir a la lista de anuncios.
     */
    @PostMapping("/activate")
    public String activateAdvertisement(
            Model modelo, @RequestParam("id") @NonNull Long id) {
        Optional<Anuncio> anuncio = repoAnuncio.findById(id);
        if (!anuncio.isPresent()) {
            modelo.addAttribute("titulo",
                    " - Error en el manejo de activación de anuncio - ");
            modelo.addAttribute("mensaje",
                    " - Atención: El anuncio indicado no existe - ");
            return "error";
        } else {
            if (anuncio.get().getActivo()) {
                anuncio.get().setActivo(false);
            } else {
                anuncio.get().setActivo(true);
            }
            repoAnuncio.save(anuncio.get());
        }
        return "redirect:/anuncios";
    }

}
