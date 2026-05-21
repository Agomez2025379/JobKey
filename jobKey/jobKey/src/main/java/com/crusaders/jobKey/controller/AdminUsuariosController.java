package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.usuarios.UsuarioUpdateRequest;
import com.crusaders.jobKey.enums.EUsuarioRol;
import com.crusaders.jobKey.service.services.UsuariosService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/usuarios")
public class AdminUsuariosController {

    private final UsuariosService service;

    public AdminUsuariosController(UsuariosService service) {
        this.service = service;
    }

    // ── GET /admin/usuarios  →  lista ────────────────────
    @GetMapping
    public String listar(Model model) {
        var usuarios = service.listar();

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("totalUsuarios",     usuarios.size());
        model.addAttribute("totalAdmins",       contarPorRol(usuarios, EUsuarioRol.ADMIN));
        model.addAttribute("totalEmpresas",     contarPorRol(usuarios, EUsuarioRol.EMPRESA));
        model.addAttribute("totalCandidatos",   contarPorRol(usuarios, EUsuarioRol.CANDIDATO));
        model.addAttribute("totalInstituciones",contarPorRol(usuarios, EUsuarioRol.INSTITUCION));

        return "admin/usuarios";
    }

    // ── GET /admin/usuarios/editar/{id}  →  formulario ───
    @GetMapping("/editar/{id}")
    public String editarPagina(@PathVariable Integer id, Model model) {
        model.addAttribute("usuario", service.obtenerPorId(id));
        model.addAttribute("roles", EUsuarioRol.values());
        return "admin/usuarios-editar";
    }

    // ── POST /admin/usuarios/editar  →  procesar ─────────
    @PostMapping("/editar")
    public String editar(
            @RequestParam Integer id,
            @Valid @ModelAttribute UsuarioUpdateRequest request,
            RedirectAttributes redirectAttrs
    ) {
        try {
            service.actualizar(id, request);
            redirectAttrs.addFlashAttribute("successMsg", "Usuario actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/admin/usuarios/editar/" + id;
        }
        return "redirect:/admin/usuarios";
    }

    // ── GET /admin/usuarios/eliminar/{id}  →  confirmación
    @GetMapping("/eliminar/{id}")
    public String eliminarPagina(@PathVariable Integer id, Model model) {
        model.addAttribute("usuario", service.obtenerPorId(id));
        return "admin/usuarios-eliminar";
    }

    // ── POST /admin/usuarios/eliminar  →  procesar ───────
    @PostMapping("/eliminar")
    public String eliminar(
            @RequestParam Integer id,
            RedirectAttributes redirectAttrs
    ) {
        service.eliminar(id);
        redirectAttrs.addFlashAttribute("successMsg", "Usuario eliminado correctamente.");
        return "redirect:/admin/usuarios";
    }

    // ── Helper ───────────────────────────────────────────
    private long contarPorRol(
            java.util.List<com.crusaders.jobKey.dto.usuarios.UsuarioResponse> lista,
            EUsuarioRol rol
    ) {
        return lista.stream()
                .filter(u -> u.getRol() == rol)
                .count();
    }
}