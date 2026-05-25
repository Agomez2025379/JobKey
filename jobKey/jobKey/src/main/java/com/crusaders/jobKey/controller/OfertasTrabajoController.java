package com.crusaders.jobKey.controller;

import com.crusaders.jobKey.dto.ofertas.OfertasTrabajoRequest;
import com.crusaders.jobKey.entity.Usuarios;
import com.crusaders.jobKey.enums.EModalidad;
import com.crusaders.jobKey.enums.EUsuarioRol;
import com.crusaders.jobKey.repository.UsuariosRepository;
import com.crusaders.jobKey.service.services.DepartamentosService;
import com.crusaders.jobKey.service.services.EmpresasService;
import com.crusaders.jobKey.service.services.OfertasTrabajoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ofertas")
@RequiredArgsConstructor
public class OfertasTrabajoController {

    private final OfertasTrabajoService service;
    private final DepartamentosService departamentosService;
    private final EmpresasService empresasService;
    private final UsuariosRepository usuariosRepository;

    @GetMapping
    public String listar(@RequestParam(required = false) String keyword, @RequestParam(required = false) Integer departamentoId, @RequestParam(required = false) EModalidad modalidad, Model model, Authentication auth) {
        model.addAttribute("ofertas", service.filtrar(keyword, departamentoId, modalidad));
        model.addAttribute("departamentos", departamentosService.listar());

        if (auth != null) {
            Usuarios user = usuariosRepository.findByEmail(auth.getName()).orElse(null);
            if (user != null) {
                model.addAttribute("esAdmin", (user.getRol() == EUsuarioRol.ADMIN));
                try {
                    model.addAttribute("idEmpresaUsuario", empresasService.obtenerIdEmpresaPorUsuario(user.getIdUsuario()));
                } catch (Exception e) {
                    model.addAttribute("idEmpresaUsuario", null);
                }
            }
        }
        return "ofertas-trabajo";
    }

    @GetMapping("/crear")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public String mostrarCrear(Model model) {
        model.addAttribute("oferta", new OfertasTrabajoRequest());
        model.addAttribute("departamentos", departamentosService.listar());
        model.addAttribute("modoEdicion", false);
        return "ofertas-form";
    }

    @PostMapping("/crear")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public String crear(@Valid @ModelAttribute("oferta") OfertasTrabajoRequest request, BindingResult result, Model model, Authentication auth) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            model.addAttribute("departamentos", departamentosService.listar());
            return "ofertas-form";
        }
        Usuarios user = usuariosRepository.findByEmail(auth.getName()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        request.setEmpresaId(empresasService.obtenerIdEmpresaPorUsuario(user.getIdUsuario()));
        service.crear(request);
        return "redirect:/ofertas";
    }

    @GetMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public String mostrarEditar(@PathVariable Integer id, Model model, Authentication auth) {
        var oferta = service.obtenerPorId(id);
        Usuarios user = usuariosRepository.findByEmail(auth.getName()).orElse(null);
        Integer idEmpresaUsuario = (user != null) ? empresasService.obtenerIdEmpresaPorUsuario(user.getIdUsuario()) : null;

        if (user != null && user.getRol() != EUsuarioRol.ADMIN && !oferta.getEmpresaId().equals(idEmpresaUsuario)) {
            return "redirect:/ofertas";
        }
        model.addAttribute("oferta", oferta);
        model.addAttribute("id", id);
        model.addAttribute("departamentos", departamentosService.listar());
        model.addAttribute("modoEdicion", true);
        return "ofertas-form";
    }

    @PostMapping("/editar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public String actualizar(@PathVariable Integer id, @Valid @ModelAttribute("oferta") OfertasTrabajoRequest request, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            model.addAttribute("id", id);
            model.addAttribute("departamentos", departamentosService.listar());
            return "ofertas-form";
        }
        service.actualizar(id, request);
        return "redirect:/ofertas";
    }

    @GetMapping("/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPRESA')")
    public String eliminar(@PathVariable Integer id, Authentication auth) {
        var oferta = service.obtenerPorId(id);
        Usuarios user = usuariosRepository.findByEmail(auth.getName()).orElse(null);
        Integer idEmpresaUsuario = (user != null) ? empresasService.obtenerIdEmpresaPorUsuario(user.getIdUsuario()) : null;

        if (user != null && (user.getRol() == EUsuarioRol.ADMIN || oferta.getEmpresaId().equals(idEmpresaUsuario))) {
            service.eliminar(id);
        }
        return "redirect:/ofertas";
    }
}