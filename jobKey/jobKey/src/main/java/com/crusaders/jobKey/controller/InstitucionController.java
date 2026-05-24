package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.instituciones.InstitucionRequest;
import com.crusaders.jobKey.dto.instituciones.InstitucionResponse;
import com.crusaders.jobKey.entity.Usuarios;
import com.crusaders.jobKey.enums.EUsuarioRol;
import com.crusaders.jobKey.repository.DepartamentosRepository;
import com.crusaders.jobKey.repository.UsuariosRepository;
import com.crusaders.jobKey.service.services.InstitucionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/instituciones")
public class InstitucionController {

    private final InstitucionService institucionService;
    private final UsuariosRepository usuarioRepository;
    private final DepartamentosRepository departamentosRepository;

    public InstitucionController(InstitucionService institucionService,
                                 UsuariosRepository usuarioRepository,
                                 DepartamentosRepository departamentosRepository) {
        this.institucionService = institucionService;
        this.usuarioRepository = usuarioRepository;
        this.departamentosRepository = departamentosRepository;
    }

    @GetMapping
    public String index(Model model,
                        @RequestParam(required = false) Integer id,
                        Authentication authentication) {

        if (id != null) {
            InstitucionResponse institucion = institucionService.obtenerPorId(id);
            model.addAttribute("institucion", institucion);
            return "institucion-detalle";
        }

        List<InstitucionResponse> instituciones;

        if (authentication == null) {
            instituciones = institucionService.listarTodas();
            model.addAttribute("instituciones", instituciones);
            return "instituciones-cards";
        }

        String email = authentication.getName();
        Usuarios usuario = usuarioRepository.findByEmail(email).orElse(null);

        if (usuario == null) {
            instituciones = institucionService.listarTodas();
            model.addAttribute("instituciones", instituciones);
            return "instituciones-cards";
        }

        if (usuario.getRol() == EUsuarioRol.ADMIN) {
            instituciones = institucionService.listarTodas();
            model.addAttribute("instituciones", instituciones);
            model.addAttribute("puedeCrear", true);
            model.addAttribute("puedeEditar", true);
            return "instituciones";
        }

        if (usuario.getRol() == EUsuarioRol.INSTITUCION) {
            instituciones = institucionService.listarPorUsuario(usuario.getIdUsuario());
            model.addAttribute("instituciones", instituciones);
            model.addAttribute("puedeCrear", true);
            model.addAttribute("puedeEditar", true);
            return "instituciones";
        }

        instituciones = institucionService.listarTodas();
        model.addAttribute("instituciones", instituciones);
        return "instituciones-cards";
    }

    @GetMapping("/nueva")
    public String mostrarFormularioCrear(Model model, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        model.addAttribute("institucion", new InstitucionRequest());
        model.addAttribute("accion", "crear");
        model.addAttribute("departamentos", departamentosRepository.findAll());
        return "formulario-institucion";
    }

    @PostMapping("/guardar")
    public String crearOActualizar(@ModelAttribute InstitucionRequest request,
                                   @RequestParam(required = false) Integer idInstitucion,
                                   RedirectAttributes redirectAttributes,
                                   Authentication authentication) {
        try {
            if (authentication == null) {
                throw new RuntimeException("Debes iniciar sesión");
            }

            String email = authentication.getName();

            if (request.getEmail() != null && !request.getEmail().isEmpty()) {
                Usuarios usuario = usuarioRepository.findByEmail(request.getEmail())
                        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + request.getEmail()));
                request.setUsuarioId(usuario.getIdUsuario());
            }

            if (request.getUsuarioId() == null || request.getUsuarioId() == 0) {
                throw new RuntimeException("Debes proporcionar un email de usuario válido");
            }

            if (idInstitucion != null && idInstitucion > 0) {
                institucionService.actualizarInstitucion(idInstitucion, request);
                redirectAttributes.addFlashAttribute("success", "Institución actualizada exitosamente");
            } else {
                institucionService.crearInstitucion(request);
                redirectAttributes.addFlashAttribute("success", "Institución creada exitosamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/instituciones";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model, Authentication authentication) {
        if (authentication == null) {
            return "redirect:/login";
        }
        InstitucionResponse institucion = institucionService.obtenerPorId(id);
        model.addAttribute("institucion", institucion);
        model.addAttribute("accion", "editar");
        model.addAttribute("departamentos", departamentosRepository.findAll());
        return "formulario-institucion";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarInstitucion(@PathVariable Integer id,
                                      RedirectAttributes redirectAttributes) {
        try {
            institucionService.eliminarInstitucion(id);
            redirectAttributes.addFlashAttribute("success", "Institución eliminada exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la institución");
        }
        return "redirect:/instituciones";
    }
}