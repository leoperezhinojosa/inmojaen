package com.iesvdc.project.inmojaen.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iesvdc.project.inmojaen.models.Anuncio;
import com.iesvdc.project.inmojaen.models.Favorito;
import com.iesvdc.project.inmojaen.models.Usuario;
import com.iesvdc.project.inmojaen.repositories.RepoAnuncio;
import com.iesvdc.project.inmojaen.repositories.RepoUsuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    private PasswordEncoder encoder;

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
     * Endpoint: /usuario/perfil (GET)
     * Método que obtiene el usuario que ha entrado del contexto
     * de la aplicación. Muestra la vista del perfil de usuario logueado.
     * 
     * @param modelo Modelo de la vista.
     * @return Usuario logueado.
     */
    @GetMapping("/perfil")
    public String getUser(Model modelo) {
        Usuario usuario = getLoggedUser();
        modelo.addAttribute("usuario", usuario);
        return "usuario/perfil";
    }

    /**
     * Endpoint: /usuario/editar (GET)
     * Muestra el formulario para editar el usuario que ha iniciado sesión.
     * 
     * @param modelo Modelo de la vista.
     * @return Vista del formulario para editar el usuario.
     */
    @GetMapping("/editar")
    public String editFormUsuario(Model modelo) {
        modelo.addAttribute("usuario", getLoggedUser());
        modelo.addAttribute("password", "");
        return "usuario/editar";
    }

    /**
     * Endpoint: /usuario/editar (POST)
     * Método que actualiza los datos del usuario que ha iniciado sesión.
     * 
     * @param modelo  Modelo de la vista.
     * @param usuario Datos actualizados del usuario.
     * @return Vista del usuario actualizado.
     */
    @PostMapping("/editar")
    public String editUsuario(Model modelo,
            @ModelAttribute Usuario usuarioEditado,
            @RequestParam("password") String password) {
        Usuario usuarioOriginal = getLoggedUser();

        // Validar la contraseña actual
        if (!encoder.matches(password, usuarioOriginal.getPassword())) {
            modelo.addAttribute("error", "Contraseña incorrecta. Los cambios no han sido guardados.");
            modelo.addAttribute("usuario", usuarioOriginal);
            return "usuario/editar";
        }

        // Actualizar los datos si la contraseña es válida
        if (usuarioEditado.getUsername() != null)
            usuarioOriginal.setUsername(usuarioEditado.getUsername());
        if (usuarioEditado.getEmail() != null)
            usuarioOriginal.setEmail(usuarioEditado.getEmail());
        if (usuarioEditado.getNombre() != null)
            usuarioOriginal.setNombre(usuarioEditado.getNombre());
        if (usuarioEditado.getApellidos() != null)
            usuarioOriginal.setApellidos(usuarioEditado.getApellidos());
        if (usuarioEditado.getTelefono() != null)
            usuarioOriginal.setTelefono(usuarioEditado.getTelefono());

        repoUsuario.save(usuarioOriginal);
        return "redirect:/";
    }

    /**
     * Endpoint: /usuario/editarpass (GET)
     * Método para mostrar el formulario para comprobar la contraseña guardada
     * del usuario logueado.
     *
     * @param modelo Modelo de la vista.
     * @return Vista del formulario para comprobar la contraseña.
     */
    @GetMapping("/editarpass")
    public String getEditPassForm(Model modelo) {
        Usuario usuario = getLoggedUser();
        modelo.addAttribute("usuario", usuario);
        return "usuario/editarpass";
    }

    /**
     * Endpoint: /usuario/editarpass (POST)
     * Método que envía la contraseña escrita para comprobar si coincide con la
     * guardada.
     * Si la contraseña es correcta, se pasa a la página de cambiar contraseña.
     * Si la contraseña es incorrecta, se manda un mensaje de fallo.
     * 
     * @param usuario Usuario logeado.
     * @param model   Modelo de la vista.
     * @return Página de cambio de contraseña si es correcta / error si no es
     *         correcta.
     */
    @PostMapping("/editarpass")
    public String getEditPass(Model modelo,
            @ModelAttribute Usuario usuarioEditado,
            @RequestParam("password") String password) {
        Usuario usuarioLogueado = getLoggedUser();

        // Validar la contraseña actual
        if (!encoder.matches(password, usuarioLogueado.getPassword())) {
            modelo.addAttribute("error", "Contraseña incorrecta. Los cambios no han sido guardados.");
            modelo.addAttribute("usuario", usuarioLogueado);
            return "usuario/editarpass";
        }
        // Si es válida, pasar a la página de introducir nueva contraseña:
        return "usuario/cambiarpass";
    }

    /**
     * Endpoint: /usuario/cambiarpass (GET)
     * Método para mostrar el formulario para cambiar la contraseña guardada
     * del usuario logueado.
     * 
     * @param usuario Usuario logueado.
     * @param model   Modelo de la vista.
     * @return Vista del formulario.
     */
    @GetMapping("/cambiarpass")
    public String getChangePassForm(Model modelo,
            @RequestParam("password") String password) {
        Usuario usuario = getLoggedUser();
        modelo.addAttribute("usuario", usuario);
        return "usuario/cambiarpass";
    }

    /**
     * Endpoint: /usuario/cambiarpass (POST)
     * Método para cambiar la contraseña del usuario logueado.
     * 
     * @param id    ID del usuario logueado.
     * @param model Modelo de la vista.
     * @return Contraseña del usuario actualizada. Vista del perfil.
     */
    @PostMapping("/cambiarpass")
    public String getChangePass(Model modelo,
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            RedirectAttributes redirectAttributes) {
        Usuario usuario = getLoggedUser();

        // Validar que la nueva contraseña no esté vacía y que coincida con la confirmación:
        if (newPassword == null || newPassword.isEmpty() || !newPassword.equals(confirmPassword)) {
            modelo.addAttribute("error", "Las contraseñas no coinciden o son inválidas.");
            modelo.addAttribute("usuario", usuario);
            return "usuario/cambiarpass";
        }

        // Validar la longitud y seguridad de la contraseña:
        if (newPassword.length() < 8) {
            modelo.addAttribute("error", "La nueva contraseña debe tener al menos 8 caracteres.");
            modelo.addAttribute("usuario", usuario);
            return "usuario/cambiarpass";
        }

        // Cifrar la nueva contraseña:
        String hashedPassword = encoder.encode(newPassword);

        // Actualizar la contraseña del usuario:
        usuario.setPassword(hashedPassword);
        repoUsuario.save(usuario);

        // Añadir mensaje flash:
        redirectAttributes.addFlashAttribute("success", "La contraseña se ha cambiado correctamente.");
        return "redirect:/usuario/perfil";
    }

    /**
     * Endpoint: /usuario/{id}/mensajes (GET)
     * Muestra los mensajes de un usuario.
     * 
     * @param id    ID del usuario.
     * @param model Modelo de la vista.
     * @return Vista de los mensajes del usuario con el ID indicado.
     */
    @GetMapping("/usuario/{id}/mensajes")
    public String obtenerMensajes(@PathVariable Long id, Model model) {
        Optional<Usuario> usuario = repoUsuario.findById(id);
        if (usuario.isPresent()) {
            Usuario user = usuario.get();
            model.addAttribute("mensajesEnviados", user.getMensajesByEmisor());
            model.addAttribute("mensajesRecibidos", user.getMensajesByReceptor());
        } else {
            model.addAttribute("error", "Usuario no encontrado");
        }
        return "mensajes";
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

        return "usuario/favoritos";
    }

}
