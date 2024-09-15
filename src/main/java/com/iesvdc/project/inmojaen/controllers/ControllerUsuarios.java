package com.iesvdc.project.inmojaen.controllers;

import java.util.List;
import java.util.Set;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import com.iesvdc.project.inmojaen.models.Rol;
import com.iesvdc.project.inmojaen.models.Usuario;
import com.iesvdc.project.inmojaen.repositories.RepoUsuario;

import lombok.NonNull;


// TODO: Revisar métodos faltantes.

/**
 * Controlador de usuarios.
 * Rutas utilizadas por el administrador para gestión de usuarios.
 * El administrador puede añadir, editar o eliminar usuarios.
 * Se comprobará que el usuario a eliminar no tenga información sensible.
 */
@Controller
@RequestMapping("/usuarios")
public class ControllerUsuarios {
    
    @Autowired
    private RepoUsuario repoUsuario;
    
    /**
     * Endpoint: /usuarios/ (GET)
     * Muestra todos los usuarios registrados.
     * 
     * @param modelo Modelo de la vista.
     * @return Vista de la lista de usuarios.
     */
    @GetMapping("/")
    public String findAll(Model modelo) {
        List<Usuario> usuarios = repoUsuario.findAll();
        modelo.addAttribute("usuarios", usuarios);
        return "usuarios/usuarios";
    }

    /**
     * Endpoint: /usuarios (GET)
     * Endpoint alternativo para la lista de usuarios.
     * 
     * @param modelo Modelo de la vista.
     * @return Vista de la lista de usuarios.
     */
    @GetMapping("")
    public String findAllUsers(Model modelo) {
        return findAll(modelo);
    }

    /**
     * Endpoint: /usuarios/add (GET)
     * Muestra el formulario para añadir un usuario nuevo.
     * Escoge también el rol del usuario.
     * 
     * @param modelo Modelo de la vista.
     * @return Vista del formulario de añadir usuario.
     */
    @GetMapping("/add")
    public String addUserForm(Model modelo) {
        modelo.addAttribute("usuario", new Usuario());
        Set<Rol> roles = Set.of(Rol.values());
        modelo.addAttribute("roles", roles);
        return "usuarios/add";
    }
    
    /**
     * Endpoint: /usuarios/add (POST)
     * Añade un usuario nuevo a la base de datos.
     * Escoge también el rol del usuario.
     * 
     * @param usuario Usuario a añadir.
     * @return Redirigir a la lista total de usuarios.
     */
    @PostMapping("/add")
    public String addUser(
        @ModelAttribute("usuario") @NonNull Usuario usuario) {
        // Actualizar la lista de usuarios con el nuevo usuario
        // Codificar la contraseña:
        BCryptPasswordEncoder passwords  = new BCryptPasswordEncoder();
        usuario.setPassword(passwords.encode(usuario.getPassword()));
        // Habilitar el usuario:
        usuario.setEnabled(true);
        repoUsuario.save(usuario);
        return "redirect:/usuarios";
    }
    
    /**
     * Endpoint: /usuarios/edit/{id} (GET)
     * Formulario para editar usuario. Se hace POST a add.
     * 
     * @param modelo Modelo de la vista.
     * @param id ID del usuario a editar.
     * @return Vista de edición del usuario.
     */
    @GetMapping("/edit/{id}")
    public String editUserForm(
            Model modelo, @PathVariable("id") @NonNull Long id) {
        Optional<Usuario> usuarioAEditar = repoUsuario.findById(id);
        if (!usuarioAEditar.isPresent()) {
            modelo.addAttribute("titulo", 
                    " - Error al editar usuario - ");
            modelo.addAttribute("mensaje",
                    " - Atención: El usuario indicado no existe - ");
            return "error";
        } else {
            Set<Rol> roles = Set.of(Rol.values());
            modelo.addAttribute("usuario", usuarioAEditar.get());
            modelo.addAttribute("roles", roles);
        }
        return "usuarios/edit";
    }

    /**
     * Endpoint: /usuarios/edit (POST)
     * Edita un usuario de la base de datos.
     * 
     * @param usuario Usuario a editar.
     * @return Redirigir a la lista de usuarios.
     */
    @PostMapping("/edit")
    public String editUser(
            @ModelAttribute("usuario") @NonNull Usuario usuario) {
        // Actualizar la lista de usuarios con el usuario editado
        // Codificar la contraseña:
        BCryptPasswordEncoder passwords  = new BCryptPasswordEncoder();
        usuario.setPassword(passwords.encode(usuario.getPassword()));
        // Habilitar el usuario:
        usuario.setEnabled(true);
        repoUsuario.save(usuario);
        return "redirect:/usuarios";
    }

