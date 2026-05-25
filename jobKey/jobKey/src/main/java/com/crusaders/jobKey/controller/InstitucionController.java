package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.instituciones.InstitucionRequest;
import com.crusaders.jobKey.dto.instituciones.InstitucionResponse;
import com.crusaders.jobKey.entity.Usuarios;
import com.crusaders.jobKey.enums.EUsuarioRol;
import com.crusaders.jobKey.repository.DepartamentosRepository;
import com.crusaders.jobKey.repository.UsuariosRepository;
import com.crusaders.jobKey.service.services.InstitucionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String index(Model model, @RequestParam(required = false) Integer id, Authentication authentication) {
        if (id != null) {
            model.addAttribute("institucion", institucionService.obtenerPorId(id));
            return "institucion-detalle";
        }

        String email = authentication.getName();
        Usuarios usuario = usuarioRepository.findByEmail(email).orElse(null);
        boolean esAdmin = (usuario != null && usuario.getRol() == EUsuarioRol.ADMIN);

        model.addAttribute("listaInstituciones", institucionService.listarTodas());
        model.addAttribute("esAdmin", esAdmin);
        model.addAttribute("usuarioLogueadoId", (usuario != null) ? usuario.getIdUsuario() : null);

        return "instituciones";
    }

    @GetMapping("/nueva")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTITUCION')")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("institucion", new InstitucionRequest());
        model.addAttribute("accion", "crear");
        model.addAttribute("departamentos", departamentosRepository.findAll());
        return "formulario-institucion";
    }

    @PostMapping("/guardar")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTITUCION')")
    public String crearOActualizar(@ModelAttribute InstitucionRequest request, @RequestParam(required = false) Integer idInstitucion, RedirectAttributes redirectAttributes, Authentication auth) {
        try {
            Usuarios usuario = usuarioRepository.findByEmail(auth.getName()).orElse(null);
            if (idInstitucion != null) {
                InstitucionResponse existente = institucionService.obtenerPorId(idInstitucion);
                if (usuario.getRol() != EUsuarioRol.ADMIN && !existente.getUsuarioId().equals(usuario.getIdUsuario())) {
                    throw new RuntimeException("No tienes permiso");
                }
                institucionService.actualizarInstitucion(idInstitucion, request);
            } else {
                request.setUsuarioId(usuario.getIdUsuario());
                institucionService.crearInstitucion(request);
            }
            redirectAttributes.addFlashAttribute("success", "Operación exitosa");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/instituciones";
    }

    @GetMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTITUCION')")
    public String editar(@PathVariable Integer id, Model model, Authentication auth) {
        InstitucionResponse inst = institucionService.obtenerPorId(id);
        Usuarios user = usuarioRepository.findByEmail(auth.getName()).orElse(null);
        if (user.getRol() != EUsuarioRol.ADMIN && !inst.getUsuarioId().equals(user.getIdUsuario())) return "redirect:/instituciones";

        model.addAttribute("institucion", inst);
        model.addAttribute("accion", "editar");
        model.addAttribute("departamentos", departamentosRepository.findAll());
        return "formulario-institucion";
    }

    @GetMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'INSTITUCION')")
    public String eliminar(@PathVariable Integer id, Authentication auth, RedirectAttributes ra) {
        InstitucionResponse inst = institucionService.obtenerPorId(id);
        Usuarios user = usuarioRepository.findByEmail(auth.getName()).orElse(null);
        if (user.getRol() != EUsuarioRol.ADMIN && !inst.getUsuarioId().equals(user.getIdUsuario())) return "redirect:/instituciones";

        institucionService.eliminarInstitucion(id);
        ra.addFlashAttribute("success", "Eliminado correctamente");
        return "redirect:/instituciones";
    }
}