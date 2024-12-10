package com.iesvdc.project.inmojaen.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iesvdc.project.inmojaen.models.Usuario;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/favoritos")
public class ControllerFavoritos {

    @Autowired
    private HttpSession session;

    /**
     * Ruta: /favoritos
     * Muestra los anuncios favoritos del usuario sin registrar.
     * @param session Sesión guardada en el navegador.
     * @param model Modelo de la vista.
     * @return Vista de los anuncios favoritos.
     */
    @GetMapping("/favoritos")
    public String getFavoritos(HttpSession session, Model model) {
        @SuppressWarnings("unchecked")
        List<Long> favoritos = (List<Long>) session.getAttribute("favoritos");
        if (favoritos == null) {
            favoritos = new ArrayList<>();
        }
        model.addAttribute("favoritos", favoritos);
        return "favoritos"; // Vista con los anuncios favoritos
    }

    /**
     * Ruta: /favoritos/add
     * Añade un anuncio a los favoritos del usuario sin registrar.
     * @param anuncioId Id del anuncio a anadir.
     * @param session Sesión guardada en el navegador.
     * @param model Modelo de la vista.
     * @return
     */
    @PostMapping("/add")
    public String addFavorito(@RequestParam Long anuncioId, HttpSession session, Model model) {
        // Obtener la lista de favoritos desde la Sesión
        @SuppressWarnings("unchecked")
        List<Long> favoritos = (List<Long>) session.getAttribute("favoritos");
        if (favoritos == null) {
            favoritos = new ArrayList<>();
            session.setAttribute("favoritos", favoritos);
        }

        // Añadir el anuncio a la lista (si no lo estuviera)
        if (!favoritos.contains(anuncioId)) {
            favoritos.add(anuncioId);

        }

        model.addAttribute("message", "Anuncio añadido a favoritos. Inicia sesión para mantener tus favoritos.");
        return "redirect:/anuncios";
    }

    /**
     * Evento para guardar los favoritos del usuario en la BD al iniciar sesión.
     * @param event Evento de inicio de sesión.
     * @param session Sesión anteriormente guardada en el navegador.
     */
    @EventListener
    public void onLoginSuccess(AuthenticationSuccessEvent event) {
        Usuario usuario = (Usuario) event.getAuthentication().getPrincipal();
        @SuppressWarnings("unchecked")
        List<Long> favoritos = (List<Long>) session.getAttribute("favoritos");

        if (favoritos != null && !favoritos.isEmpty()) {
            favoritos.forEach(anuncioId -> guardarFavorito(usuario.getId(), anuncioId));
            session.removeAttribute("favoritos");
        }
    }

    private void guardarFavorito(Long userId, Long anuncioId) {
        // Implementa la lógica para guardar los favoritos en la BD
    }
}