    /**
     * Endpoint: /usuarios/delete (GET)
     * Devuelve el formulario de eliminar un usuario de la base de datos.
     * 
     * @param modelo Modelo de la vista.
     * @param id ID del usuario a eliminar.
     * @return Vista del formulario de eliminar un usuario.
     */
    @GetMapping("/delete/{id}")
    public String deleteUserForm(
            Model modelo, @PathVariable("id") @NonNull Long id) {
        Optional<Usuario> usuarioABorrar = repoUsuario.findById(id);
        if (!usuarioABorrar.isPresent()) {
            modelo.addAttribute("titulo", 
                    " - Error al eliminar usuario - ");
            modelo.addAttribute("mensaje",
                    " - Atención: El usuario indicado no existe - ");
            return "error";
        } else {
            Integer anunciosPublicados = repoUsuario.findById(
                usuarioABorrar.get().getId()).get().getAnunciosEnVenta().size();

            modelo.addAttribute("usuario", usuarioABorrar.get());
            
            // Los administradores no pueden ser eliminados.
            if (usuarioABorrar.get().getRol().equals("ADMIN")) {
                modelo.addAttribute("titulo",
                " - Error al borrar usuario - ");
                modelo.addAttribute("mensaje",
                "El usuario 'Administrador' no puede ser eliminado ");
                return "error";
            }
                
            // Los vendedores con anuncios publicados no pueden ser eliminados.
            if (anunciosPublicados > 0) {
                modelo.addAttribute("titulo",
                        " - Error al borrar usuario - ");
                modelo.addAttribute("mensaje",
                        "El usuario tiene anuncios publicados. "
                        + "Elimine los anuncios antes de borrar el usuario.");
                return "error";
            }

            // Los usuarios inalterables (con compras, ventas, reservas, 
            // alquileres y/o mensajes realizados) no pueden ser eliminados.
            if (usuarioABorrar.get().getInalterable()) {
                modelo.addAttribute("titulo",
                        " - Error al borrar usuario - ");
                modelo.addAttribute("mensaje",
                        "El usuario tiene compras, ventas, reservas, "
                        + "alquileres y/o mensajes en su historial.\n "
                        + "No puede ser eliminado.");
                return "error"; 
            }
        }
        
        return "usuarios/delete";
    }

    /**
     * Endpoint: /usuarios/delete (POST)
     * Elimina un usuario de la base de datos.
     * 
     * @param id Identificador del usuario a eliminar.
     * @return Redirigir a la lista de usuarios.
     */
    @PostMapping("/delete")
    public String deleteUser(
            Model modelo, @RequestParam("id") @NonNull Long id) {
        Optional<Usuario> usuarioABorrar = repoUsuario.findById(id);
        if (!usuarioABorrar.isPresent()) {
            modelo.addAttribute("titulo", 
                    " - Error al eliminar usuario - ");
            modelo.addAttribute("mensaje",
                    " - Atención: El usuario indicado no existe - ");
            return "error";
        } else {
            // Los administradores no pueden ser eliminados.
            if (usuarioABorrar.get().getRol().equals("ADMIN")) {
                modelo.addAttribute("titulo",
                " - Error al borrar usuario - ");
                modelo.addAttribute("mensaje",
                "El usuario 'Administrador' no puede ser eliminado ");
                return "error";
            }
                
            // Los vendedores con anuncios publicados no pueden ser eliminados.
            if (usuarioABorrar.get().getAnunciosEnVenta().size() > 0) {
                modelo.addAttribute("titulo",
                        " - Error al borrar usuario - ");
                modelo.addAttribute("mensaje",
                        "El usuario tiene anuncios publicados. "
                        + "Elimine los anuncios antes de borrar el usuario.");
                return "error";
            }

            // Los usuarios inalterables (con compras, ventas, reservas, 
            // alquileres y/o mensajes realizados) no pueden ser eliminados.
            if (usuarioABorrar.get().getInalterable()) {
                modelo.addAttribute("titulo",
                        " - Error al borrar usuario - ");
                modelo.addAttribute("mensaje",
                        "El usuario tiene compras, ventas, reservas, "
                        + "alquileres y/o mensajes en su historial.\n "
                        + "No puede ser eliminado.");
                return "error"; 
            }
        }

        // Eliminar el usuario:
        modelo.addAttribute("usuario", usuarioABorrar.get());
        repoUsuario.delete(usuarioABorrar.get());
        return "redirect:/usuarios";
    }
    
    
}
