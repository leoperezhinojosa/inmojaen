package com.iesvdc.project.inmojaen.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.iesvdc.project.inmojaen.repositories.RepoRol;
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

    @Autowired
    private RepoRol repoRol;

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
     * Endpoint: /usuarios/info/{id} (GET)
     * Muestra la información de un usuario.
     * 
     * @param modelo
     * @return Vista de la información completa del usuario.
     */
    @GetMapping("/info/{id}")
    public String getUserInfo(Model modelo, @PathVariable("id") @NonNull Long id) {
        Optional<Usuario> usuario = repoUsuario.findById(id);
        if (!usuario.isPresent()) {
            modelo.addAttribute("titulo", "Error al mostrar usuario");
            modelo.addAttribute("mensaje", "El usuario con el id " + id + " no existe");
            return "error";
        } else {
            modelo.addAttribute("usuario", usuario.get());
            return "usuarios/info";
        }
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
        Set<Rol> rolesSet = new HashSet<>(repoRol.findAll());
        List<Rol> roles = new ArrayList<>(rolesSet);
        Rol rol = new Rol();
        Long rolId = rol.getId();
        modelo.addAttribute("roles", roles);
        modelo.addAttribute("rolId", rolId);
        modelo.addAttribute("usuario", new Usuario());
        return "usuarios/add";
    }

    /**
     * Endpoint: /usuarios/add (POST)
     * Añade un usuario nuevo a la base de datos.
     * Escoge también el rol del usuario.
     * 
     * @param usuario Usuario a añadir.
     * @param rol     Rol del usuario a añadir.
     * @return Redirigir a la lista total de usuarios.
     */
    @PostMapping("/add")
    public String addUser(
            Model modelo, @ModelAttribute("usuario") @NonNull Usuario usuario, @RequestParam("rolId") Long rolId) {

        // Comprobar si el username ya existe:
        if (!repoUsuario.findByUsername(usuario.getUsername()).isEmpty()) {
            modelo.addAttribute("titulo",
                    " - Error al crear usuario - ");
            modelo.addAttribute("mensaje",
                    " - Atención: El Nombre de Usuario ya está registrado - ");
            return "error";
        }

        // Comprobar si el email ya existe:
        if (!repoUsuario.findByEmail(usuario.getEmail()).isEmpty()) {
            modelo.addAttribute("titulo",
                    " - Error al crear usuario - ");
            modelo.addAttribute("mensaje",
                    " - Atención: El Email ya está registrado - ");
            return "error";
        }

        // Recuperar el Rol desde la base de datos
        Rol rol = repoRol.findById(rolId)
                .orElseThrow(() -> new IllegalArgumentException("Rol no válido"));
        usuario.setRol(rol);

        // Codificar la contraseña:
        BCryptPasswordEncoder passwords = new BCryptPasswordEncoder();
        usuario.setPassword(passwords.encode(usuario.getPassword()));
        // Setear las características del usuario según su rol:
        if (rolId == 1) {
            usuario.setInalterable(true);
            usuario.setPremium(false);
        } else {
            usuario.setInalterable(false);
            if (rolId == 2) {
                usuario.setPremium(true);
            } else {
                usuario.setPremium(false);
            }
        }

        // Habilitar el usuario:
        usuario.setEnabled(true);
        // Actualizar la lista de usuarios con el nuevo usuario
        repoUsuario.save(usuario);
        return "redirect:/usuarios";
    }

    /**
     * Endpoint: /usuarios/edit/{id} (GET)
     * Formulario para editar usuario. Se hace POST a add.
     * 
     * @param modelo Modelo de la vista.
     * @param id     ID del usuario a editar.
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
            List<Rol> roles = repoRol.findAll();
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
        BCryptPasswordEncoder passwords = new BCryptPasswordEncoder();
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
     * @param id     ID del usuario a eliminar.
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

            // Todo: ¿Es necesario esta comprobación? ¿Basta con que sean inalterables?
            // Los administradores no pueden ser eliminados.
            // if (usuarioABorrar.get().getRol().equals("ADMIN")) {
            // modelo.addAttribute("titulo",
            // " - Error al borrar usuario - ");
            // modelo.addAttribute("mensaje",
            // "El usuario 'Administrador' no puede ser eliminado ");
            // return "error";
            // }

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
                        " - Error al borrar usuario: Inalterable - ");
                modelo.addAttribute("mensaje",
                        "El usuario es administrador. "
                                // + "o tiene compras, ventas, reservas, "
                                // + "alquileres y/o mensajes en su historial.\n "
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
            Model modelo, @RequestParam @NonNull Long id) {
        Optional<Usuario> usuarioABorrar = repoUsuario.findById(id);
        if (!usuarioABorrar.isPresent()) {
            modelo.addAttribute("titulo",
                    " - Error al eliminar usuario - ");
            modelo.addAttribute("mensaje",
                    " - Atención: El usuario indicado no existe - ");
            return "error";
        }

        // Todo: ¿Es necesario esta comprobación? ¿Basta con que sean inalterables?
        // Los administradores no pueden ser eliminados.
        // if (usuarioABorrar.get().getRol().getRol().equals("ADMIN")) {
        // modelo.addAttribute("titulo",
        // " - Error al borrar usuario - ");
        // modelo.addAttribute("mensaje",
        // "El usuario de tipo 'Administrador' no puede ser eliminado ");
        // return "error";
        // }

        // Los vendedores con anuncios publicados no pueden ser eliminados. (Nota:
        // activar después de la asignación de anuncios al usuario)
        // if (usuarioABorrar.get().getAnunciosEnVenta().size() > 0) {
        // modelo.addAttribute("titulo",
        // " - Error al borrar usuario - ");
        // modelo.addAttribute("mensaje",
        // "El usuario tiene anuncios publicados. "
        // + "Elimine los anuncios antes de borrar el usuario.");
        // return "error";
        // }

        // Los usuarios inalterables (administradores) no pueden ser eliminados.
        if (usuarioABorrar.get().getInalterable()) {
            modelo.addAttribute("titulo",
                    " - Error al borrar usuario - ");
            modelo.addAttribute("mensaje",
                    "El usuario es administrador. No puede ser eliminado.");
            return "error";
        }

        try {
            modelo.addAttribute("usuario", usuarioABorrar.get());
            modelo.addAttribute("titulo",
                    " - El usuario ha sido eliminado - ");
            // Eliminar el usuario:
            repoUsuario.delete(usuarioABorrar.get());
            
        } catch (Exception e) {
            modelo.addAttribute("titulo",
                    " - Error al borrar usuario - ");
            modelo.addAttribute("mensaje",
                    "El usuario tiene anuncios publicados. "
                            + "Elimine los anuncios antes de borrar el usuario.");
            return "error";
        }
        return "redirect:/usuarios";
    }

    /**
     * Endpoint: /activate (POST)
     * Activa o desactiva un usuario.
     * 
     * @param id Identificador del usuario a activar o desactivar.
     * @return Redirigir a la lista de usuarios.
     */
    @PostMapping("/activate")
    public String activateUser(
            Model modelo, @RequestParam("id") @NonNull Long id) {
        Optional<Usuario> usuario = repoUsuario.findById(id);
        if (!usuario.isPresent()) {
            modelo.addAttribute("titulo",
                    " - Error en el manejo de activación de usuario - ");
            modelo.addAttribute("mensaje",
                    " - Atención: El usuario indicado no existe - ");
            return "error";
        } else {
            // Todo: ¿Es necesaria esta comprobación? Puede dar un aspecto poco intuitivo.
            if (usuario.get().getInalterable()) {
                modelo.addAttribute("titulo",
                        " - Error: Desactivación de usuario anulada - ");
                modelo.addAttribute("mensaje", " - Atención: "
                        + "El usuario indicado es inalterable y no puede ser desactivado - ");
                return "error";
            } else {
                if (usuario.get().getEnabled()) {
                    usuario.get().setEnabled(false);
                } else {
                    usuario.get().setEnabled(true);
                }
                repoUsuario.save(usuario.get());
            }
        }
        return "redirect:/usuarios";
    }

}
