package com.iesvdc.project.inmojaen.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iesvdc.project.inmojaen.models.Anuncio;
import com.iesvdc.project.inmojaen.models.Favorito;
import com.iesvdc.project.inmojaen.models.Rol;
import com.iesvdc.project.inmojaen.models.Usuario;
import com.iesvdc.project.inmojaen.repositories.RepoAnuncio;
import com.iesvdc.project.inmojaen.repositories.RepoRol;
import com.iesvdc.project.inmojaen.repositories.RepoUsuario;

import lombok.NonNull;

/**
 * Controlador para el perfil temporal.
 * Este perfil es creado con el rol TEMPORAL, y se usa para crear usuarios sin
 * cuenta.
 * Puede guardar Anuncios como favoritos, que se pasarán
 * al perfil User correspondiente cuando se registre o loguee.
 * Los usuarios con este rol se borrarán según el tiempo ajustado
 * en el servicio CleanServices.
 */
@Controller
@RequestMapping("/invitado")
public class ControllerTemporal {

    @Autowired
    private RepoUsuario repoUsuario;

    // @Autowired
    // private RepoRol repoRol;

    @Autowired
    private RepoAnuncio repoAnuncio;

    /**
     * Endpoint: /invitado/temporal (POST)
     * 
     * Crea un perfil temporal con el rol TEMPORAL para usuarios sin cuenta.
     * 
     * @param modelo          Modelo de la vista.
     * @param usuarioTemporal Usuario temporal creado.
     * @return Vista invitado.
     */
    // @PostMapping("/temporal")
    // public String crearPerfilTemporal(Model modelo) {
    //     // Buscar el rol TEMPORAL
        
    //     Rol rolTemporal = new Rol();
    //     rolTemporal.setId((Long) 2L + 2);
    //     rolTemporal.setRol("TEMPORAL");

    //     // Crear el usuario temporal
    //     LocalDateTime fechaActual = LocalDateTime.now();
    //     Usuario usuarioTemporal = new Usuario();
    //     usuarioTemporal.setUsername("temporal" + fechaActual.toString());
    //     usuarioTemporal.setNombre("Usuario");
    //     usuarioTemporal.setApellidos("Temporal");
    //     usuarioTemporal.setEmail(usuarioTemporal.getUsername() + "@sincorreo.com");
    //     usuarioTemporal.setPassword("00000000");
    //     usuarioTemporal.setRol(rolTemporal);
    //     usuarioTemporal.setEnabled(true);
    //     usuarioTemporal.setInalterable(false);
    //     usuarioTemporal.setPremium(false);

    //     repoUsuario.save(usuarioTemporal);

    //     // Pasar el perfil temporal al modelo
    //     modelo.addAttribute("perfil", usuarioTemporal);

    //     return "redirect:/invitado";
    // }

    /**
     * Endpoint: invitado/anuncios/ (GET)
     * 
     * Muestra todos los anuncios activados de la base de datos.
     * El rol TEMPORAL puede verlos, y marcar los que desee como favoritos.
     * Se borrarán junto al perfil temporal si no se loguea.
     * 
     * @param anuncio Anuncio a marcar como favorito.
     * @param modelo  Modelo de la vista.
     * @return Vista de la lista de anuncios.
     */
    @GetMapping("/")
    public String invitadoAnuncios(Model modelo) {
        List<Anuncio> anuncios = repoAnuncio.findAll();
        modelo.addAttribute("anuncios", anuncios);
        return "invitado/anuncios";
    }

    /**
     * Endpoint: invitado/anuncios (GET)
     * Endpoint alternativo para la lista de usuarios.
     * 
     * @param modelo Modelo de la vista.
     * @return Vista de la lista de usuarios.
     */
    // @GetMapping("")
    // public String invitadoAnunciosActivos(Model modelo) {
    //     return invitadoAnuncios(modelo);
    // }

    /**
     * Endpoint: invitado/favoritos (GET)
     * Endpoint para la lista de favoritos del perfil temporal.
     * 
     * @param modelo Modelo de la vista.
     * @param id     ID del usuario temporal.
     * @return Vista de la lista de favoritos.
     */
    // @GetMapping("/favoritos")
    // public String invitadoFavoritos(Model modelo, @PathVariable("id") @NonNull Long id) {
    //     Optional<Usuario> usuario = repoUsuario.findById(id);
    //     if (usuario.isPresent()) {
    //         List<Favorito> favoritos = repoUsuario.findById(id).get().getFavoritos();
    //         if (favoritos != null && !favoritos.isEmpty()) {
    //             List<Anuncio> anuncios = favoritos.stream()
    //                     .map(f -> f.getAnuncio())
    //                     .collect(Collectors.toList());
    //             modelo.addAttribute("anuncios", anuncios);
    //         } else {
    //             modelo.addAttribute("anuncios", new ArrayList<Anuncio>());
    //         }
    //     } else {
    //         modelo.addAttribute("anuncios", new ArrayList<Anuncio>());
    //         modelo.addAttribute("titulo", "- Error: Perfil temporal no válido -");
    //         modelo.addAttribute("mensaje", "El perfil temporal especificado no existe.");
    //         return "error";
    //     }
    //     return "invitado/favoritos";
    // }

}
