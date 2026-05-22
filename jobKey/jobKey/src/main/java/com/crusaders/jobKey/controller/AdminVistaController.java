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
@RequestMapping("/admins")
public class AdminVistaController {

    private final AdminsService service;

    public AdminVistaController(AdminsService service) {
        this.service = service;
    }

    // ── GET /admin/admins  →  lista ──
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("admins", service.listar());
        return "admins";
    }

    // ── GET /admin formulario crear ─────
    @GetMapping("/nuevo")
    public String nuevoPagina(Model model) {
        model.addAttribute("adminRequest", new AdminRequest());
        return "Adminscrear";
    }

    // ── POST /admin/crear  →  procesar crear ───
    @PostMapping("/crear")
    public String crear(
            @Valid @ModelAttribute AdminRequest request,
            RedirectAttributes redirectAttrs
    ) {
        try {
            service.crear(request);
            redirectAttrs.addFlashAttribute("successMsg", "Admin successfully updated.");
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/admins/nuevo";
        }
        return "redirect:/admins";
    }

    // ── GET /admin/editar/{id}  →  formulario editar
    @GetMapping("/editar/{id}")
    public String editarPagina(@PathVariable Integer id, Model model) {
        model.addAttribute("admin", service.obtenerPorId(id));
        return "Adminseditar";
    }

    // ── POST /admin/editar  →  procesar editar ────
    @PostMapping("/editar")
    public String editar(
            @RequestParam Integer id,
            @Valid @ModelAttribute AdminUpdateRequest request,
            RedirectAttributes redirectAttrs
    ) {
        try {
            service.actualizar(id, request);
            redirectAttrs.addFlashAttribute("successMsg", "Admin successfully created.");
        } catch (IllegalArgumentException e) {
            redirectAttrs.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/admins/editar/" + id;
        }
        return "redirect:/admins";
    }

    // ── GET /admin/eliminar/{id}  →  confirmación ─
    @GetMapping("/eliminar/{id}")
    public String eliminarPagina(@PathVariable Integer id, Model model) {
        model.addAttribute("admin", service.obtenerPorId(id));
        return "Adminseliminar";
    }

    // ── POST /admin/admins/eliminar  →  procesar eliminar ─
    @PostMapping("/eliminar")
    public String eliminar(
            @RequestParam Integer id,
            RedirectAttributes redirectAttrs
    ) {
        service.eliminar(id);
        redirectAttrs.addFlashAttribute("successMsg", "Admin eliminado correctamente.");
        return "redirect:/admins";
    }
}