package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.entity.Usuarios;
import com.crusaders.jobKey.enums.EUsuarioRol;
import com.crusaders.jobKey.repository.UsuariosRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/usuarios")
public class AdminUsuariosController {

    private final UsuariosRepository usuariosRepository;

    public AdminUsuariosController(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    // ── GET /admin/usuarios ──────────────────────────────
    // Carga la lista completa + conteos por rol para las stats
    @GetMapping
    public String listar(Model model) {
        List<Usuarios> usuarios = usuariosRepository.findAll();

        model.addAttribute("usuarios", usuarios);

        // Conteos para las stat-cards del header
        model.addAttribute("totalUsuarios",    usuarios.size());
        model.addAttribute("totalCandidatos",  contarPorRol(usuarios, EUsuarioRol.CANDIDATO));
        model.addAttribute("totalEmpresas",    contarPorRol(usuarios, EUsuarioRol.EMPRESA));
        model.addAttribute("totalInstituciones", contarPorRol(usuarios, EUsuarioRol.INSTITUCION));

        return "admin/usuarios"; // → templates/admin/usuarios.html
    }

    // ── POST /admin/usuarios/editar ──────────────────────
    // Recibe idUsuario + email + rol desde el modal de edición
    @PostMapping("/editar")
    public String editar(
            @RequestParam Integer idUsuario,
            @RequestParam String email,
            @RequestParam EUsuarioRol rol,
            RedirectAttributes redirectAttrs
    ) {
        usuariosRepository.findById(idUsuario).ifPresentOrElse(
                usuario -> {
                    usuario.setEmail(email);
                    usuario.setRol(rol);
                    usuariosRepository.save(usuario);
                    redirectAttrs.addFlashAttribute("successMsg",
                            "Usuario #" + idUsuario + " actualizado correctamente.");
                },
                () -> redirectAttrs.addFlashAttribute("errorMsg",
                        "No se encontró el usuario con ID " + idUsuario + ".")
        );
        return "redirect:/admin/usuarios";
    }

    // ── POST /admin/usuarios/eliminar ────────────────────
    // Recibe idUsuario desde el modal de confirmación
    @PostMapping("/eliminar")
    public String eliminar(
            @RequestParam Integer idUsuario,
            RedirectAttributes redirectAttrs
    ) {
        if (usuariosRepository.existsById(idUsuario)) {
            usuariosRepository.deleteById(idUsuario);
            redirectAttrs.addFlashAttribute("successMsg",
                    "Usuario #" + idUsuario + " eliminado correctamente.");
        } else {
            redirectAttrs.addFlashAttribute("errorMsg",
                    "No se encontró el usuario con ID " + idUsuario + ".");
        }
        return "redirect:/admin/usuarios";
    }

    // ── Helper ───────────────────────────────────────────
    private long contarPorRol(List<Usuarios> lista, EUsuarioRol rol) {
        return lista.stream()
                .filter(u -> u.getRol() == rol)
                .count();
    }
}