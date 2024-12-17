package com.iesvdc.project.inmojaen.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iesvdc.project.inmojaen.models.Anuncio;
import com.iesvdc.project.inmojaen.models.Imagen;
import com.iesvdc.project.inmojaen.models.Usuario;
import com.iesvdc.project.inmojaen.repositories.RepoAnuncio;
import com.iesvdc.project.inmojaen.repositories.RepoUsuario;

import lombok.NonNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    private RepoAnuncio repoAnuncio;

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

        // Validar que la nueva contraseña no esté vacía y que coincida con la
        // confirmación:
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
     * Endpoint: /usuario/publicados (GET)
     * Método para traer los anuncios activos publicados por el usuario logueado.
     * Nota: Si el usuario los borra, sólo se desactivarán.
     * 
     * @param modelo Modelo de la vista.
     * @param id     ID del usuario logueado.
     * @return Vista de los anuncios activos.
     */
    @GetMapping("/publicados")
    public String getPublicados(Model modelo) {
        Usuario usuarioLogueado = getLoggedUser();
        List<Anuncio> anunciosActivos = usuarioLogueado.getAnunciosEnVenta()
                .stream()
                .filter(Anuncio::getActivo) // Filtra los anuncios activos
                .toList(); // Convierte el stream en una lista

        modelo.addAttribute("anuncios", anunciosActivos);
        modelo.addAttribute("usuario", usuarioLogueado);
        return "usuario/publicados";
    }

    /**
     * Endpoint: /usuario/info/{id} (GET)
     * Método para mostrar la información de un anuncio del usuario logueado.
     * 
     * @param modelo Modelo de la vista.
     * @param id     ID del anuncio a mostrar.
     * @return Vista detallada del anuncio.
     */
    @GetMapping("/info/{id}")
    public String getAnuncioInfo(Model modelo, @PathVariable("id") @NonNull Long id) {
        Optional<Anuncio> anuncio = repoAnuncio.findById(id);

        Usuario usuario = getLoggedUser();

        if (!anuncio.isPresent() || !anuncio.get().getActivo()) {
            modelo.addAttribute("titulo", " - Error al mostrar anuncio - ");
            modelo.addAttribute("mensaje", "El anuncio con el id " + id + " no existe.");
            return "error";
        } else {
            Anuncio anuncioObtenido = anuncio.get();

            // Obtener las imágenes asociadas al anuncio:
            List<Imagen> imagenes = anuncioObtenido.getImagenes();

            // Añadir datos al modelo:
            modelo.addAttribute("usuario", usuario);
            modelo.addAttribute("anuncio", anuncioObtenido);
            modelo.addAttribute("imagenes", imagenes);

            return "usuario/info";
        }
    }

    /**
     * Endpoint: /usuario/crear (GET)
     * Muestra el formulario para añadir un anuncio nuevo.
     * 
     * @param modelo Modelo de la vista.
     * @return Vista del formulario de añadir anuncio.
     */
    @GetMapping("/crear")
    public String addCreationForm(Model modelo) {
        Usuario usuario = getLoggedUser();
        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("anuncio", new Anuncio());
        return "usuario/crear";
    }

    /**
     * Endpoint: /usuario/crear (POST)
     * Añade un anuncio nuevo del usuario logueado a la base de datos.
     * 
     * @param anuncio Anuncio a añadir.
     * @return Redirección a la lista de anuncios del usuario.
     */
    @PostMapping("/crear")
    public String addCreation(
            @ModelAttribute("anuncio") @NonNull Anuncio anuncio) {
        // Añadir un nuevo anuncio al usuario logueado:
        anuncio.setActivo(true);
        anuncio.setFechaPublicacion(LocalDate.now());
        anuncio.setVendido(false);
        anuncio.setReservado(false);
        anuncio.setVisto(false);
        // Se asigna al usuario logueado:
        anuncio.setVendedor(getLoggedUser());
        repoAnuncio.save(anuncio);
        return "redirect:/usuario/publicados";
    }

    /**
     * Endpoint: /usuario/editar-anuncio/{id} (GET)
     * Muestra el formulario para editar un anuncio del usuario logueado.
     * 
     * @param modelo  Modelo de la vista.
     * @param usuario Usuario logueado.
     * @param id      ID del anuncio a editar.
     * @return Vista de edición del anuncio.
     */
    @GetMapping("/editar-anuncio/{id}")
    public String editAnuncioForm(
            Model modelo, @PathVariable("id") Long id) {
        Optional<Anuncio> anuncioAEditar = repoAnuncio.findById(id);
        Usuario usuario = getLoggedUser();
        if (!anuncioAEditar.isPresent()) {
            modelo.addAttribute("titulo",
                    " - Error al editar anuncio - ");
            modelo.addAttribute("mensaje",
                    " - Atención: El anuncio con ID " + id + " no existe - ");
            return "error";
        } else {
            modelo.addAttribute("usuario", usuario);
            modelo.addAttribute("anuncio", anuncioAEditar.get());
        }

        return "usuario/editar-anuncio";
    }

    /**
     * Endpoint: /usuario/editar-anuncio (POST)
     * Edita un anuncio existente del usuario logueado en la base de datos.
     * 
     * @param anuncio Anuncio a editar.
     * @return Redirección a la lista total de anuncios.
     */
    @PostMapping("/editar-anuncio")
    public String editarAnuncio(@ModelAttribute Anuncio anuncio) {

        // Validar que el anuncio ya existe en la base de datos
        Anuncio anuncioExistente = repoAnuncio.findById(anuncio.getId())
                .orElseThrow(() -> new RuntimeException("Anuncio no encontrado"));

        Usuario usuario = getLoggedUser();

        // Actualizar los campos del anuncio existente
        anuncioExistente.setTitulo(anuncio.getTitulo());
        anuncioExistente.setDescripcion(anuncio.getDescripcion());
        anuncioExistente.setPrecio(anuncio.getPrecio());
        anuncioExistente.setHabitaciones(anuncio.getHabitaciones());
        anuncioExistente.setBanos(anuncio.getBanos());
        anuncioExistente.setSuperficie(anuncio.getSuperficie());
        anuncioExistente.setDireccion(anuncio.getDireccion());
        anuncioExistente.setVendedor(usuario);

        // Guardar el anuncio actualizado
        repoAnuncio.save(anuncioExistente);

        return "redirect:/usuario/publicados";
    }

    /**
     * Endpoint: /usuario/borrar-anuncio/{id} (GET)
     * Muestra el formulario para eliminar/desactivar un anuncio del usuario
     * logueado.
     * 
     * @param id ID del anuncio a eliminar/desactivar.
     * @return Metodo POST para eliminar el anuncio.
     */
    @GetMapping("/borrar-anuncio/{id}")
    public String deleteAdvertisementForm(
            Model modelo, @PathVariable("id") @NonNull Long id) {
        Optional<Anuncio> anuncioAEliminar = repoAnuncio.findById(id);
        Usuario usuario = getLoggedUser();
        if (!anuncioAEliminar.isPresent()) {
            modelo.addAttribute("titulo",
                    " - Error al eliminar anuncio - ");
            modelo.addAttribute("mensaje",
                    " - Atención: El anuncio con ID " + id + " no existe - ");
            return "error";
        } else {
            // Los anuncios reservados o comprados no pueden eliminarse/desactivarse.
            if (anuncioAEliminar.get().getReservado() || anuncioAEliminar.get().getVendido()) {
                modelo.addAttribute("titulo",
                        " - Error al eliminar anuncio - ");
                modelo.addAttribute("mensaje",
                        " - Atención: El anuncio con ID " + id + " no puede eliminarse - ");
                return "error";
            } else {
                modelo.addAttribute("usuario", usuario);
                modelo.addAttribute("anuncio", anuncioAEliminar.get());
            }
        }
        return "usuario/borrar-anuncio";
    }

    /**
     * Endpoint: /borrar-anuncio (POST)
     * Desactiva un anuncio.
     * Nota: Este método puede reformularse para activar/desactivar anuncios.
     * 
     * @param id Identificador del anuncio a activar o desactivar un anuncio del
     *           usuario logueado.
     * @return Redirigir a la lista de anuncios publicados por el usuario.
     */
    @PostMapping("/borrar-anuncio")
    public String unactivateAdvertisement(
            Model modelo, @RequestParam("id") @NonNull Long id) {
        Optional<Anuncio> anuncio = repoAnuncio.findById(id);
        if (!anuncio.isPresent()) {
            modelo.addAttribute("titulo",
                    " - Error en el borrado de anuncio - ");
            // " - Error en el manejo de activación de anuncio - ");
            modelo.addAttribute("mensaje",
                    " - Atención: El anuncio indicado no existe - ");
            return "error";
        } else {
            // Se desactiva el anuncio, con lo que no aparece después:
            anuncio.get().setActivo(false);
            repoAnuncio.save(anuncio.get());
        }
        return "redirect:/usuario/publicados";
    }

    /**
     * Endpoint: /images/{id} (GET)
     * Muestra una página con las imágenes asociadas a un anuncio.
     * 
     * @param id Identificador del anuncio.
     * @return Imagen asociada al anuncio.
     */
    @GetMapping("/images/{id}")
    public String getImages(Model modelo, @PathVariable("id") @NonNull Long id) {
        Optional<Anuncio> anuncio = repoAnuncio.findById(id);
        if (!anuncio.isPresent()) {
            modelo.addAttribute("titulo", "Error al mostrar anuncio");
            modelo.addAttribute("mensaje", "El anuncio con el id " + id + " no existe");
            return "error";
        } else {
            List<Imagen> imagenes = anuncio.get().getImagenes();
            System.out.println("Imágenes asociadas: " + imagenes);
            modelo.addAttribute("anuncio", anuncio.get());
            modelo.addAttribute("imagenes", imagenes);
            return "usuario/anuncio-images";
        }
    }

    /**
     * Endpoint: /usuario/reservar-anuncio/{id}
     * Método para cambiar el estado de un anuncio a reservado por su anunciante.
     * 
     * @param id ID del anuncio a reservar.
     * @return cambio de estado del anuncio a reservado.
     */
    @PostMapping("/reservar-anuncio/{id}")
    public String reservarAnuncio(@PathVariable Long id) {
        Anuncio anuncio = repoAnuncio.findById(id)
                .orElseThrow(() -> new RuntimeException("Anuncio no encontrado"));
        anuncio.setFechaReserva(LocalDate.now()); // Guarda la fecha de reserva
        anuncio.setReservado(true); // Cambia el estado a reservado
        anuncio.setVendido(false);
        repoAnuncio.save(anuncio);

        return "redirect:/usuario/publicados";
    }

    /**
     * Endpoint: /usuario/vender-anuncio/{id}
     * @param id ID del anuncio a vender.
     * Método para cambiar el estado de un anuncio a vendido por su anunciante.
     * 
     * @return cambio de estado del anuncio a vendido.
     */
    @PostMapping("/vender-anuncio/{id}")
    public String venderAnuncio(@PathVariable Long id) {
        Anuncio anuncio = repoAnuncio.findById(id)
                .orElseThrow(() -> new RuntimeException("Anuncio no encontrado"));
        anuncio.setFechaVenta(LocalDate.now()); // Guarda la fecha de venta
        anuncio.setVendido(true); // Cambia el estado a vendido
        anuncio.setReservado(false);
        repoAnuncio.save(anuncio);

        return "redirect:/usuario/publicados";
    }

}
