package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.admins.AdminRequest;
import com.crusaders.jobKey.dto.admins.AdminUpdateRequest;
import com.crusaders.jobKey.service.services.AdminsService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/admins")
public class AdminVistaController {

    private final AdminsService service;

    public AdminVistaController(AdminsService service) {
        this.service = service;
    }

    // ── GET /admin/admins  →  lista ──────────────────────
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("admins", service.listar());
        return "admin/admins";
    }

    // ── GET /admin/admins/nuevo  →  formulario crear ─────
    @GetMapping("/nuevo")
    public String nuevoPagina(Model model) {
        model.addAttribute("adminRequest", new AdminRequest());
        return "admin/admins-crear";
    }

    // ── POST /admin/admins/crear  →  procesar crear ──────
    @PostMapping("/crear")
    public String crear(
            @Valid @ModelAttribute AdminRequest request,
            RedirectAttributes redirectAttrs
    ) {
        try {
            service.crear(request);
            redirectAttrs.addFlashAttribute("successMsg", "Admin creado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/admin/admins/nuevo";
        }
        return "redirect:/admin/admins";
    }

    // ── GET /admin/admins/editar/{id}  →  formulario editar
    @GetMapping("/editar/{id}")
    public String editarPagina(@PathVariable Integer id, Model model) {
        model.addAttribute("admin", service.obtenerPorId(id));
        return "admin/admins-editar";
    }

    // ── POST /admin/admins/editar  →  procesar editar ────
    @PostMapping("/editar")
    public String editar(
            @RequestParam Integer id,
            @Valid @ModelAttribute AdminUpdateRequest request,
            RedirectAttributes redirectAttrs
    ) {
        try {
            service.actualizar(id, request);
            redirectAttrs.addFlashAttribute("successMsg", "Admin actualizado correctamente.");
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/admin/admins/editar/" + id;
        }
        return "redirect:/admin/admins";
    }

    // ── GET /admin/admins/eliminar/{id}  →  confirmación ─
    @GetMapping("/eliminar/{id}")
    public String eliminarPagina(@PathVariable Integer id, Model model) {
        model.addAttribute("admin", service.obtenerPorId(id));
        return "admin/admins-eliminar";
    }

    // ── POST /admin/admins/eliminar  →  procesar eliminar ─
    @PostMapping("/eliminar")
    public String eliminar(
            @RequestParam Integer id,
            RedirectAttributes redirectAttrs
    ) {
        service.eliminar(id);
        redirectAttrs.addFlashAttribute("successMsg", "Admin eliminado correctamente.");
        return "redirect:/admin/admins";
    }
}